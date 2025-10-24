package com.distributore.specification;

import org.springframework.data.jpa.domain.Specification;

import com.distributore.entity.Category;
import com.distributore.entity.Product;

import jakarta.persistence.criteria.Join;

public class ProductSpecification {

	public static Specification<Product> hasName(String name) {
		return ((root, query, builder) -> builder.like(root.get("nome").as(String.class), "%" + name + "%"));
	}

	
	public static Specification<Product> hasCategory(String category){
		 return ((root, query, builder) -> {
		 		 Join<Product, Category> categories = root.join("categoria");
		 		 
				return builder.like(categories.get("nome").as(String.class), "%" + category + "%");
		 });
	 }

}
