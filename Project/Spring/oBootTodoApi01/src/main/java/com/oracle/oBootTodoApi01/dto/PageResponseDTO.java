package com.oracle.oBootTodoApi01.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
	private List<E> dtoList;
	private List<Integer> pageNumList;
	private PageRequestDTO pageRequestDTO;
	private int totalCount;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	private int current;
	
	//3개만 쓰는 생성자 생성
//	@Builder//(builderMethodName = "builder") : 기본 builderName
	@Builder(builderMethodName = "withAll")
	PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, int totalCount){
		this.dtoList = dtoList;
		this.pageRequestDTO = pageRequestDTO;
		this.totalCount = totalCount;
	}
}
