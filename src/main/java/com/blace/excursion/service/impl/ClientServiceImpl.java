package com.blace.excursion.service.impl;

import com.blace.excursion.dto.CreateReservationDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.dto.ReservationDTO;
import com.blace.excursion.model.*;
import com.blace.excursion.repository.ClientRepository;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.ReservationRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private ExcursionRepository excursionRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ReservationRepository reservationRepository,
                             UserRepository userRepository, ExcursionRepository excursionRepository) {
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.excursionRepository = excursionRepository;
    }

    @Override
    public List<ReservationDTO> getReservations() {
        Set<Reservation> reservations = clientRepository.getOne(getClientId()).getReservations();
        return reservationsToDTO(getNotCancelledReservations(reservations));
    }

    private Set<Reservation> getNotCancelledReservations(Set<Reservation> reservations) {
        Iterator<Reservation> it = reservations.iterator();

        while (it.hasNext()) {
            Reservation reservation = it.next();
            java.sql.Date date = new java.sql.Date(reservation.getExcursion().getDate().getTime());
            if (reservation.getCancelled() || date.before(new java.sql.Date(Calendar.getInstance().getTime().getTime()))) {
                reservations.remove(reservation);
            }

        }
        return reservations;
    }

    private Long getUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        return user.getId();
    }

    private Long getClientId() {
        Client client = clientRepository.getByUserId(getUserId());
        return client.getId();
    }

    private Client getClient() {
        return clientRepository.getByUserId(getUserId());
    }

    private List<ReservationDTO> reservationsToDTO(Set<Reservation> reservations) {
        Iterator<Reservation> it = reservations.iterator();
        List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();
        while (it.hasNext()) {
            reservationDTOs.add(new ReservationDTO(it.next()));
        }
        return reservationDTOs;
    }

    @Override
    public List<ReservationDTO> getPastReservations() {
        Set<Reservation> reservations = clientRepository.getOne(getClientId()).getReservations();
        Set<Reservation> pastReservations = new HashSet<Reservation>();

        for (Reservation reservation : reservations) {

            java.sql.Date date = new java.sql.Date(reservation.getExcursion().getDate().getTime());
            if (date.before(new java.sql.Date(Calendar.getInstance().getTime().getTime()))) {
                pastReservations.add(reservation);
            }
        }

        return reservationsToDTO(pastReservations);
    }

    private List<PastExcursionDTO> pastExcursionToDTO(Set<PastExcursion> pastExcursions) {
        Iterator<PastExcursion> it = pastExcursions.iterator();
        List<PastExcursionDTO> pastExcursionDTOs = new ArrayList<PastExcursionDTO>();
        while (it.hasNext()) {
            pastExcursionDTOs.add(new PastExcursionDTO(it.next()));
        }
        return pastExcursionDTOs;
    }

    @Override
    public Boolean cancelReservation(Long reservationId) {
        Client client = clientRepository.getByUserId(getUserId());
        Iterator<Reservation> it = client.getReservations().iterator();
        while (it.hasNext()) {
            Reservation reservation = it.next();
            if (reservation.getId() == reservationId)
                reservation.setCancelled(true);
        }
        clientRepository.save(client);
        return true;
    }

    public void createReservation(CreateReservationDTO createReservationDTO) {
        Client client = getClient();
        Excursion excursion = excursionRepository.getOne(createReservationDTO.getExcursionId());
        Reservation reservation = new Reservation(client, excursion, createReservationDTO.getNumOfPersons());
        System.out.println(reservation.getClient().getId());
        reservationRepository.save(reservation);
    }

    @Override
    public void createNewClient(Client client) {
        this.clientRepository.save(client);
    }

}
