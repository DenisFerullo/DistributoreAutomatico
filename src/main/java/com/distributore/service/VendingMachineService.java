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

	
// --------------------------------- IN SVILUPPO ------------------------------------------------------------------------
	
	@Transactional
	public PurchaseResult processPurchase(PurchaseRequest request) {
		
		try {
		
		Product product = productService.getEntityById(request.productId());
		
		Distributor distributor = distributorService.getEntityById(request.distributorId());

		
// 		0. Gestione reset saldo per nuova transazione
		if (request.resetSaldo()) {
			distributor.resetSaldoTemporale();
			distributorService.saveEntity(distributor);
		}
		
// 		1. Verifica che il distributore sia operativo
		if ( distributor == null || !distributor.isWorking()) {
			
			// Se c'è saldo accumulato, lo restituiamo
			BigDecimal saldoDaRestituire = distributor != null ?
					distributor.getSaldoTemporale() : BigDecimal.ZERO;
			
			if (distributor != null) {
				distributor.resetSaldoTemporale();
				distributorService.saveEntity(distributor);
			}
			
			return new PurchaseResult(
						false, 
						"Distributore non trovato o non operativo", 
						null, 
						saldoDaRestituire,									// Restituisce il saldo accumulato
						LocalDateTime.now()
					);
		}

		
// 		2. Controlla disponibilità prodotto
		if (product == null) {
			
			 // Restituisce il saldo accumulato
            BigDecimal saldoDaRestituire = distributor.getSaldoTemporale();
            distributor.resetSaldoTemporale();
            distributorService.saveEntity(distributor);
            
			return new PurchaseResult(
						false, 
						"Prodotto non trovato", 
						null, 
						saldoDaRestituire, 
						LocalDateTime.now()
					);
		}

		
// 		3. Verifica quantità disponibile
		if(product.getQuantity() < request.quantity()) {
			
			// Restituisce il saldo accumulato
            BigDecimal saldoDaRestituire = distributor.getSaldoTemporale();
            distributor.resetSaldoTemporale();
            distributorService.saveEntity(distributor);
		
			return new PurchaseResult(
					false,
					"Quantità non disponibile",
					null,
					saldoDaRestituire,
					LocalDateTime.now()
				);
		}
		
// 		4. AGGIUNGI L'IMPORTO AL SALDO TEMPORALE
        distributor.addToSaldoTemporale(request.insertedAmount());
        distributorService.saveEntity(distributor);
        
        BigDecimal saldoAttuale = distributor.getSaldoTemporale();
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

		
//		 5. Verifica che il saldo sia sufficiente
		if (saldoAttuale.compareTo(totalPrice) < 0) {
			
			BigDecimal mancante = totalPrice.subtract(saldoAttuale);
			
			return new PurchaseResult(
						false, 
						String.format("importo insufficiente. Richiesto : €%.2f, mancano €%.2f", saldoAttuale, mancante ), 
						null, 
						null,											// Non restituisce resto parziale (l'importo rimane nel saldo)
						LocalDateTime.now()
					);
		}
		
// 		6. Calcola resto (differenza tra saldo accumulato e prezzo)
		BigDecimal change = saldoAttuale.subtract(totalPrice);

		
// 		7. Verifica disponibilità resto in cassa
		CashRegister cashRegister = distributor.getCashRegister();
		
		if (cashRegister.getTotalCash().compareTo(change) < 0) {
			
			 // Restituisci TUTTO il saldo temporale
            BigDecimal totaleDaRestituire = distributor.getSaldoTemporale();
            distributor.resetSaldoTemporale();
            distributorService.saveEntity(distributor);
			
			return new PurchaseResult(
						false, 
						"Impossibile erogare il resto. Riprova con importo esatto", 
						null, 
						totaleDaRestituire, 
						LocalDateTime.now()
					);
		}

// 		8. Registra vendita 
		SalesRegister newSale = SalesRegister.builder()
					.saleDate(LocalDateTime.now())
					.soldQuantity(request.quantity())
					.totalAmount(totalPrice)
					.product(product)
					.cashRegister(cashRegister)
					.distributor(distributor)
				.build();
		
		salesRegisterService.saveEntity(newSale);
		

// 		9. Aggiorna cassa --
		cashRegister.setTotalCash(cashRegister.getTotalCash().add(totalPrice));
		cashRegisterService.saveEntity(cashRegister);
		

// 		10. Decrementa scorte --
		product.setQuantity(product.getQuantity() - request.quantity());
		productService.saveEntity(product);
		
		
// 		11. RESET SALDO TEMPORALE dopo acquisto completato
        BigDecimal restoDaRestituire = change; 											// Salva il resto prima di resettare
        distributor.resetSaldoTemporale();
        distributorService.saveEntity(distributor);
		
        
// 		12. Ritorna risultato
		ProductDto pDto = ProductMapperDto.toDto(product);
		return new PurchaseResult(
					true, 
					"Acquisto effettuato con successo!", 
					pDto, 
					restoDaRestituire, 
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
	
	
// ------------------------------------------------
	
	
	@Transactional
	public PurchaseResult annullaOperazioneERestituisciSaldo(Long distributorId) {
	    try {
	        Distributor distributor = distributorService.getEntityById(distributorId);
	        
	        if (distributor == null) {
	            return new PurchaseResult(
	                false,
	                "Distributore non trovato",
	                null,
	                BigDecimal.ZERO,
	                LocalDateTime.now()
	            );
	        }
	        
	        BigDecimal saldoDaRestituire = distributor.getSaldoTemporale();
	        
	        // Reset del saldo temporale
	        distributor.resetSaldoTemporale();
	        distributorService.saveEntity(distributor);
	        
	        return new PurchaseResult(
	            false,
	            "Operazione annullata. Importo restituito: €" + saldoDaRestituire,
	            null,
	            saldoDaRestituire,
	            LocalDateTime.now()
	        );
	        
	    } catch (Exception e) {
	        return new PurchaseResult(
	            false,
	            "Errore durante l'annullamento: " + e.getMessage(),
	            null,
	            BigDecimal.ZERO,
	            LocalDateTime.now()
	        );
	    }
	}

	
// -------------------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------------------- //
	
	
	// TODO: Metodo per inserimento denaro
	public Double insertMoney(Long distributorId, Double amount) {
		// Aggiorna saldo temporaneo
		return null;
	}

	
}
