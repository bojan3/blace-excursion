package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.CommentDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.LocationDTO;



public interface ExcursionService {

	List<ExcursionDTO> getExcursions();

	List<CommentDTO> getComments(Long excursionId);

	List<LocationDTO> getLocations();

}
