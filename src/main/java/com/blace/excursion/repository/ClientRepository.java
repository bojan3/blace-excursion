package com.blace.excursion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blace.excursion.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("FROM Client WHERE user_id = ?1")
	public Client getByUserId(Long userId);
}
