package com.blace.excursion.repository;

import com.blace.excursion.model.Reservation;
import com.blace.excursion.model.ReservationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationKey> {

}
