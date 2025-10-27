package com.distributore.service;

import org.springframework.stereotype.Service;
import com.distributore.entity.CashRegister;
import com.distributore.repository.CashRegisterRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CashRegisterService {

	private final CashRegisterRepository cashRepository;
	
	public void saveEntity (CashRegister cash) {
		cashRepository.save(cash);
	}
	
}
