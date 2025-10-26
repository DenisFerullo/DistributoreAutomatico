package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.distributore.entity.Distributor;
import com.distributore.repository.DistributorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistributorService {
	
	private final DistributorRepository distributoreRepository;

	public List<Distributor> getAll() {
		List<Distributor> distributori = distributoreRepository.findAll();
		return distributori;
	}

}
