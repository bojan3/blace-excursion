package com.blace.excursion.service;

import com.blace.excursion.dto.CommentDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.LocationDTO;

import java.util.List;


public interface ExcursionService {

    List<ExcursionDTO> getExcursions();

    List<CommentDTO> getComments(Long excursionId);

    List<LocationDTO> getLocations();

    List<ExcursionDTO> getExcursionsSorted(String type, String order);

    void approveLocation(String token);

    void disapproveLocation(String token);
}
