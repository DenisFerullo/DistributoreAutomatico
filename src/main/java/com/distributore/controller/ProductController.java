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
		return ResponseEntity
				.ok(
						productService
							.getInventory()
					);
	}
	
	
	@GetMapping("/view/{nome}")
	public ResponseEntity<Long> mostraQuantitaDelProdottoConNome(@PathVariable String nome) {
		return ResponseEntity
				.ok(
						productService
							.getByNome(nome)
							.quantita()
					);
	}
	
 
// -------------------------------------------------------------------------------------------------- //
	
	
	@PostMapping("/add")
	public ResponseEntity<ProductDto> salvaProdotto(@RequestBody ProductDto pDto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(
						productService
							.addToInventory(pDto)
					);
	}

	
// -------------------------------------------------------------------------------------------------- //
	
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<ProductDto> modificaProdotto(@PathVariable Long id, @RequestBody ProductDto pDto) {
		
		ProductDto updatedDto = productService.edit(pDto, id);

		if (updatedDto != null) {
	        return ResponseEntity
	        		.ok(updatedDto);
	    } 
		else {
	        return ResponseEntity
	        		.notFound().build();
	    }
	}

	
	@PutMapping("/sell/{id}")
	public ResponseEntity<?> vendiProdotto(@PathVariable Long id){
		// Non è necessario passare un intero Dto come input, 
		// 		perché lo andremo a ricercare attraverso l'id. 
		// Non è neccesario passare la quantità da modificare, 
		// 		perché i prodotti sono erogati uno alla volta (sottrazione -1 alla volta). 
		
		try {
			return ResponseEntity
					.ok(
						productService
							.sell(id)
						);
		} catch (RuntimeException e) {
			return ResponseEntity
					.badRequest()
					.body(e.getMessage());
		}
		
		
	}
	
	
// -------------------------------------------------------------------------------------------------- //
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
		 boolean wasDeleted = productService.delete(id);
		    return wasDeleted ? 
		           ResponseEntity.noContent().build() :  // 204 se cancellato
		           ResponseEntity.notFound().build();    // 404 se non esisteva
	}

	
// -------------------------------------------------------------------------------------------------- //

	
}
