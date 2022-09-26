package com.blace.excursion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.dto.ReservationDTO;
import com.blace.excursion.service.ClientService;

@RestController
@RequestMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	private ClientService clientService;
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping("/reservation")
	public ResponseEntity<List<ReservationDTO>> getReservations(){
		List<ReservationDTO> reservationDTOs = clientService.getReservations();
		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/reservation/cancel/{reservationId}")
	public ResponseEntity<Boolean> cancelReservation(@PathVariable Long reservationId){
		Boolean cancelled = clientService.cancelReservation(reservationId);
		return new ResponseEntity<>(cancelled, HttpStatus.OK);
	}
	
	@GetMapping("/pastExcursion")
	public ResponseEntity<List<PastExcursionDTO>> getPastExcursions(){
		List<PastExcursionDTO> pastExcursionDTOs = clientService.getPastExcursions();
		return new ResponseEntity<>(pastExcursionDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/createReservation")
	public ResponseEntity<Boolean> createReservatin(@RequestBody ExcursionDTO excursionDTO){
		clientService.createReservation(excursionDTO);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
