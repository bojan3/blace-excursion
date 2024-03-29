package com.blace.excursion.controller;

import com.blace.excursion.dto.*;
import com.blace.excursion.dto.error.ErrorResponse;
import com.blace.excursion.dto.error.ValidationError;
import com.blace.excursion.dto.error.ValidationErrrorResponse;
import com.blace.excursion.dto.excursion.CreateExcursionDTO;
import com.blace.excursion.dto.excursion.ExcursionDTO;
import com.blace.excursion.exception.FailedOrganizeExcursionException;
import com.blace.excursion.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tourguide", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourGuideController {

    private TourGuideService tourGuideService;

    @Autowired
    public TourGuideController(TourGuideService tourGuideService) {
        this.tourGuideService = tourGuideService;
    }

    @PostMapping("/create/excursion")
    public ResponseEntity<ExcursionDTO> createExcursion(@Validated @RequestBody CreateExcursionDTO createExcursionDTO) throws MessagingException, FailedOrganizeExcursionException {
        ExcursionDTO excursion = tourGuideService.createExcursion(createExcursionDTO);
        return new ResponseEntity<>(excursion, HttpStatus.OK);
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

    @ExceptionHandler(value = FailedOrganizeExcursionException.class)
    public ResponseEntity<ErrorResponse> FailedOrganizeExcursionExceptionHandler(FailedOrganizeExcursionException failedOrganizeExcursionException){

        return new ResponseEntity<>(new ErrorResponse(400, failedOrganizeExcursionException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrrorResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        HashSet<ValidationError> errors = new HashSet<ValidationError>();

        for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
        }

        return new ResponseEntity<>(new ValidationErrrorResponse(400, "Invalid request data", errors), HttpStatus.BAD_REQUEST);
    }
}
