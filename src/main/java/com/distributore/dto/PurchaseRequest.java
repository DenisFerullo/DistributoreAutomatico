package com.distributore.dto;

public record PurchaseRequest(
	    
		Long productId,
	    
	    Long distributorId, 
	    
	    Double insertedAmount,
	   
	    Integer quantity
	
		) {}
