package com.distributore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distributore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByNome(String nome);
}
