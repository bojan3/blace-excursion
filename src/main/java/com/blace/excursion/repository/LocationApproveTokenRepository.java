package com.blace.excursion.repository;

import com.blace.excursion.model.LocationApproveToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface LocationApproveTokenRepository extends JpaRepository<LocationApproveToken, Long> {
    @Query(value = "select * from location_approve_token where token = ?1", nativeQuery = true)
    LocationApproveToken getByToken(String token);

    @Query(value = "select * from location_approve_token where excursion_id = ?1", nativeQuery = true)
    Set<LocationApproveToken> findAllByExcursionId(Long id);
}
