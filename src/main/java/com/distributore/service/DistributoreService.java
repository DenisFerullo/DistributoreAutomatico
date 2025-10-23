package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.distributore.entity.Distributore;
import com.distributore.repository.DistributoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistributoreService {
	
	private final DistributoreRepository distributoreRepository;

	public List<Distributore> getAll() {
		List<Distributore> distributori = distributoreRepository.findAll();
		return distributori;
	}

}
