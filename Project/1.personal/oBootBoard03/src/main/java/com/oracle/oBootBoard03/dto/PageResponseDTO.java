package com.oracle.oBootBoard03.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
	private List<E> dtoList;
	private List<Integer> pageNumList;
	private PageRequestDTO pageRequestDTO;
	private int totalCount;
	private boolean prev;
	private boolean next;
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
		
		System.out.println("PageResponseDTO withAll S : " + pageRequestDTO);
		//Paging 작업
		int end = (int)(Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;
		int start = end - 9;
		int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));
		end = end > last ? last : end;

//		System.out.println("PageResponseDTO withAll 1 start : " + start);
//		System.out.println("PageResponseDTO withAll 2 end : " + end);
//		System.out.println("PageResponseDTO withAll 3 last: " + last);
		
		this.prev = start > 1;
		this.next = totalCount > end * pageRequestDTO.getSize();
		
		// 1, 2, 3
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		
		if(prev)
			this.prevPage = start - 1;
		
		if(next)
			this.nextPage = end + 1;
		
		this.totalPage = this.pageNumList.size();
		
		this.current = pageRequestDTO.getPage();
	}
}
