package com.blace.excursion.service;

import com.blace.excursion.dto.CreateReservationDTO;
import com.blace.excursion.dto.ReservationDTO;
import com.blace.excursion.model.Client;

import java.util.List;


public interface ClientService {

    List<ReservationDTO> getReservations();

    List<ReservationDTO> getPastReservations();

    Boolean cancelReservation(Long excursionId);

    void createReservation(CreateReservationDTO createReservationDTO);

    void createNewClient(Client client);
}
