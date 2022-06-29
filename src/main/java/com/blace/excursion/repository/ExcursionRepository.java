package com.blace.excursion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blace.excursion.model.Excursion;

public interface ExcursionRepository extends JpaRepository<Excursion, Long>{
	
	@Query(value = "select * from excursion where date > cast(now() as date)", nativeQuery = true)
	List<Excursion> findAllNotPass();
}
