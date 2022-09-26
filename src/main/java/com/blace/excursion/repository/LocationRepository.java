package com.blace.excursion.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blace.excursion.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query(value = "select * from location where deleted != 1", nativeQuery = true)
	List<Location> findAllNotDeleted();

	@Query(value = "select * from location where id in (?1)", nativeQuery = true)
	Set<Location> findByIds(List<Long> locationIds);

}
