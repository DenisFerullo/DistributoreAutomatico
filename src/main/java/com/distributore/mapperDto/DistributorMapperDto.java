package com.distributore.mapperDto;

import com.distributore.dto.DistributorDto;
import com.distributore.entity.Distributor;

public class DistributorMapperDto {
	
	public static DistributorDto distributorToDistributorDto(Distributor d) {

       return new DistributorDto(d.getId(), d.getNome() , d.isWorking() , d.getLastMaintenance(),  d.getCreatedAt() , d.getUpdatedAt() ,d.getCashRegister(), d.getLocation(), d.getProdotti());
	}

	

	public static Distributor newDistributorDto(DistributorDto dDto) {
		Distributor d = new Distributor();
		d.setNome(dDto.nome());
		d.setLocation(dDto.location());
		d.setCashRegister(dDto.cashRegister());
		d.setCreatedAt(dDto.createdAt());
		d.setUpdatedAt(dDto.updatedAt());
		d.setLastMaintenance(dDto.lastMaintenance());
		d.setWorking(dDto.isWorking());
		return d;
	}

}
