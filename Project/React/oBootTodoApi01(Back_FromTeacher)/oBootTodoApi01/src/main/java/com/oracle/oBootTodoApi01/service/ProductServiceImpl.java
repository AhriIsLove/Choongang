package com.oracle.oBootTodoApi01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootTodoApi01.dao.ProductDao;
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

	private final ProductRepository productRepository;  // JPA
	private final ProductDao        productDao;         // MyBatis  
	
	@Override
	public Long register(ProductDTO productDTO) {
		Product product = dtoToEntity(productDTO);
		Product saveProduct = productRepository.save(product);
		
		return saveProduct.getPno();
	}
	
	private Product dtoToEntity(ProductDTO productDTO) {
		// Build 사용이유 : 유지보수 용이 
		// 1. 많은 필드(특히 선택적 필드 많을 때)
		// 2. 객체 생성 과정이 복잡할 때
		// 3. 가독성(불변 객채)
		// 간단 객체 --> 생성자
		Product product = Product.builder()
				                 .pno(productDTO.getPno())
				                 .pname(productDTO.getPname())
				                 .pdesc(productDTO.getPdesc())
				                 .price(productDTO.getPrice())
				                 .keyword(productDTO.getKeyword())
								 .build()
								 ;
		// 업로드 처리가 끝난 파일들의 이름 리스트 
		List<String> uploadfileNames = productDTO.getUploadFileNames();
		if(uploadfileNames == null)  return product;
		
		// Entity에게 uploadName명 넘겨줌  --> List<ProductImage> imageList 누적 
		uploadfileNames.stream()
					   .forEach(uploadfileName->{
						   			product.addImageString(uploadfileName);
					   			}
					   );	

		
		return product;

	}

	@Override
	public int productTotal() {
		int totalCount = productDao.totalProduct();
		System.out.println("ProductServiceImpl todoTotal totalCount->"+totalCount);
		return totalCount;
	}

	@Override
	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
	    log.info("ProductServiceImpl getList..............");

	    System.out.println("ProductServiceImpl list pageRequestDTO->"+pageRequestDTO);

	    List<ProductDTO> dtoProductList = productDao.listProduct(pageRequestDTO);
	    System.out.println("ProductServiceImpl list dtoProductList->"+dtoProductList);
	    int totalCount = productDao.totalProduct();
	    return PageResponseDTO.<ProductDTO>withAll()
                .dtoList(dtoProductList)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();	
	}

	@Override
	public ProductDTO get(Long pno) {
		Optional<Product> maybeProduct = productRepository.selectOne(pno);
		Product product = maybeProduct.orElseThrow();
		System.out.println("Service ProductDTO get product->"+product);
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
				                          .build()
				                           ;
		
		System.out.println("Service ProductDTO get entityToDTO imageList Before" );
		List<ProductImage> imageList = product.getImageList();
		
	    if(imageList == null || imageList.size() == 0 ){
		      return productDTO;
		}
		
	    List<String> fileNameList = 
	    		imageList.stream()
	    		         .map(productImage->productImage.getFileName())
	    		         .toList()
	    		         ;
	    productDTO.setUploadFileNames(fileNameList);
		System.out.println("Service ProductDTO get entityToDTO imageList After" );

		return productDTO;
	}

	@Override
	public void remove(Long pno) {
		productRepository.updateToDelete(pno, true);
		
	}

	@Override
	public void modify(ProductDTO productDTO) {
	    Optional<Product> maybeProduct = productRepository.findById(productDTO.getPno());

	    Product product = maybeProduct.orElseThrow();

	    //change pname, pdesc, price
	    product.changeName(productDTO.getPname());
	    product.changeDesc(productDTO.getPdesc());
	    product.changePrice(productDTO.getPrice());
	    product.changeKeyword(productDTO.getKeyword());

	    //upload File -- clear first
	    product.clearList();
        // DB --> Image String 
	    List<String> uploadFileNames = productDTO.getUploadFileNames();
		System.out.println("1. Service modify uploadFileNames->"+uploadFileNames );

	    if(uploadFileNames != null && uploadFileNames.size() > 0 ){
	       uploadFileNames.stream().forEach(
	    		              uploadName -> {
									         product.addImageString(uploadName);
									        }
	    		  					);
	    }
		System.out.println("1. Service modify product->"+product );
        productRepository.save(product);		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
