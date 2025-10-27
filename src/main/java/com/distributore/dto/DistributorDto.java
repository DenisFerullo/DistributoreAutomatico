package com.distributore.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public record DistributorDto (
			Long id, 
			String name, 
			boolean isWorking, 
			LocalDateTime lastMaintenance,
			LocalDateTime createdAt, 
			LocalDateTime updatedAt,
			Long cashRegisterId,
			Long locationId,
			List<Long> productsIds
		) 
{
}
