package com.oracle.oBootTodoApi01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootTodoApi01.dao.ProductDAO;
import com.oracle.oBootTodoApi01.domain.Product;
import com.oracle.oBootTodoApi01.domain.ProductImage;
import com.oracle.oBootTodoApi01.dto.PageRequestDTO;
import com.oracle.oBootTodoApi01.dto.PageResponseDTO;
import com.oracle.oBootTodoApi01.dto.ProductDTO;
import com.oracle.oBootTodoApi01.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;// JPA
	private final ProductDAO productDAO;
	
	@Override
	public Long register(ProductDTO productDTO) {
		Product product = dtoToEntity(productDTO);
		
		Product saveProduct = productRepository.save(product);
		
		return saveProduct.getPno();
	}

	private Product dtoToEntity(ProductDTO productDTO) {
		Product product = Product.builder()
				.pno(productDTO.getPno())
				.pname(productDTO.getPname())
				.pdesc(productDTO.getPdesc())
				.price(productDTO.getPrice())
				.keyword(productDTO.getKeyword())
				.build();
		
		// 업로드 처리가 끝난 파일들의 이름 리스트
		List<String> uploadFileNames = productDTO.getUploadFileNames();
		if(uploadFileNames == null) return product;
		else {
			// Entity에게 uploadName을 넘겨중 --> List<ProductImage> imageList 누적
			uploadFileNames.stream().forEach(uploadFileName -> {
				product.addImageString(uploadFileName);
			});
		}
		
		return product;
	}

	@Override
	public int productTotal() {
		int totalCount = productDAO.totalProduct();
		
		return totalCount;
	}

	@Override
	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
		List<ProductDTO> productDTOList = productDAO.listProduct(pageRequestDTO);
		
		int totalCount = productDAO.totalProduct();
		
		// Build 사용이유 : 유지보수 용이 
		// 1. 많은 필드(특히 선택적 필드 많을 때)
		// 2. 객체 생성 과정이 복잡할 때
		// 3. 가독성(불변 객채)
		// 간단 객체 --> 생성자
		return PageResponseDTO.<ProductDTO>withAll()
				.dtoList(productDTOList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}

	@Override
	public ProductDTO get(Long pno) {
		Optional<Product> maybeProduct = productRepository.selectOne(pno);
		Product product = maybeProduct.orElseThrow();
		
		ProductDTO productDTO = entityToDTO(product);
		
		return productDTO;
	}

	private ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = ProductDTO.builder()
				.pno(product.getPno())
				.pname(product.getPname())
				.pdesc(product.getPdesc())
				.price(product.getPrice())
				.keyword(product.getKeyword())
				.build();
		
		List<ProductImage> imageList = product.getImageList();
		if(imageList == null || imageList.size() == 0) return productDTO;
		else {
			// 람다함수 : 이름 없는 익명 함수(Anonymous Function)
			// - 기본구조
			// -- (매개변수) -> { 함수 본문 }
			// -- list.stream().행동(변수 -> 변수사용코드).마무리;
			// - 명령어(행동)
			// -- map : 각 요소 반환
			// -- filter : 조건에 맞는 요소 반환
			// -- flatMap : 각 요소만 반환(요소가 배열일 경우 배열을 분리해서 요소로만 반환)
			// -- sorted : 정렬해서 반환
			// -- distinct : 중복없이 반환
			// -- limit : 앞에서부터 정해진 갯수만 반환
			// -- skip : 앞에서부터 정해진 갯수를 건너뛰고 나머지 반환
			// - 명령어(마무리)
			// -- forEach : 각 요소에 대한 작업 수행
			// -- collect : 각 요소를 반환
			// -- reduce : 요소를 하나로 압축(하나의 String으로 합치기)
			// -- count, min, max, sum, average : 통계
			// -- anyMatch, allMatch, noneMatch : 조건에 맞는 요소들 반환
			// -- findFirst : 순서가 가장 앞에 있는 요소 반환
			// -- findAny : 가장 먼저 탐색되는 아무 요소 반환(존재여부 확인)
			List<String> fileNameList = imageList.stream()
					.map(productImage->productImage.getFileName()).toList();
			
			productDTO.setUploadFileNames(fileNameList);
		}
		
		return productDTO;
	}

	@Override
	public void remove(Long pno) {
		//삭제(실제로 삭제하지 않음)
		productRepository.updateToDelete(pno, true);
		
	}

	@Override
	public void modify(ProductDTO productDTO) {
		//DB에서 가져오기
		Optional<Product> maybeProduct = productRepository.findById(productDTO.getPno());
		Product product = maybeProduct.orElseThrow();
		
		//기본 정보 수정
		product.changePname(productDTO.getPname());
		product.changePdesc(productDTO.getPdesc());
		product.changePrice(productDTO.getPrice());
		product.changeKeyword(productDTO.getKeyword());
		
		//이미지 목록 삭제
		product.clearList();
		
		//이미지 목록 생성
		List<String> uploadFileNames = productDTO.getUploadFileNames();
		if(uploadFileNames != null && uploadFileNames.size() > 0) {
			uploadFileNames.stream()
			.forEach(uploadName -> {
				product.addImageString(uploadName);
			});
		}
		
		//DB에 저장
		productRepository.save(product);
	}
}
