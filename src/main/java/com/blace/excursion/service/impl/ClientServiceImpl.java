package com.blace.excursion.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.dto.ReservationDTO;
import com.blace.excursion.model.Client;
import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.PastExcursion;
import com.blace.excursion.model.Reservation;
import com.blace.excursion.model.ReservationKey;
import com.blace.excursion.model.User;
import com.blace.excursion.repository.ClientRepository;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.ReservationRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.service.ClientService;

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

	private Set<Reservation> getNotCancelledReservations(Set<Reservation> reservations){
		Iterator<Reservation> it = reservations.iterator();
		while (it.hasNext()) {
			Reservation reservation = it.next();
			if (reservation.getCancelled()) {
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

	private List<ReservationDTO> reservationsToDTO(Set<Reservation> reservations) {
		Iterator<Reservation> it = reservations.iterator();
		List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();
		while (it.hasNext()) {
			reservationDTOs.add(new ReservationDTO(it.next()));
		}
		return reservationDTOs;
	}

	@Override
	public List<PastExcursionDTO> getPastExcursions() {
		Set<PastExcursion> pastExcursions = clientRepository.getOne(getClientId()).getPastExcursions();
		return pastExcursionToDTO(pastExcursions);
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

	@Override
	public void createReservation(ExcursionDTO excursionDTO) {
		Client client = clientRepository.getOne(getClientId());
		Excursion excursion = excursionRepository.getOne(excursionDTO.getId());
		Reservation reservation = new Reservation(client, excursion, 1);
		reservationRepository.save(reservation);
	}

}
