package com.blace.excursion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blace.excursion.model.Reservation;
import com.blace.excursion.model.ReservationKey;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationKey>{

}
