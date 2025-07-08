package com.oracle.oBootBoard03.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paging {
	private int currentPage = 1;
	private int rowPage = 10;
	private int pageBlock = 10;
	private int start;
	private int end;
	private int startPage;
	private int endPage;
	private int totalCount;
	private int totalPage;
	
	public Paging(int pTotalCount, int pCcurrentPage) {
		this.totalCount = pTotalCount;
		this.currentPage = pCcurrentPage;
		
		start = (currentPage - 1) * rowPage + 1;
		end = start + rowPage - 1;
		
		totalPage = (int)Math.ceil((double)totalCount/rowPage);
		
		startPage = currentPage - (currentPage - 1) % pageBlock;
		endPage = startPage + pageBlock - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
}
