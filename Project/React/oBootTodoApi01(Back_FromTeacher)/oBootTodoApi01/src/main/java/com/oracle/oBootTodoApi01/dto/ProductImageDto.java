package com.oracle.oBootTodoApi01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {
	private Long    product_pno;
	private int     ord;
	private String  file_name;

}
