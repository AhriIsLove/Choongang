package com.oracle.oBootTodoApi01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oracle.oBootTodoApi01.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.pno = :pno")
	Optional<Product> selectOne(@Param("pno") Long pno);
	
	// @Modifying
	// - @Query를 통해 INSERT, DELETE, UPDATE쿼리를 쓰게 될때 무조건 사용해야하는 어노테이션
	@Modifying
	@Query("UPDATE Product p SET p.delFlag = :flag WHERE p.pno = :pno")
	void updateToDelete(@Param("pno") Long pno, @Param("flag") boolean flag);
}
