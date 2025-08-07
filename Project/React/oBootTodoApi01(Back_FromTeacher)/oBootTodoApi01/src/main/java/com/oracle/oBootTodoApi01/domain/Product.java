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
@Table(name = "tbl_product")
@Getter
@ToString(exclude = "imageList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name         = "product_seq_gen",       // Seq 객체
		sequenceName = "product_seq_generator", // Seq DB 
		initialValue = 1,
		allocationSize = 1
		)
public class Product {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "product_seq_gen"
			)
	private Long pno;
	private String pname;
	private int price;
	private String pdesc;
	private boolean delFlag;
	private String keyword;
	
	// ProductImage는 @Embeddable로 정의된 값 타입 객체로, 독립적인 엔티티가 아니라 Product에 종속
	// JPA는 자동으로 Product 테이블과는 별도로 컬렉션을 저장할 테이블을 생성
	// 엔티티명_필드명' 형태의 테이블이 생성됨  PRODUCT_IMAGELIST
	// 명시적으로 테이블 이름을 지정하려면 @CollectionTable 어노테이션을 추가로 사용
	@ElementCollection
	@Builder.Default
	private List<ProductImage> imageList = new ArrayList<>(); 

	// 견고한 객체지향 모델 구축 설계
	// 1. Business 로직 구축
	// 2. 개발자 의도 명확 
	public void changeDel(boolean delFlag) {
	    this.delFlag = delFlag;
	}

	public void changePrice(int price) {
	    this.price = price;
	}
  
  	public void changeDesc(String desc){
      this.pdesc = desc;
    }

    public void changeName(String name){
      this.pname = name;
    }

    public void changeKeyword(String keyword){ 
    	this.keyword = keyword; 
    }

    // addImage(): ProductImage 객체를 리스트에 추가하고 순서(ord) 값을 설정
    public void addImage(ProductImage image) {
      image.setOrd(this.imageList.size());
      imageList.add(image);
  	  System.out.println("Product addImage productImage->"+image);
  	  System.out.println("Product addImage imageList->"+imageList);

    }

    // 파일명만 받아서 ProductImage 객체를 생성하고 리스트에 추가
    public void addImageString(String fileName){
    	ProductImage productImage = 
    			ProductImage.builder()
							.fileName(fileName)
							.build();
    	
    	addImage(productImage);

    }

    // clearList(): 모든 이미지를 리스트에서 제거
    public void clearList() {
    	this.imageList.clear();
    }
   
    
    
	

}
