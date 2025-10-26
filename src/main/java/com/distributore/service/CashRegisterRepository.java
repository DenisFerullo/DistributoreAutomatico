package com.distributore.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.distributore.entity.CashRegister;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Long>{

}
