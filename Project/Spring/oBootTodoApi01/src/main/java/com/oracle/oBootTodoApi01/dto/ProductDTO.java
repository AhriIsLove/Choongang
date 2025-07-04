package com.oracle.oBootTodoApi01.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long pno;
	private String pname;
	private int price;
	private String pdesc;//설명
	private String keyword;
	private boolean delFlag;
	
	//조회용
	private String simage;//썸네일이미지
	private String pageNum;
	private int start;
	private int end;
	private String currentPage;
	
	//이미지 파일
	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>();
	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>();
}
