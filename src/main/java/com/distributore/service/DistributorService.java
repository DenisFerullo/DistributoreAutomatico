package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.distributore.dto.DistributorDto;
import com.distributore.entity.Distributor;
import com.distributore.mapperDto.DistributorMapperDto;
import com.distributore.repository.DistributorRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DistributorService {

	private final DistributorRepository distributorRepository;

// -------------------------------------------------------------------------------------------------- //

	public Distributor getEntityById(Long id) {
		return distributorRepository.findById(id).orElse(null);
	}

	public DistributorDto getDtoById(Long id) {
		return DistributorMapperDto.toDto(getEntityById(id));
	}

// ------------

	public List<DistributorDto> getAllAsDto() {
		return DistributorMapperDto.toDtoList(getAllAsEntities());
	}

	public List<Distributor> getAllAsEntities() {
		return distributorRepository.findAll();
	}

// -------------------------------------------------------------------------------------------------- //

	public void saveEntity(Distributor d) {
		distributorRepository.save(d);
	}
}
