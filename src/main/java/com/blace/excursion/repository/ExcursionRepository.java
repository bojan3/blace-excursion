package com.blace.excursion.repository;

import com.blace.excursion.model.Excursion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExcursionRepository extends PagingAndSortingRepository<Excursion, Long> {

    @Query(value = "select * from excursion where date > cast(now() as date) and approved = 1", nativeQuery = true)
    public List<Excursion> findAllNotPassApproved();

    @Query(value = "select * from excursion where tour_guide_id = :tourguideId and cancelled = 0", nativeQuery = true)
    public List<Excursion> getByUserIdNotCancelled(@Param("tourguideId") Long tourguideId);

    public Page<Excursion> findAll(Specification<Excursion> specification, Pageable pageable);
}
