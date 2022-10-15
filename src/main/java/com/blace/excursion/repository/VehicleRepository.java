package com.blace.excursion.repository;

import com.blace.excursion.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "select * from vehicle where deleted != 1", nativeQuery = true)
    List<Vehicle> findAllNotDeleted();

}
