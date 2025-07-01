package com.oracle.oBootTodoApi01.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductImage {
	private int ord;
	private String fileName;

	public void setOrd(int ord) {
		this.ord = ord;
	}
}
