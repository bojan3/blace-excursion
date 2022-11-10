package com.blace.excursion.controller;

import com.blace.excursion.dto.CreateReservationDTO;
import com.blace.excursion.dto.ReservationDTO;
import com.blace.excursion.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<ReservationDTO> reservationDTOs = clientService.getReservations();
        return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
    }

    @GetMapping("/reservation/cancel/{reservationId}")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable Long reservationId) {
        Boolean cancelled = clientService.cancelReservation(reservationId);
        return new ResponseEntity<>(cancelled, HttpStatus.OK);
    }

    @GetMapping("/pastExcursion")
    public ResponseEntity<List<ReservationDTO>> getPastExcursions() {
        List<ReservationDTO> pastReservations = clientService.getPastReservations();
        return new ResponseEntity<>(pastReservations, HttpStatus.OK);
    }

    @PostMapping("/createReservation")
    public ResponseEntity<Boolean> createReservatin(@RequestBody CreateReservationDTO createReservationDTO) {
        clientService.createReservation(createReservationDTO);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
