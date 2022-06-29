package com.blace.excursion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.TourGuide;

public interface ExcursionRepository extends JpaRepository<Excursion, Long>{
	
	@Query(value = "select * from excursion where date > cast(now() as date)", nativeQuery = true)
	List<Excursion> findAllNotPass();

	@Query(value = "select * from excursion where tour_guide_id = :tourguideId and cancelled = 0", nativeQuery = true)
	List<Excursion> getByUserIdNotCancelled(@Param("tourguideId") Long tourguideId);
}
