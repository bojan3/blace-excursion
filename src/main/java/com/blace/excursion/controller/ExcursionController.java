package com.blace.excursion.controller;

import com.blace.excursion.dto.excursion.ExcursionDTO;
import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.dto.error.ValidationError;
import com.blace.excursion.dto.error.ValidationErrrorResponse;
import com.blace.excursion.dto.excursion.ExcursionFilter;
import com.blace.excursion.service.ExcursionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/api/excursion", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExcursionController {

    private ExcursionService excursionService;

    @Autowired
    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<ExcursionDTO>> getAvailableExcursions(@Validated ExcursionFilter excursionFilter) {
        List<ExcursionDTO> excursionDTOs = excursionService.getAvailableExcursions(excursionFilter);
        return new ResponseEntity<>(excursionDTOs, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ExcursionDTO>> getExcursionsSorted(@RequestParam String type,
                                                                  @RequestParam String order) {
        List<ExcursionDTO> excursionDTOs = excursionService.getExcursionsSorted(type, order);
        return new ResponseEntity<>(excursionDTOs, HttpStatus.OK);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDTO>> getLocations() {
        List<LocationDTO> locationDTOs = excursionService.getLocations();
        return new ResponseEntity<>(locationDTOs, HttpStatus.OK);
    }

    @PostMapping("/location/approve")
    public ResponseEntity<Boolean> approveLocation(@RequestBody String token) {
        this.excursionService.approveLocation(token);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/location/disapprove")
    public ResponseEntity<Boolean> disapproveLocation(@RequestBody String token) {
        this.excursionService.disapproveLocation(token);
        return new ResponseEntity<>(true, HttpStatus.OK);
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
