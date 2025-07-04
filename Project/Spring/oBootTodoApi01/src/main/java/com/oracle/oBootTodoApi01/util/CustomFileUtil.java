package com.oracle.oBootTodoApi01.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

// @Controller, @Service, @Repository, @Configuration 외 등록 
// @Component : 클래스 등록
// @Bean : 함수 등록
@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {
	// @Value
	// - application.yml에서 Key로 찾아 Value를 저장
	// -- Key : com.oracle.oBootTodoApi01.upload.path
	// -- Value : upload
	@Value("${com.oracle.oBootTodoApi01.upload.path}")
	private String uploadPath;
	
	// @PostConstruct
	// - 의존성 주입(DI)이후 자동으로 실행(초기화 작업)
	@PostConstruct
	public void init() {
		// 폴더 확인
		File tempFolder = new File(uploadPath);
		if(tempFolder.exists() == false) {
			// 폴더 생성
			tempFolder.mkdir();
		}
		
		uploadPath = tempFolder.getAbsolutePath();
		log.info(uploadPath);
	}
	
	public List<String> saveFiles(List<MultipartFile> files) {
		//파일을 보내지 않을떄는 POSTMAN에서 files를 체크 해제 해야 한다.
		//체크한 상태로 파일없이 보내면 이름없는 파일이 생성된다.
		if(files == null || files.size() == 0) {
			return null;
		}
		// return : 업로드 결과 파일 명 리스트
		List<String> uploadNames = new ArrayList<>();
		for(MultipartFile multipartFile : files) {
			// UUID.randomUUID() : 고유 ID 랜덤 생성
			String savedName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
			
			// Path : 파일 경로 만들기
			// - uploadPath : 폴더 경로
			// - savedName : 파일 명
			Path savePath = Paths.get(uploadPath, savedName);
			
			try {
				// Files.copy : 파일 복사
				// - multipartFile.getInputStream()를 savePath로 복사
				Files.copy(multipartFile.getInputStream(), savePath);
				
				//확장자명
				String contentType = multipartFile.getContentType();
				if(contentType != null && contentType.startsWith("image")) {
					// 썸네일 파일 경로
					Path thumbnailPath = Paths.get(uploadPath, "s_"+savedName);
					
					// Thumbnails
					// - build.gradle에 implementation 'net.coobird:thumbnailator:0.4.19' 추가 필요
					// - savePath.toFile()을 thumbnailPath.toFile()로 생성
					Thumbnails.of(savePath.toFile())
					.size(400, 400).toFile(thumbnailPath.toFile());
				}

				// return : 업로드 결과 파일 명 리스트 추가
				uploadNames.add(savedName);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		// return : 업로드 결과 파일 명 리스트
		return uploadNames;
	}

	public void deletFiles(List<String> deleteFileNames) {
		if(deleteFileNames == null || deleteFileNames.size() == 0) {
			return;
		}
		
		deleteFileNames.forEach(fileName->{
			//썸네일파일, 파일 삭제
			String thumbnailFileName = "s_" + fileName;
			Path thumbnailFilePath = Paths.get(uploadPath, thumbnailFileName);
			Path filePath = Paths.get(uploadPath, fileName);
			
			try {
				Files.deleteIfExists(thumbnailFilePath);
				Files.deleteIfExists(filePath);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		});
	}

	public ResponseEntity<Resource> getFile(String fileName) {
		// File.separator
		// - 환경에 맞는 구분자(/, \, ...) 자동 설정
		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		
		// 요청한 파일이 누락된 경우 default.jpeg를 보내준다.
		if(!resource.exists()) {
			resource = new FileSystemResource(uploadPath + File.separator + "default.jpeg");
		}
		
		// 응답 Header 생성 후 Content-Type 적용
		HttpHeaders headers = new HttpHeaders();
		try {
			// Content-Type
			// - text/html: HTML 문서
			// - text/css: CSS 파일
			// - application/javascript: JavaScript 파일
			// - image/jpeg: JPEG 이미지
			// - image/jpg: JPG 이미지
			// - image/png: PNG 이미지
			// - *** application/json: JSON 데이터 
			// - application/pdf: PDF 문서
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		// 최종적 200 상태코드 +  응답 Header + 파일 Resource 전달
		// 주요 목적 : File D/L 또는 Image 보여줄때
		return ResponseEntity.ok()	// 상태코드 : 200
				.headers(headers)	// Header : Content-Type
				.body(resource);	// Body : 파일(resource)
	}
}
