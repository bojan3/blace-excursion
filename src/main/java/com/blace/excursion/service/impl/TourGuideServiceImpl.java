package com.blace.excursion.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blace.excursion.dto.CreateExcursionDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Location;
import com.blace.excursion.model.PastExcursion;
import com.blace.excursion.model.TourGuide;
import com.blace.excursion.model.User;
import com.blace.excursion.model.Vehicle;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.TourGuideRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.repository.VehicleRepository;
import com.blace.excursion.service.TourGuideService;

@Service
public class TourGuideServiceImpl implements TourGuideService {

	private ExcursionRepository excursionRepository;
	private LocationRepository locationRepository;
	private TourGuideRepository tourGuideRepository;
	private UserRepository userRepository;
	private VehicleRepository vehicleRepository;

	@Autowired
	public TourGuideServiceImpl(ExcursionRepository excursionRepository, LocationRepository locationRepository,
			TourGuideRepository tourGuideRepository, UserRepository userRepository,
			VehicleRepository vehicleRepository) {
		this.excursionRepository = excursionRepository;
		this.locationRepository = locationRepository;
		this.tourGuideRepository = tourGuideRepository;
		this.userRepository = userRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public Boolean createExcursion(CreateExcursionDTO createExcursionDTO) {

		if (!isAvailable(createExcursionDTO))
			return false;

		Vehicle vehicle = getVehicle(createExcursionDTO);

		if (vehicle == null) {
			return false;
		}

		Location location = locationRepository.getOne(createExcursionDTO.getLocationId());
		TourGuide tourGuide = tourGuideRepository.getByUserId(getUserId());

		Excursion excursion = new Excursion(createExcursionDTO.getDate(), false,
				createExcursionDTO.getMaxNumberOfPersons(), createExcursionDTO.getPrice(), tourGuide, location,
				vehicle);

		excursionRepository.save(excursion);
		return true;
	}

	private Vehicle getVehicle(CreateExcursionDTO createExcursionDTO) {
		List<Vehicle> vehicles = vehicleRepository.findAll();

		for (Vehicle vehicle : vehicles) {
			if (vehicle.getMaxNumberOfPersons() >= createExcursionDTO.getMaxNumberOfPersons()) {
				if (isVehicleAvalible(vehicle, createExcursionDTO)) {
					return vehicle;
				}
			}
		}
		return null;
	}

	private Boolean isVehicleAvalible(Vehicle vehicle, CreateExcursionDTO createExcursionDTO) {
		Iterator<Excursion> it = vehicle.getExcursions().iterator();

		while (it.hasNext()) {
			java.sql.Date date = new java.sql.Date(it.next().getDate().getTime());
			if (date.equals(createExcursionDTO.getDate())) {
				return false;
			}
		}
		return true;
	}

	private Long getUserId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		return user.getId();
	}

	private Boolean isAvailable(CreateExcursionDTO createExcursionDTO) {
		Set<Excursion> excurions = locationRepository.getOne(createExcursionDTO.getLocationId()).getExcursions();

		Iterator<Excursion> it = excurions.iterator();
		while (it.hasNext()) {
			if (it.next().getDate().equals(createExcursionDTO.getDate()))
				return false;
		}
		return true;
	}

	@Override
	public List<PastExcursionDTO> getPastExcursions() {
		TourGuide tourGuide = tourGuideRepository.getByUserId(getUserId());
		Iterator<Excursion> it = tourGuide.getExcursions().iterator();
		Set<PastExcursion> pastExcursions = new HashSet<PastExcursion>();
		while (it.hasNext()) {
			pastExcursions.addAll(it.next().getPastExcursions());
		}
		return pastExcursionToDTO(pastExcursions);
	}

	private List<PastExcursionDTO> pastExcursionToDTO(Set<PastExcursion> pastExcursions) {
		Iterator<PastExcursion> it = pastExcursions.iterator();
		List<PastExcursionDTO> pastExcursionDTOs = new ArrayList<PastExcursionDTO>();
		while (it.hasNext()) {
			pastExcursionDTOs.add(new PastExcursionDTO(it.next()));
		}
		return pastExcursionDTOs;
	}

	@Override
	public List<ExcursionDTO> getExcursions() {
		TourGuide tourGuide = tourGuideRepository.getByUserId(getUserId());
		return excursionsToDTO(tourGuide.getExcursions());
	}

	private List<ExcursionDTO> excursionsToDTO(Set<Excursion> excursions) {
		List<ExcursionDTO> excursionDTOs = new ArrayList<ExcursionDTO>();
		Iterator<Excursion> it = excursions.iterator();
		while (it.hasNext()) {
			ExcursionDTO excursionDTO = new ExcursionDTO(it.next());
			excursionDTOs.add(excursionDTO);
		}
		return excursionDTOs;
	}

}
