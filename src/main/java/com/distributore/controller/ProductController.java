package com.distributore.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.distributore.dto.ProductDto;
import com.distributore.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

// -------------------------------------------------------------------------------------------------- //

	@GetMapping("/view")
	public ResponseEntity<List<ProductDto>> mostraListaProdotti() {
		return ResponseEntity.ok(productService.getAllAsDto());
	}

	@GetMapping("/info/{name}")
	public ResponseEntity<Long> mostraQuantitaDelProdottoConNome(@PathVariable String name) {
		return ResponseEntity.ok(productService.getDtoByName(name).quantity());
	}

	@GetMapping("/infoProdotto/{id}")
	public ResponseEntity<ProductDto> mostraProdotto(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getDtoById(id));
	}

// -------------------------------------------------------------------------------------------------- //

	@PostMapping("/add")
	public ResponseEntity<ProductDto> salvaProdotto(@RequestBody ProductDto pDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addToInventory(pDto));
	}

// -------------------------------------------------------------------------------------------------- //

	@PutMapping("/edit/{id}")
	public ResponseEntity<ProductDto> modificaProdotto(@PathVariable Long id, @RequestBody ProductDto pDto) {

		ProductDto updatedDto = productService.edit(pDto, id);

		if (updatedDto != null) {
			return ResponseEntity.ok(updatedDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/sell/{id}")
	public ResponseEntity<?> vendiProdotto(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(productService.sell(id));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

// -------------------------------------------------------------------------------------------------- //

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.ok().body("Prodotto non trovato o già eliminato");
		}
	}

// -------------------------------------------------------------------------------------------------- //

}
