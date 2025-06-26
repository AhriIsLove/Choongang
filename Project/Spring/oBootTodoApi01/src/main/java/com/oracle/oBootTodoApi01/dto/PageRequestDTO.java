package com.oracle.oBootTodoApi01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
//부모/자식 클래스 둘다 @SuperBuilder 설정시
//부모클래스의 @Builder 포함
//자식클래스에세 @Builder 포함
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
	@Builder.Default
	private int page = 1;
	@Builder.Default
	private int size = 10;
	private int start;
	private int end;
}
