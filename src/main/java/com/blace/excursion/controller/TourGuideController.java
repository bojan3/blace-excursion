package com.blace.excursion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blace.excursion.dto.CreateExcursionDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.service.TourGuideService;

@RestController
@RequestMapping(value = "/api/tourguide", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourGuideController {
	
	private TourGuideService tourGuideService;
	
	
	@Autowired
	public TourGuideController(TourGuideService tourGuideService) {
		this.tourGuideService = tourGuideService;
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> createExcursion(@RequestBody CreateExcursionDTO createExcursionDTO){
		System.out.println("usao u kontroller: " + createExcursionDTO);
		Boolean created = tourGuideService.createExcursion(createExcursionDTO);
		if (created == false) {
			return new ResponseEntity<>(created, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(created, HttpStatus.OK);
	}
	
	@GetMapping("/pastExcursions")
	public ResponseEntity<List<PastExcursionDTO>> getPastExcursions(){
		List<PastExcursionDTO> pastExcursionDTOs = tourGuideService.getPastExcursions();
		return new ResponseEntity<>(pastExcursionDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/excursions")
	public ResponseEntity<List<ExcursionDTO>> getExcursions(){
		List<ExcursionDTO> excursionDTOs = tourGuideService.getExcursions();
		return new ResponseEntity<>(excursionDTOs, HttpStatus.OK);
	}
}
