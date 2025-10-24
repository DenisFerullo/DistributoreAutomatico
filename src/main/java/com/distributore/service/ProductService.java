package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.distributore.dto.ProductDto;
import com.distributore.mapperDto.ProductMapperDto;
import com.distributore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

// -------------------------------------------------------------------------------------------------- //

	public List<ProductDto> getInventory() {
		return productRepository.findAll()
				.stream()
				.map(ProductMapperDto::productToProductDto).toList();
	}

	public ProductDto getByNome(String nome) {
		return ProductMapperDto.productToProductDto(productRepository.findDistinctByNome(nome));
	}

//-------------------------------------------------------------------------------------------------- //

	public ProductDto edit(ProductDto pDto, Long id) {
		return productRepository.findById(id).map(p -> {
             ProductMapperDto.updateAttoreFromDto(pDto, p);
             
			return ProductMapperDto.productToProductDto(productRepository.save(p) /* updatedProduct */
			);
		}).orElse(null);
	}


	public ProductDto addToInventory(ProductDto pDto) {
		return ProductMapperDto.productToProductDto(productRepository.save(ProductMapperDto.newProduct(pDto)));
	}

// -------------------------------------------------------------------------------------------------- //

	public ProductDto sell(Long id) {
		return productRepository.findById(id).map(p -> {
			if (p.getQuantita() <= 0) {
				throw new RuntimeException("PRODUCT_OUT_OF_STOCK");
			}
			p.setQuantita(p.getQuantita() - 1);
			return productRepository.save(p);
		})
		.map(ProductMapperDto::productToProductDto)
			.orElseThrow(() -> new RuntimeException("Product not found"));
	}

// -------------------------------------------------------------------------------------------------- //

	public boolean delete(Long id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public ProductDto getById(Long id) {

		
		return ProductMapperDto.productToProductDto(productRepository.findById(id).orElse(null));
	}
}
