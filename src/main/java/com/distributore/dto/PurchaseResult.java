package com.distributore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseResult(
	    
		boolean success,
		String message,
		ProductDto product,
		BigDecimal change,
		LocalDateTime timestamp
		) 
{

}