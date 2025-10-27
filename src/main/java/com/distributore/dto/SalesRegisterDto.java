package com.distributore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SalesRegisterDto(
		Long id,
		LocalDateTime saleDate, 
		Integer soldQuantity, 
		BigDecimal totalAmount, 
		Long productId, 
		Long cashRegisterId, 
		Long distributorId, 
		LocalDateTime createdAt,
		LocalDateTime updatedAt
		) 
		{}
