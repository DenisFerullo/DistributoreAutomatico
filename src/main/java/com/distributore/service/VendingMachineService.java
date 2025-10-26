package com.distributore.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.distributore.dto.DistributorDto;
import com.distributore.dto.ProductDto;
import com.distributore.dto.PurchaseRequest;
import com.distributore.dto.PurchaseResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VendingMachineService {

	private final ProductService productService;
	private final CashRegisterService cashRegisterService;
	private final SalesRegisterService salesRegisterService;
	private final DistributorService distributorService;

	// TODO: Implementare flusso acquisto completo
	public PurchaseResult processPurchase(PurchaseRequest request) {

		ProductDto pDto = productService.getById(request.productId());

		DistributorDto distributorDto = distributorService.getById(request.distributorId());

		// 1. Verifica che il distributore sia operativo

		if (!distributorDto.isWorking())
			
			return new PurchaseResult(false, "", null, null, LocalDateTime.now());
		
		
		// 2. Controlla disponibilità prodotto
		
		if (pDto == null)
		
			return new PurchaseResult(false, "", null, null, LocalDateTime.now());
		
		
		// 3. Verifica che l'importo sia sufficiente

		if (request.insertedAmount() < pDto.prezzo())
		
			return new PurchaseResult(false, "", null, null, LocalDateTime.now());
		
// --------------------------------------------------------------------------- //		
		// TODO: Sostituzione con CashRegister
		// 4. Calcola resto

		Double change = pDto.prezzo() - request.insertedAmount();

		// l'importo inserito è maggiore del prezzo del prodotto
		
		if (distributorDto.cashRegister().getTotalCash() < change)
		
			return new PurchaseResult(false, "", null, null, LocalDateTime.now());

// --------------------------------------------------------------------------- //		

		
		// 5. Registra vendita
		// 6. Aggiorna cassa
		// 7. Decrementa scorte
		// 8. Ritorna risultato

		return new PurchaseResult(false, "", null, null, LocalDateTime.now());
	}

	// TODO: Metodo per inserimento denaro
	public Double insertMoney(Long distributorId, Double amount) {
		// Aggiorna saldo temporaneo
		return null;
	}

	// TODO: Metodo per annullamento transazione
	public void cancelTransaction(Long distributorId) {
		// Resetta saldo temporaneo
	}
}
