package com.distributore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public record  ProductDto (
			Long id , 
			String sku,
			String name, 
			BigDecimal price,
			Long quantity, 
			Integer re_StockValue,
			Long categoryId,
			LocalDateTime createdAt,
			LocalDateTime updatedAt
		)
{
}
