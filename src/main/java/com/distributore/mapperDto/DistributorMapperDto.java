package com.distributore.mapperDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.distributore.dto.DistributorDto;
import com.distributore.entity.Distributor;
import com.distributore.entity.Product;

public class DistributorMapperDto {
	
	public static DistributorDto toDto(Distributor d) {

		if (d == null)	return null;
		
       return new DistributorDto(
    		   	d.getId(), 
    		   	d.getName() , 
    		   	d.isWorking() , 
    		   	d.getLastMaintenance(),  
    		   	d.getCreatedAt() , 
    		   	d.getUpdatedAt() ,
    		   	d.getCashRegister() != null ? d.getCashRegister().getId() : null, 
    		   	d.getLocation() != null ? d.getLocation().getId() : null, 
    		   	d.getProducts() != null ? d.getProducts().stream().map(Product::getId).toList() : Collections.emptyList());
	}
	
	
	
	public static List<DistributorDto> toDtoList(List<Distributor> distributors) {
       
		if (distributors == null) return Collections.emptyList();
        
        return distributors.stream()
	            .map(DistributorMapperDto::toDto)
	            .collect(Collectors.toList());
    }

	

	// FIXME: siamo passati da DTO contenti entity, a DTO che contengono altri DTO (soltanto gli Ids)
	public static Distributor toEntity(DistributorDto dDto) {
		 if (dDto == null) return null;
	        
	        return Distributor.builder()
	            .id(dDto.id())
	            .name(dDto.name())
	            .isWorking(dDto.isWorking())
	            .lastMaintenance(dDto.lastMaintenance())
	            .createdAt(dDto.createdAt())
	            .updatedAt(dDto.updatedAt())
	           
	            // non implementiamo qui CashRegister, Location e Products perché sono oggetti relazionali (e non campi diretti - dovremmo usare i service)
	           
	            .build();
	}
	
	
	
	public static void updateFromDto(DistributorDto dDto, Distributor distributor) {
        
		if (dDto == null || distributor == null) return;
        
		        distributor.setName(dDto.name());
		        distributor.setWorking(dDto.isWorking());
		        distributor.setLastMaintenance(dDto.lastMaintenance());
		        distributor.setCreatedAt(dDto.createdAt());
		        distributor.setUpdatedAt(dDto.updatedAt());
    }

}
