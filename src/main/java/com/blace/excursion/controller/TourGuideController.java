package com.blace.excursion.controller;

import com.blace.excursion.dto.*;
import com.blace.excursion.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tourguide", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourGuideController {

    private TourGuideService tourGuideService;

    @Autowired
    public TourGuideController(TourGuideService tourGuideService) {
        this.tourGuideService = tourGuideService;
    }

    @PostMapping("/")
    public ResponseEntity<Message> createExcursion(@RequestBody CreateExcursionDTO createExcursionDTO) throws MessagingException {
        Message message = tourGuideService.createExcursion(createExcursionDTO);
//		if (created == false) {
//			return new ResponseEntity<>(created, HttpStatus.BAD_REQUEST);
//		}
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/excursions")
    public ResponseEntity<List<TourguideExcursionDTO>> getExcursions() {
        List<TourguideExcursionDTO> excursionDTOs = tourGuideService.getExcursions();
        return new ResponseEntity<>(excursionDTOs, HttpStatus.OK);
    }

    @GetMapping("/cancel/{excursionId}")
    public ResponseEntity<Boolean> cancelExcursion(@PathVariable Long excursionId) {
        Boolean cancelled = this.tourGuideService.cancelExcursion(excursionId);
        return new ResponseEntity<>(cancelled, HttpStatus.OK);
    }

    @GetMapping("/create/excursion/vehicles/")
    public ResponseEntity<List<List<VehicleDTO>>> getVehiclesSuggestion(@RequestParam Date date, @RequestParam Integer maxNumberOfPersons) {
        VehicleFilter vehicleFilter = new VehicleFilter(date, maxNumberOfPersons);
        List<List<VehicleDTO>> vehiclesSuggestion = this.tourGuideService.getVehiclesSuggestion(vehicleFilter);
        return new ResponseEntity<>(vehiclesSuggestion, HttpStatus.OK);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getRestaurants() {

        List<RestaurantDTO> restaurants = this.tourGuideService.getRestaurants();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
