package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UploadController {
	@RequestMapping("/upLoadFormStart")
	public String upLoadFormStart() {
		
		
		return "upLoadFormStart";
	}
	
	@RequestMapping("/uploadForm"/*, method = RequestMethod.POST*/)//Regacy방법
	public String uploadForm(HttpServletRequest request, Model model) throws IOException, ServletException {
		//이미지를 가져온다.
		Part image = request.getPart("file1");
		//Stream으로 바꾼다.
		InputStream inputStream = image.getInputStream();
		
		//파일명.확장자명
		String fileName = image.getSubmittedFileName();
		String[] split = fileName.split("\\.");
		//파일명
		String originalName = split[split.length - 2];
		//확장자명
		String suffix = split[split.length - 1];
		System.out.println("fileName : " + fileName);
		System.out.println("originalName : " + originalName);
		System.out.println("suffix : " + suffix);
		
		//프로젝트 업로드 경로
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload1/");
		System.out.println("uploadPath : " + uploadPath);
		
		//업로드
		String savedName = uploadFile(originalName, suffix, inputStream, uploadPath);
		model.addAttribute("savedName", savedName);
		
		// Service --> DB CRUD
		
		return "uploadResult";
	}

	private String uploadFile(String originalName, String suffix, InputStream inputStream, String uploadPath) throws FileNotFoundException, IOException {
		// universally unique identifier (UUID)
		// - 고유 ID 랜덤으로 부여
		UUID uid = UUID.randomUUID();

		// 폴더 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드 폴더 생성 : " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName + "." + suffix;
		log.info(savedName);
		
		// 임시파일 생성
		File tempFile = new File(uploadPath + savedName);
		
		// 임시파일에 stream복사
		try(FileOutputStream outputStream = new FileOutputStream(tempFile)) {
			int read;
			// 최대 2MB
			byte[] bytes = new byte[20480000];
			//inputStream을 읽어서 outStream에 복사
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} finally {
			System.out.println("복사 끝");
		}
		
		return savedName;
	}
	
	@RequestMapping("/uploadFileDelete")
	public String uploadFileDelete(HttpServletRequest request, Model model) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload1/");
		// Backup Folder
		String delFile = request.getParameter("delFile");
		String deleteFile = uploadPath + delFile;
		System.out.println("deleteFile : " + deleteFile);
		
		int delResult = upFileDelete(deleteFile);
		
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		
		return "uploadResult";
	}

	private int upFileDelete(String deleteFile) {
		int result = 0;
		
		File file = new File(deleteFile);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("삭제 성공~");
				result = 1;
			}
			else {
				System.out.println("삭제 실패~");
				result = 0;
			}
		}
		else {
			System.out.println("삭제 되어있음~");
			result = -1;
		}
		
		return result;
	}
}
