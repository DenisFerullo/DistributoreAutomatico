package com.distributore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.distributore.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    // Metodo per rimuovere il prodotto da tutte le inventory
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM inventory_products WHERE product_id = :productId", nativeQuery = true)
    void removeProductFromAllInventories(@Param("productId") Long productId);
    
    // Metodo per trovare tutte le inventory che contengono il prodotto
    @Query("SELECT i FROM Inventory i JOIN i.products p WHERE p.id = :productId")
    List<Inventory> findInventoriesByProductId(@Param("productId") Long productId);
}