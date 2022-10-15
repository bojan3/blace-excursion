package com.blace.excursion.controller;

import com.blace.excursion.dto.CommentDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.service.ExcursionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/excursion", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExcursionController {

    private ExcursionService excursionService;

    @Autowired
    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }

    @GetMapping("")
    public ResponseEntity<List<ExcursionDTO>> getExcursions() {
        List<ExcursionDTO> excursionDTOs = excursionService.getExcursions();
        return new ResponseEntity<>(excursionDTOs, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ExcursionDTO>> getExcursionsSorted(@RequestParam String type,
                                                                  @RequestParam String order) {
        List<ExcursionDTO> excursionDTOs = excursionService.getExcursionsSorted(type, order);
        return new ResponseEntity<>(excursionDTOs, HttpStatus.OK);
    }

    @GetMapping("/comments/{excursionId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long excursionId) {
        List<CommentDTO> commentDTOs = excursionService.getComments(excursionId);
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
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
}
