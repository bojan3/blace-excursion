package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.dto.ReservationDTO;


public interface ClientService {

	List<ReservationDTO> getReservations();

	List<PastExcursionDTO> getPastExcursions();

	Boolean cancelReservation(Long excursionId);

	void createReservation(ExcursionDTO excursionDTO);

}
