package com.distributore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public record  ProductDto (Long id , String nome, Long quantita, Integer re_StockValue){

	
}
