package com.blace.excursion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blace.excursion.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
	
}
