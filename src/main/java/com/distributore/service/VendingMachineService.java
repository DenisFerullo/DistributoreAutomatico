package com.distributore.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.distributore.dto.ProductDto;
import com.distributore.dto.PurchaseRequest;
import com.distributore.dto.PurchaseResult;
import com.distributore.entity.CashRegister;
import com.distributore.entity.Distributor;
import com.distributore.entity.Product;
import com.distributore.entity.SalesRegister;
import com.distributore.mapperDto.ProductMapperDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendingMachineService {

	private final ProductService productService;
	private final CashRegisterService cashRegisterService;
	private final SalesRegisterService salesRegisterService;
	private final DistributorService distributorService;

	@Transactional
	public PurchaseResult processPurchase(PurchaseRequest request) {
		
		try {
		
		Product product = productService.getEntityById(request.productId());
		
		Distributor distributor = distributorService.getEntityById(request.distributorId());

		
		// 1. Verifica che il distributore sia operativo
		if (!distributor.isWorking())
			return new PurchaseResult(
						false, 
						"Distributore non operativo", 
						null, 
						request.insertedAmount(),
						LocalDateTime.now()
					);

		
		// 2. Controlla disponibilità prodotto
		if (product == null)
			return new PurchaseResult(
						false, 
						"Prodotto non trovato", 
						null, 
						request.insertedAmount(), 
						LocalDateTime.now()
					);

		
		// 3. Verifica quantità disponibile
		if(product.getQuantity() < request.quantity())
			return new PurchaseResult(
					false,
					"Quantità non disponibile",
					null,
					request.insertedAmount(),
					LocalDateTime.now()
				);
		
		
		// 4. Verifica che l'importo sia sufficiente
		BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.quantity()));
		
		if (request.insertedAmount().compareTo(totalPrice) < 0)
			return new PurchaseResult(
						false, 
						String.format("importo insufficiente. Richiesto : €%.2f", totalPrice), 
						null, 
						request.insertedAmount(),
						LocalDateTime.now()
					);

		
		// 5. Calcola resto
//		Double change = request.insertedAmount() - totalPrice;
		BigDecimal change = request.insertedAmount().subtract(totalPrice);

		
		// 6. Verifica disponibilità resto in cassa
		CashRegister cashRegister = distributor.getCashRegister();
		
		if (cashRegister.getTotalCash().compareTo(change) < 0)
			return new PurchaseResult(
						false, 
						"Impossibile erogare il resto. Riprova con importo esatto", 
						null, 
						request.insertedAmount(), 
						LocalDateTime.now()
					);


		// 7. Registra vendita 
		SalesRegister newSale = SalesRegister.builder()
					.saleDate(LocalDateTime.now())
					.soldQuantity(request.quantity())
					.totalAmount(totalPrice)
					.product(product)
					.cashRegister(cashRegister)
					.distributor(distributor)
				.build();
		
		salesRegisterService.saveEntity(newSale);
		

		// 8. Aggiorna cassa --
		cashRegister.setTotalCash(cashRegister.getTotalCash().add(totalPrice));
		cashRegisterService.saveEntity(cashRegister);
		

		// 9. Decrementa scorte --
		product.setQuantity(product.getQuantity() - request.quantity());
		productService.saveEntity(product);
		
		
		// 10. Ritorna risultato
		ProductDto pDto = ProductMapperDto.toDto(product);
		return new PurchaseResult(
					true, 
					"Acquisto effettuato con successo!", 
					pDto, 
					change, 
					LocalDateTime.now()
				);
		
		} catch (Exception e) {
			return new PurchaseResult (
					false,
					"Errore durante l'acquisto: " + e.getMessage(),
					null,
					request.insertedAmount(),
					LocalDateTime.now()
				);
		}

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
