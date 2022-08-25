package com.blace.excursion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blace.excursion.model.TourGuide;

public interface TourGuideRepository extends JpaRepository<TourGuide, Long> {

	@Query(value = "select * from tour_guide where user_id = :userId", nativeQuery = true)
	public TourGuide getByUserId(@Param("userId") Long userId);

	@Query(value = "select * from tour_guide where deleted != 1", nativeQuery = true)
	public List<TourGuide> findAllNotDeleted();
}
