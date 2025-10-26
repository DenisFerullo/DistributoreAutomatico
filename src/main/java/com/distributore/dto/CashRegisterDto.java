package com.distributore.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.distributore.entity.Distributor;
import com.distributore.entity.SalesRegister;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public record CashRegisterDto (
		Long id, 
		String nome, 
		Double totalCash, 
		LocalDateTime orario,  
		LocalDateTime createdAt, 
		LocalDateTime updatedAt, 
		Distributor distributore, 
		List<SalesRegister> sales
		) 
{

}
