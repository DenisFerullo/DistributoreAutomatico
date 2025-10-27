package com.distributore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public record CashRegisterDto (
		Long id, 
		String name, 
		BigDecimal totalCash, 
		LocalDateTime schedule,  
		LocalDateTime createdAt, 
		LocalDateTime updatedAt, 
		Long distributorId,
		List<Long> salesIds
		) 
{
}
