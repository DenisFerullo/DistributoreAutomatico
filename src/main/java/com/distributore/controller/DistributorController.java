package com.distributore.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.distributore.dto.DistributorDto;
import com.distributore.service.DistributorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/distributor")
public class DistributorController {

	private final DistributorService distributorService;

	@GetMapping("/view")
	public ResponseEntity<List<DistributorDto>> mostraListaDistributori() {

		List<DistributorDto> distributor = distributorService.getAllAsDto();

		return ResponseEntity.ok(distributor);
	}
}
