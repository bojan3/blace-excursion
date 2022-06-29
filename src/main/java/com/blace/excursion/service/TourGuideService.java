package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.CreateExcursionDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;

public interface TourGuideService {

	Boolean createExcursion(CreateExcursionDTO createExcursionDTO);

	List<PastExcursionDTO> getPastExcursions();

	List<ExcursionDTO> getExcursions();

}
