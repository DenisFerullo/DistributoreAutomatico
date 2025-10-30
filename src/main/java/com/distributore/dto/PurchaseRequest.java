package com.distributore.dto;

import java.math.BigDecimal;

public record PurchaseRequest(
	    
		Long productId,
	    Long distributorId, 
	    BigDecimal insertedAmount,
	    Integer quantity,
	    boolean resetSaldo			// true = nuova transazione, false = aggiungi importo 
		) {}
