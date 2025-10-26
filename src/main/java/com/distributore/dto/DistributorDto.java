package com.distributore.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.distributore.entity.CashRegister;
import com.distributore.entity.Location;
import com.distributore.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public record DistributorDto (Long id , String nome , boolean isWorking , LocalDateTime lastMaintenance ,LocalDateTime createdAt , LocalDateTime updatedAt ,CashRegister cashRegister ,Location location , List<Product> prodotti) {

}
