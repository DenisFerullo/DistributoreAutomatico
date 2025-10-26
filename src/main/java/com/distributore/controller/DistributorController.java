package com.distributore.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distributore.entity.Distributor;
import com.distributore.service.DistributorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/distributore")
public class DistributorController {

	private final DistributorService distributoreService;

	@GetMapping("/view")
	public ResponseEntity<List<Distributor>> visualizzaInventario() {

		List<Distributor> distributore = distributoreService.getAll();

		return ResponseEntity.ok(distributore);
	}
}
