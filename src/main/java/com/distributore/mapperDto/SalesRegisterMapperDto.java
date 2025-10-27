package com.distributore.mapperDto;

import com.distributore.dto.SalesRegisterDto;
import com.distributore.entity.SalesRegister;

public class SalesRegisterMapperDto {
	public static SalesRegisterDto toDto(SalesRegister sr) {
        if(sr == null) return null;
        return new SalesRegisterDto(
            sr.getId(),
            sr.getSaleDate(),
            sr.getSoldQuantity(),
            sr.getTotalAmount(),
            sr.getProduct() != null ? sr.getProduct().getId() : null,
            sr.getCashRegister() != null ? sr.getCashRegister().getId() : null,
            sr.getDistributor() != null ? sr.getDistributor().getId() : null,
            sr.getCreatedAt(),
            sr.getUpdatedAt()
        );
    }
}
