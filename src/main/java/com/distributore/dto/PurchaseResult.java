package com.distributore.dto;

import java.time.LocalDateTime;

public record PurchaseResult(
	    
		boolean success,
	    
		String message,
	    
		ProductDto product,
	    
		Double change,
	    
		LocalDateTime timestamp
	
		) {}