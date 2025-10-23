package com.distributore.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distributore.entity.Distributore;
import com.distributore.service.DistributoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/distributore")
public class DistributoreController {

	private final DistributoreService distributoreService;

	@GetMapping("/view")
	public ResponseEntity<List<Distributore>> visualizzaInventario() {

		List<Distributore> distributore = distributoreService.getAll();

		return ResponseEntity.ok(distributore);
	}
}
