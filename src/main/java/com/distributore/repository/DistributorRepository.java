package com.distributore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.distributore.entity.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Long>{
	  
	
	 // Trova tutti i distributor che contengono il prodotto
    @Query("SELECT d FROM Distributor d JOIN d.products p WHERE p.id = :productId")
    List<Distributor> findDistributorsByProductId(@Param("productId") Long productId);
    
    // Rimuove il prodotto da tutti i distributor (query nativa per efficienza)
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM distributor_products WHERE product_id = :productId", nativeQuery = true)
    void removeProductFromAllDistributors(@Param("productId") Long productId);

}
