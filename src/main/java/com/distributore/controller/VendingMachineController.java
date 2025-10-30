package com.distributore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.distributore.dto.PurchaseRequest;
import com.distributore.dto.PurchaseResult;
import com.distributore.service.VendingMachineService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vending")
public class VendingMachineController {

	public final VendingMachineService sellingService;

	@PostMapping("/process")
	public ResponseEntity<PurchaseResult> selling(@RequestBody PurchaseRequest request) {
		
		PurchaseResult response = sellingService.processPurchase(request);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/annulla")
	public PurchaseResult annullaOperazione(@RequestParam Long distributorId) {
		return sellingService.annullaOperazioneERestituisciSaldo(distributorId);
	}
}
