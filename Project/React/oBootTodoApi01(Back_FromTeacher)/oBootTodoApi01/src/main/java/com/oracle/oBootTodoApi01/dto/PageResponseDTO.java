package com.oracle.oBootTodoApi01.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
	private List<E> dtoList;  //tbl_todo,tbl_product
    private List<Integer> pageNumList;
    private PageRequestDTO pageRequestDTO;
    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;
    
    @Builder(builderMethodName = "withAll")
    PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO,int totalCount ) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = totalCount;
        
        System.out.println("PageResponseDTO withAll->"+pageRequestDTO);
        // Page Setting
        int end =   (int)(Math.ceil( pageRequestDTO.getPage() / 10.0 )) *  10;
        System.out.println("PageResponseDTO withAll 1 end->"+end);
        int start = end - 9;
        int last =  (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));
        System.out.println("PageResponseDTO withAll 1 last->"+last);
        end =  end > last ? last: end;

        System.out.println("PageResponseDTO withAll start->"+start);
        System.out.println("PageResponseDTO withAll 2 end->"+end);
        System.out.println("PageResponseDTO withAll 2 last->"+last);
        System.out.println("PageResponseDTO withAll start->"+start);

        this.prev = start > 1;
        this.next =  totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start,end).boxed()
        		                                           .collect(Collectors.toList());

        if(prev) {
            this.prevPage = start -1;
        }

        if(next) {
            this.nextPage = end + 1;
        }

        this.totalPage = this.pageNumList.size();

        this.current = pageRequestDTO.getPage();

    	
    }

}
