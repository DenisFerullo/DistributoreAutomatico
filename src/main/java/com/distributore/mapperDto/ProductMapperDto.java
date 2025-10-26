package com.distributore.mapperDto;

import com.distributore.dto.ProductDto;
import com.distributore.entity.Product;

public class ProductMapperDto {

	public static ProductDto productToProductDto(Product p) {

		return new ProductDto(p.getId(), p.getNome(), p.getQuantita(), p.getRe_StockValue(), p.getPrezzo());

	}
	
	public static void updateProductFromDto(ProductDto pDto , Product p) {
		p.setNome(pDto.nome());
		p.setQuantita(pDto.quantita());
		p.setRe_StockValue(pDto.re_StockValue());
		p.setPrezzo(pDto.prezzo());
	}

	public static Product newProduct(ProductDto productDto) {
		Product product = new Product();
		product.setNome(productDto.nome());
		product.setQuantita(productDto.quantita());
		product.setRe_StockValue(productDto.re_StockValue());
		return product;
	}
	
}
