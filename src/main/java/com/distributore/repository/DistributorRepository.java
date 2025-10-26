package com.distributore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.distributore.entity.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Long>{

}
