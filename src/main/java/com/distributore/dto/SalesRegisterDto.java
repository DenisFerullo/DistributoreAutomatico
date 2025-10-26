package com.distributore.dto;

import java.time.LocalDateTime;
import com.distributore.entity.CashRegister;
import com.distributore.entity.Distributor;
import com.distributore.entity.Product;

public record SalesRegisterDto(Long id,LocalDateTime dataVendita, Integer quantitaVenduta, Double importoTotale, Product product, CashRegister cashRegister, Distributor distributore ) {

}
