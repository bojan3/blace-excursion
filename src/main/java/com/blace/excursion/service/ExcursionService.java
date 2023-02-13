package com.blace.excursion.service;

import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.dto.excursion.ExcursionDTO;
import com.blace.excursion.dto.excursion.ExcursionFilter;

import java.util.List;


public interface ExcursionService {

    List<ExcursionDTO> getAvailableExcursions(ExcursionFilter ExcursionFilter);

    List<LocationDTO> getLocations();

    List<ExcursionDTO> getExcursionsSorted(String type, String order);

    void approveLocation(String token);

    void disapproveLocation(String token);
}
