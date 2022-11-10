package com.blace.excursion.service;

import com.blace.excursion.dto.*;

import javax.mail.MessagingException;
import java.util.List;

public interface TourGuideService {

    Message createExcursion(CreateExcursionDTO createExcursionDTO) throws MessagingException;

    List<PastExcursionDTO> getPastExcursions();

    List<TourguideExcursionDTO> getExcursions();

    Boolean cancelExcursion(Long excursionId);

    List<List<VehicleDTO>> getVehiclesSuggestion(VehicleFilter vehicleFilter);

    List<RestaurantDTO> getRestaurants();
}
