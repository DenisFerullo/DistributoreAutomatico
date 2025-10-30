package com.distributore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.distributore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findDistinctByName(String name);
	
	@Query("SELECT COUNT(d) > 0 FROM Distributor d JOIN d.products p WHERE p.id = :productId")
    boolean existsInDistributors(@Param("productId") Long productId);
}
