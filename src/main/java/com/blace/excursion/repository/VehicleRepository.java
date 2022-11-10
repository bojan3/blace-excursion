package com.blace.excursion.repository;

import com.blace.excursion.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "select * from vehicle where deleted != 1", nativeQuery = true)
    List<Vehicle> findAllNotDeleted();

    @Query(value = "select * from vehicle where id in (?1)", nativeQuery = true)
    Set<Vehicle> findByIds(List<Long> vehicleIds);
}
