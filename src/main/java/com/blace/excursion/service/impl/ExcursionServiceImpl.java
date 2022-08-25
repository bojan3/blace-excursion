package com.blace.excursion.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blace.excursion.dto.CommentDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.model.Comment;
import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Location;
import com.blace.excursion.model.PastExcursion;
import com.blace.excursion.model.User;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.service.ExcursionService;

import net.bytebuddy.jar.asm.commons.Remapper;

@Service
public class ExcursionServiceImpl implements ExcursionService {

	private ExcursionRepository excursionRepository;
	private UserRepository userRepository;
	private LocationRepository locationRepository;

	@Autowired
	public ExcursionServiceImpl(ExcursionRepository excursionRepository, UserRepository userRepository,
			LocationRepository locationRepository) {
		this.excursionRepository = excursionRepository;
		this.userRepository = userRepository;
		this.locationRepository = locationRepository;
	}

	@Override
	public List<ExcursionDTO> getExcursions() {
		List<Excursion> excursions = excursionRepository.findAllNotPass();
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

	@Override
	public List<CommentDTO> getComments(Long excursionId) {
		Excursion excursion = excursionRepository.getOne(excursionId);
		List<Comment> comments = getCommentsFromLocation(excursion.getLocation());
		return commentsToDTO(comments, getUserId());

	}

	private Long getUserId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		return user.getId();
	}

	private List<Comment> getCommentsFromLocation(Location location) {
		Set<Excursion> excursionsOnLocation = location.getExcursions();
		List<Comment> comments = new ArrayList<Comment>();
		Iterator<Excursion> it = excursionsOnLocation.iterator();
		while (it.hasNext()) {
			comments.addAll(getCommentsFromPastExcursion(it.next().getPastExcursions()));
		}
		return comments;
	}

	private List<Comment> getCommentsFromPastExcursion(Set<PastExcursion> pastExcursions) {
		Iterator<PastExcursion> it = pastExcursions.iterator();
		List<Comment> comments = new ArrayList<Comment>();
		while (it.hasNext()) {
			comments.addAll(it.next().getComments());
		}
		return comments;
	}

	private List<CommentDTO> commentsToDTO(List<Comment> comments, Long accountId) {
		List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
		for (Comment comment : comments) {
			CommentDTO commentDTO = new CommentDTO(comment, accountId);
			commentDTOs.add(commentDTO);
		}
		return commentDTOs;
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
}
