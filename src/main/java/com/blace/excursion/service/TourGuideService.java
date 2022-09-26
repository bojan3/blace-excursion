package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.CreateExcursionDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.Message;
import com.blace.excursion.dto.PastExcursionDTO;

public interface TourGuideService {

	Message createExcursion(CreateExcursionDTO createExcursionDTO);

	List<PastExcursionDTO> getPastExcursions();

	List<ExcursionDTO> getExcursions();

	Boolean cancelExcursion(Long excursionId);

}
