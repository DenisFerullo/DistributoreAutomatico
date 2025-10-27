package com.distributore.mapperDto;

import java.util.Collections;
import java.util.List;
import com.distributore.dto.ProductDto;
import com.distributore.entity.Product;

public class ProductMapperDto {

	public static ProductDto toDto(Product p) {
		
		if( p == null)	return null;
		
		return new ProductDto(
					p.getId(), 
					p.getSku(),
					p.getName(), 
					p.getPrice(),
					p.getQuantity(), 
					p.getRe_StockValue(),
					p.getCategory() != null ? p.getCategory().getId() : null,
					p.getCreatedAt(),
					p.getUpdatedAt()
				);
	}

	
	
	public static List<ProductDto> toDtoList(List<Product> products) {
		
		if(products == null)	return Collections.emptyList();
		
		return products.stream()
				.map(ProductMapperDto::toDto)
				.toList();
	}
	
	
	
	public static Product toEntity(ProductDto pDto) {
		
		if( pDto == null )	return null;
		
		return Product.builder()
	            .id(pDto.id())
	            .sku(pDto.sku())
	            .name(pDto.name())
	            .price(pDto.price())
	            .quantity(pDto.quantity())
	            .re_StockValue(pDto.re_StockValue())
	            //	.categoria(pDto.category())		Non lo consideriamo qui, perché richiederebbe di caricare una Categoria a partire dal pDto.category().getId()
	            .createdAt(pDto.createdAt())
	            .updatedAt(pDto.updatedAt())
	            .build();
	}
	
	
	
	public static void updateFromDto(ProductDto pDto , Product p) {
		
		if ( pDto == null || p == null)	return;
		
		p.setSku(pDto.sku());
	    p.setName(pDto.name());
	    p.setPrice(pDto.price());
	    p.setQuantity(pDto.quantity());
	    p.setRe_StockValue(pDto.re_StockValue());
	    // p.setCategory(pDto.category());
	    p.setCreatedAt(pDto.createdAt());
	    p.setUpdatedAt(pDto.updatedAt());
	}
	
}
