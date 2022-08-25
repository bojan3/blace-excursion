package com.blace.excursion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blace.excursion.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	@Query(value = "select * from vehicle where deleted != 1", nativeQuery = true)
	List<Vehicle> findAllNotDeleted();

}
