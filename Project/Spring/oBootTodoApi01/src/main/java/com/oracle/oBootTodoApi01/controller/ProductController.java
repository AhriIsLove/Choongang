package com.oracle.oBootTodoApi01.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.PageResponseDTO;
import com.oracle.oBootTodoApi01.dto.ProductDTO;
import com.oracle.oBootTodoApi01.service.Paging;
import com.oracle.oBootTodoApi01.service.ProductService;
import com.oracle.oBootTodoApi01.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;
	private final CustomFileUtil fileUtil;
	
	// FormData로 받을 경우 @RequestBody로 받지 않는다.
	// - @RequestBody는 application/json형태의 body를 읽는다.
	// - FormData는 binary형태의 데이터로 전송되어 @RequestBody로 읽을 수 없다.
	@PostMapping("/register")
	public Map<String, Long> register(ProductDTO productDTO){
		log.info("register S : " + productDTO);
		
		List<MultipartFile> files = productDTO.getFiles();
		
		// File Upload
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		productDTO.setUploadFileNames(uploadFileNames);
		log.info("register 1 : " + uploadFileNames);
		
		// File Upload + Product 내용 --> tbl_product Insert
		Long pno = productService.register(productDTO);
		
		return Map.of("result", pno);
	}
	
	@GetMapping("/list")
	public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO){
		System.out.println("ProductController PageResponseDTO S pageRequestDTO : " + pageRequestDTO);
		
		int totalCount = productService.productTotal();
		
		//Paging 작업
		Paging page = new Paging(totalCount, pageRequestDTO.getPage());
		//Parameter emp --> Page만 추가 Setting
		pageRequestDTO.setStart(page.getStart());
		pageRequestDTO.setEnd(page.getEnd());
		
		System.out.println("ProductController PageResponseDTO 2 pageRequestDTO : " + pageRequestDTO);
		
		return productService.getList(pageRequestDTO);
	}
	
	@GetMapping("/{pno}")
	public ProductDTO read(@PathVariable(name="pno") Long pno) {
		System.out.println("0");
		
		return productService.get(pno);
	}
	
	@DeleteMapping("/remove/{pno}")
	public Map<String, String> remove(@PathVariable(name="pno") Long pno) {
		//삭제해야할 파일들 알아내기
		List<String> deleteFileNames = productService.get(pno).getUploadFileNames();
		
		//DB 삭제
		productService.remove(pno);
		
		//파일 삭제
		fileUtil.deletFiles(deleteFileNames);
		
		return Map.of("RESULT", "SUCCESS");
	}
	
	@PutMapping("/modify/{pno}")
	public Map<String, String> modify(@PathVariable(name="pno") Long pno, ProductDTO productDTO){
		//수정 대상 제품(1u,2u,3u)
		ProductDTO oldProductDTO = productService.get(pno);
		//수정 대상 제품이 수정되어야 할 제품 상태(3,4,5)
		productDTO.setPno(pno);
		
		//기존의 파일들(1u,2u,3u)
		List<String> oldFileNames = oldProductDTO.getUploadFileNames();
		
		//새로 업로드 해야 하는 파일들(3,4,5)
		List<MultipartFile> files = productDTO.getFiles();
		
		//새로 업로드 되어서 만들어진 파일 이름들(3u,4u,5u)
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);

		//유지되는 파일들() : view에서 uploadFileNames에 값을 넣어준 상태로 보내준다면 있을것임(3u)
		List<String> uploadedFileNames = productDTO.getUploadFileNames();
		// + 새로 업로드된 파일 이름들(3u,4u,5u)
		if(currentUploadFileNames != null && currentUploadFileNames.size() > 0) {
			//addAll : 중복값 허용
			uploadedFileNames.addAll(currentUploadFileNames);
		}

		//DB 수정
		//1u,2u,3u삭제 3u,4u,5u추가
		productService.modify(productDTO);
		
		//지워야 하는 파일 목록 찾기(1u,2u,3u)
		//유지되는 파일들이 view에서 정해줘야 하는데 없으면 old의 3u와 uploaded의 3u는 다른 3u이다.
		if(oldFileNames != null && oldFileNames.size() > 0) {
			//기존의 파일들중에(u1,2u,3u)
			List<String> removeFiles = oldFileNames.stream()
					//이미 업로드된 파일들을 못찾으면 -1로 삭제대상으로 지정(3u,4u,5u)
					.filter(fileName -> uploadedFileNames.indexOf(fileName) == -1)
					.collect(Collectors.toList());
			
			//삭제(1u,2u)
			fileUtil.deletFiles(removeFiles);
		}
		
		return Map.of("RESULT", "SUCCESS");
	}

	// ResponseEntity는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스. 
    // HttpStatus, HttpHeaders, HttpBody를 포함
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGET(@PathVariable(name="fileName") String fileName){
		return fileUtil.getFile(fileName);
	}
}
