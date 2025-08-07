package com.oracle.oBootTodoApi01.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

	private final SqlSession session;
	
	@Override
	public int totalProduct() {
		int totProductCount = 0;
		System.out.println("ProductDaoImpl Start ...");
		try {
			totProductCount = session.selectOne("productTotal");
			System.out.println("ProductDaoImpl totalProduct totProductCount->" +totProductCount);
			
		} catch (Exception e) {
			System.out.println("ProductDaoImpl totalProduct e.getMessage()->"+e.getMessage());
		}
		
		return totProductCount;
	}

	@Override
	public List<ProductDTO> listProduct(PageRequestDTO pageRequestDTO) {
		int end = 0;
		List<ProductDTO> productList = null;
		System.out.println("ProductDaoImpl listProduct pageRequestDTO ->"+pageRequestDTO );
		ProductDTO productDTO = new ProductDTO();
		// Start Row 설정 
		productDTO.setStart(pageRequestDTO.getStart());
		// End   Row 설정 
		productDTO.setEnd(pageRequestDTO.getEnd());
		System.out.println("ProductDaoImpl listTodo productDTO->"+productDTO );
		try {
			//   1.   현재 여기에는 >ImageList 정보가 누락되어 있음 
			productList = session.selectList("tkProductListAll", productDTO);
			System.out.println("ProductDaoImpl listProduct productList.size()->"+productList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listProduct e.getMessage()->"+e.getMessage());
		}
		return productList;	
	}

}
