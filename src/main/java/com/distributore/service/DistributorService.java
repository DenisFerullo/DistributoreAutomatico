package com.distributore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.distributore.dto.DistributorDto;
import com.distributore.mapperDto.DistributorMapperDto;
import com.distributore.repository.DistributorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistributorService {

	private final DistributorRepository distributoreRepository;

	public List<DistributorDto> getAll() {

		return distributoreRepository.findAll().stream().map(DistributorMapperDto::distributorToDistributorDto)
				.toList();
	}

	public DistributorDto getById(Long id) {
		return DistributorMapperDto.distributorToDistributorDto(distributoreRepository.findById(id).orElse(null));

	}

}
