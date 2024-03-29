package com.blace.excursion.service.impl;

import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.dto.excursion.ExcursionDTO;
import com.blace.excursion.dto.excursion.ExcursionFilter;
import com.blace.excursion.model.*;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.LocationApproveTokenRepository;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.service.ExcursionService;
import com.blace.excursion.util.AvailableExcursionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExcursionServiceImpl implements ExcursionService {

    private ExcursionRepository excursionRepository;
    private UserRepository userRepository;
    private LocationRepository locationRepository;

    private LocationApproveTokenRepository locationApproveTokenRepository;

    @Autowired
    public ExcursionServiceImpl(ExcursionRepository excursionRepository, UserRepository userRepository,
                                LocationRepository locationRepository, LocationApproveTokenRepository locationApproveTokenRepository) {
        this.excursionRepository = excursionRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.locationApproveTokenRepository = locationApproveTokenRepository;
    }

    @Override
    public List<ExcursionDTO> getAvailableExcursions(ExcursionFilter excursionFilter) {

        AvailableExcursionSpecification excursionSpecification = new AvailableExcursionSpecification(excursionFilter);
        Pageable pageSpecification = PageRequest.of(excursionFilter.getPageIndex(), excursionFilter.getPageSize());

        Page<Excursion> excursions = this.excursionRepository.findAll(excursionSpecification, pageSpecification);

        return excursions.stream().map(excursion -> new ExcursionDTO(excursion.getId(), excursion.getDate(),
                excursion.getMaxNumberOfPersons(), excursion.getMinNumberOfPersons(),
                excursion.getPrice(), getUserFullName(excursion.getTourGuide().getUser()),
                locationsToDTO(excursion.getLocations()), getMealName(excursion.getMeal()),
                getRestaurantName(excursion.getMeal()))).collect(Collectors.toList());
    }

    private String getUserFullName(User user){
        return user.getFirstName() + " " + user.getLastName();
    }
    private String getMealName(Meal meal){
        return meal != null ? meal.getName() : null;
    }

    private String getRestaurantName(Meal meal){
        return meal != null ? meal.getRestaurant().getName() : null;
    }

    private Long getUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        return user.getId();
    }

    @Override
    public List<LocationDTO> getLocations() {
        List<Location> locations = locationRepository.findAllNotDeleted();
        return locationsToDTO(locations);
    }

    @Override
    public List<ExcursionDTO> getExcursionsSorted(String type, String order) {
        return null;
    }

    private List<LocationDTO> locationsToDTO(List<Location> locations) {
        List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
        for (Location location : locations) {
            LocationDTO locationDTO = new LocationDTO(location);
            locationDTOs.add(locationDTO);
        }
        return locationDTOs;
    }

    @Override
    public void approveLocation(String token) {
        LocationApproveToken LAToken = this.locationApproveTokenRepository.getByToken(token);
        LAToken.setApproved(true);
        this.locationApproveTokenRepository.save(LAToken);
        Excursion excursion = LAToken.getExcursion();

        if (allApproved(excursion)) {
            excursion.setApproved(true);
            this.excursionRepository.save(excursion);
        }
    }

    private boolean allApproved(Excursion excursion) {
        Set<LocationApproveToken> tokens = this.locationApproveTokenRepository.findAllByExcursionId(excursion.getId());
        for (LocationApproveToken token : tokens) {
            if (!token.getApproved())
                return false;
        }
        return true;
    }

    @Override
    public void disapproveLocation(String token) {
        LocationApproveToken LAToken = this.locationApproveTokenRepository.getByToken(token);
        LAToken.setApproved(false);
        this.locationApproveTokenRepository.save(LAToken);

        Excursion excursion = LAToken.getExcursion();
        if (excursion.getApproved()){
            excursion.setApproved(false);
            excursionRepository.save(excursion);
        }

    }


}
