package com.blace.excursion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blace.excursion.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

}
