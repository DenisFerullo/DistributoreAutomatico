package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.distributore.dto.ProductDto;
import com.distributore.entity.Product;
import com.distributore.mapperDto.ProductMapperDto;
import com.distributore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

// -------------------------------------------------------------------------------------------------- //
	
	public Product getEntityById(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public ProductDto getDtoById(Long id) {
		return ProductMapperDto.toDto(getEntityById(id));
	}

// ------------

	public List<Product> getInventoryAsEntity() {
		return productRepository.findAll();
	}
	
	public List<ProductDto> getInventoryAsDto() {
		return getInventoryAsEntity().stream()
				.map(ProductMapperDto::toDto).toList();
	}

// ------------

	public Product getEntityByNome(String nome) {
		return productRepository.findDistinctByName(nome);
	}
	
	public ProductDto getDtoByNome(String nome) {
		return ProductMapperDto.toDto(getEntityByNome(nome));
	}

//-------------------------------------------------------------------------------------------------- //

	
	public void saveEntity(Product p) {
		productRepository.save(p);
	}
	
	public void saveDto(ProductDto p) {
		saveEntity(ProductMapperDto.toEntity(p));
	}
	
	// ------------

	// FIXME: Sviluppare i metodi del ProductService in versione Entity e Dto - EDIT
	public ProductDto edit(ProductDto pDto, Long id) {
		return productRepository.findById(id).map(p -> {
			ProductMapperDto.updateFromDto(pDto, p);

			return ProductMapperDto.toDto(productRepository.save(p) /* updatedProduct */
			);
		}).orElse(null);
	}

	// FIXME: Sviluppare i metodi del ProductService in versione Entity e Dto - ADD_TO_INVENTORY
	public ProductDto addToInventory(ProductDto pDto) {
		return ProductMapperDto.toDto(productRepository.save(ProductMapperDto.toEntity(pDto)));
	}

// -------------------------------------------------------------------------------------------------- //

	// FIXME: Sviluppare i metodi del ProductService in versione Entity e Dto - SELL
	public ProductDto sell(Long id) {
		return productRepository.findById(id).map(p -> {
			if (p.getQuantity() <= 0) {
				throw new RuntimeException("PRODUCT_OUT_OF_STOCK");
			}
			p.setQuantity(p.getQuantity() - 1);
			return productRepository.save(p);
		}).map(ProductMapperDto::toDto).orElseThrow(() -> new RuntimeException("Product not found"));
	}

// -------------------------------------------------------------------------------------------------- //

	// FIXME: Sviluppare i metodi del ProductService in versione Entity e Dto - DELETE
	public boolean delete(Long id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
