package com.oracle.oBootTodoApi01.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Getter
// exclude
// ToString시 imageList까지 보여주기를 시도한다.
// imageList에서 부모의 pID를 저장 해 놓기 때문에 ToString하게 될 경우
// 부모의 ToString을 불러와 무한 루프에 빠질 수 있어
// exclude로 imageList를 제외시킴
// 여러개 하는 방법 : @ToString(exclude = {"imageList", "password", "secretKey"})
@ToString(exclude = "imageList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "product_seq_gen",
		sequenceName = "product_seq_generator",
		initialValue = 1,
		allocationSize = 1
		)
public class Product {
	@Id
	@GeneratedValue(
			generator = "product_seq_gen",
			strategy = GenerationType.SEQUENCE
			)
	private Long pno;
	private String pname;
	private int price;
	private String pdesc;
	private boolean delFlag;
	private String keyword;
	
	// @ElementCollection
	// - ProductImage를 @Embeddable로 정의된 값 타입 객체로, 독립적인 엔티티가 아니라 Product에 종속
	// - JPA는 자동으로 Product 테이블과는 별도로 컬렉션을 저장할 테이블을 생성
	// - 엔티티명_필드명' 형태의 테이블이 생성됨  PRODUCT_IMAGELIST
	// - 명시적으로 테이블 이름을 지정하려면 @CollectionTable 어노테이션을 추가로 사용
	// -- 일반적으로 1:1대응, List로 설정했기 때문에 1:N대응
	@ElementCollection
	// @Builder.Default
	// @Builder는 초기값 지정하는 ' = new ArrayList<>();'를 무시하기 때문에
	// @Builder.Default로 초기값 지정되도록 설정
	@Builder.Default
	private List<ProductImage> imageList = new ArrayList<>();

	// set -> change : 견고한 객체지향 모델 구축 설계
	// 1. business 로직 구축
	// 2. 개발자의 의도를 명확하게 표시
	
	public void changePname(String pname) {
		this.pname = pname;
	}

	public void changePrice(int price) {
		this.price = price;
	}

	public void changePdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public void changeDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public void changeKeyword(String keyword) {
		this.keyword = keyword;
	}

	// ProductImage를 리스트에 추가하고 순서(ord)를 설정
	public void addImage(ProductImage image) {
		image.setOrd(imageList.size());
		imageList.add(image);
		System.out.println("Product image : " + image);
	}
	
	// 파일명만 받아서 ProductImage 객체를 생성하고 리스트에 추가
	public void addImageString(String fileName) {
		ProductImage productImage = ProductImage.builder()
				.fileName(fileName)
				.build();
		
		addImage(productImage);
	}
	
	// 모든 이미지를 리스트에서 제거
	public void clearList() {
		imageList.clear();
	}
}
