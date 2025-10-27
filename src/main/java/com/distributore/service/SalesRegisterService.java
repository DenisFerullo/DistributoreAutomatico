package com.distributore.service;

import org.springframework.stereotype.Service;
import com.distributore.entity.SalesRegister;
import com.distributore.repository.SalesRegisterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesRegisterService {
	
	private final SalesRegisterRepository salesRepository;

	public void saveEntity (SalesRegister sale) {
		salesRepository.save(sale);
	}
}
