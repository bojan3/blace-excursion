package com.blace.excursion.repository;

import com.blace.excursion.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {

    @Query(value = "select * from excursion where date > cast(now() as date)", nativeQuery = true)
    public List<Excursion> findAllNotPass();

    @Query(value = "select * from excursion where tour_guide_id = :tourguideId and cancelled = 0", nativeQuery = true)
    public List<Excursion> getByUserIdNotCancelled(@Param("tourguideId") Long tourguideId);

}
