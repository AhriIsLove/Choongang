package com.oracle.oBootMybatis01.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paging {
    private int currentPage = 1;//현재 페이지
	private int rowPage = 10;	//한 페이지당 글수
	private int pageBlock = 10;	//한 화면당 페이지수
	private int start;			//현재 페이지의 시작 글 Index
	private int end;			//현재 페이지의 끝 글 Index
	private int startPage;		//현재 화면의 시작 페이지
	private int endPage;		//현재 화면의 끝 페이지
	private int total;			//전체 글수
	private int totalPage;		//전체 페이지수
	
	public Paging(int total, String currentPage1) {
		//전체 글수
		this.total = total;
		//현제 페이지
		if(currentPage1 != null) {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		
		//현재 페이지의 시작 글/끝 글
		start = (currentPage - 1) * rowPage + 1;
		end = start + rowPage;
		
		//전체 페이지 수
		totalPage = (int)Math.ceil((double)total / rowPage);
		
		//현재 화면의 시작 페이지/끝 페이지
		startPage = currentPage - (currentPage - 1) % pageBlock;
		endPage = startPage + pageBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
}
