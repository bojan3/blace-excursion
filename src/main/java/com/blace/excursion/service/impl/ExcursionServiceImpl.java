package com.blace.excursion.service.impl;

import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Location;
import com.blace.excursion.model.LocationApproveToken;
import com.blace.excursion.model.User;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.LocationApproveTokenRepository;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.service.ExcursionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    public List<ExcursionDTO> getExcursions() {
        List<Excursion> excursions = excursionRepository.findAllNotPassApproved();
        return excursionsToDTO(excursions);
    }

    private List<ExcursionDTO> excursionsToDTO(List<Excursion> excursions) {
        List<ExcursionDTO> excursionDTOs = new ArrayList<ExcursionDTO>();

        for (Excursion excursion : excursions) {
            ExcursionDTO excursionDTO = new ExcursionDTO(excursion);
            excursionDTOs.add(excursionDTO);
        }
        return excursionDTOs;
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

    private List<LocationDTO> locationsToDTO(List<Location> locations) {
        List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
        for (Location location : locations) {
            LocationDTO locationDTO = new LocationDTO(location);
            locationDTOs.add(locationDTO);
        }
        return locationDTOs;
    }

    @Override
    public List<ExcursionDTO> getExcursionsSorted(String type, String order) {
        List<Excursion> excursions = null;
        if (order.equals("asc"))
            excursions = excursionRepository.findAll(Sort.by(type).ascending());
        else
            excursions = excursionRepository.findAll(Sort.by(type).descending());
        return excursionsToDTO(filterNotPass(excursions));
    }

    public List<Excursion> filterNotPass(List<Excursion> excursions) {
        Iterator<Excursion> it = excursions.iterator();
        while (it.hasNext())
            if (!it.next().notPass())
                it.remove();
        return excursions;
    }

    @Override
    public void approveLocation(String token) {
        LocationApproveToken LAToken = this.locationApproveTokenRepository.getByToken(token);
        LAToken.setApproved(true);
        this.locationApproveTokenRepository.save(LAToken);
        System.out.println("ID tokena je" + LAToken.getId());
        Excursion excursion = LAToken.getExcursion();

        if (allApproved(excursion)) {
//        if (allApproved(excursion)) {
            excursion.setApproved(true);
            this.excursionRepository.save(excursion);
            //TODO: send tourguide mail that excrusion is approved
            System.out.println("TODO: send tourguide mail that excrusion is approved");
        }
    }

    private boolean allApproved(Excursion excursion) {
        Set<LocationApproveToken> tokens = this.locationApproveTokenRepository.findAllByExcursionId(excursion.getId());
        Iterator<LocationApproveToken> it = tokens.iterator();
        while (it.hasNext()) {
            if (!it.next().getApproved())
                return false;
        }
        return true;
    }

    @Override
    public void disapproveLocation(String token) {
        LocationApproveToken LAToken = this.locationApproveTokenRepository.getByToken(token);
        //TODO: send tourguide mail that excrusion can't happen
        System.out.println("TODO: send tourguide mail that excrusion can't happen");
    }


}
