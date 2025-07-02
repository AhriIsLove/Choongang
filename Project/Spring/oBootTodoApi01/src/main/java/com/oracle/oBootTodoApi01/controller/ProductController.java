package com.oracle.oBootTodoApi01.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.PageResponseDTO;
import com.oracle.oBootTodoApi01.dto.ProductDTO;
import com.oracle.oBootTodoApi01.dto.TodoDTO;
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
}
