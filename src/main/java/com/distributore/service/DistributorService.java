package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.distributore.dto.DistributorDto;
import com.distributore.entity.Distributor;
import com.distributore.mapperDto.DistributorMapperDto;
import com.distributore.repository.DistributorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistributorService {

	private final DistributorRepository distributoreRepository;
	
// -------------------------------------------------------------------------------------------------- //

	public Distributor getEntityById(Long id) {
		return distributoreRepository.findById(id).orElse(null);
	}
	
	public DistributorDto getDtoById(Long id) {
		return DistributorMapperDto.toDto(getEntityById(id));
	}
	
// ------------

	public List<Distributor> getAllAsEntities() {
		return distributoreRepository.findAll();
	}
	
	public List<DistributorDto> getAllAsDto() {
		return getAllAsEntities().stream().map(DistributorMapperDto::toDto).toList();
	}
	
// -------------------------------------------------------------------------------------------------- //

	

}
