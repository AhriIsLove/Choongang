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
// Embeddable
// - 독립적인 식별자(@Id)가 없음
// - Product 엔티티에 완전히 종속됨
// - 생명주기가 Product 엔티티에 의존함
// 장점 
// - 간단한 구조: 복잡한 연관관계 없이 컬렉션 관리 가능
// - 자동 영속성 전이: Product가 저장/삭제되면 ProductImage도 함께 저장/삭제됨
@Embeddable
public class ProductImage {
	private int ord;
	private String fileName;

	public void setOrd(int ord) {
		this.ord = ord;
	}
}
