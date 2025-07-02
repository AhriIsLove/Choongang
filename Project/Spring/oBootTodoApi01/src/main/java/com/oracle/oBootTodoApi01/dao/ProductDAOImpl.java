package com.oracle.oBootTodoApi01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
	private final SqlSession session;
	
	@Override
	public int totalProduct() {
		int totProductCount = 0;
		
		try {
			totProductCount = session.selectOne("productTotal");
			System.out.println("productTotal 성공");
		} catch (Exception e) {
			System.out.println("productTotal 실패");
		}
		
		return totProductCount;
	}

	@Override
	public List<ProductDTO> listProduct(PageRequestDTO pageRequestDTO) {
		int end = 0;
		List<ProductDTO> productList = null;
		
		//페이지 범위 설정
		ProductDTO productDTO = new ProductDTO();
		productDTO.setStart(pageRequestDTO.getStart());
		productDTO.setEnd(pageRequestDTO.getEnd());
		
		try {
			//범위에 맞는 리스트 조회
			productList = session.selectList("tkProductListAll", productDTO);
			System.out.println("tkProductListAll 성공");
		} catch (Exception e) {
			System.out.println("tkProductListAll 실패");
		}
		
		return productList;
	}

}
