package com.blace.excursion.service;

import com.blace.excursion.dto.*;
import com.blace.excursion.dto.excursion.CreateExcursionDTO;
import com.blace.excursion.dto.excursion.ExcursionDTO;
import com.blace.excursion.exception.FailedOrganizeExcursionException;

import javax.mail.MessagingException;
import java.util.List;

public interface TourGuideService {

    ExcursionDTO createExcursion(CreateExcursionDTO createExcursionDTO) throws MessagingException, FailedOrganizeExcursionException;

    List<TourguideExcursionDTO> getExcursions();

    Boolean cancelExcursion(Long excursionId);

    List<List<VehicleDTO>> getVehiclesSuggestion(VehicleFilter vehicleFilter);

    List<RestaurantDTO> getRestaurants();
}
