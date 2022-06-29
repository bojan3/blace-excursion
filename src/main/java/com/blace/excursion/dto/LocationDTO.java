package com.blace.excursion.dto;

import com.blace.excursion.model.Location;

public class LocationDTO {
	
	private Long id;
	private String name;
	private String description;

	public LocationDTO(Location location) {
		super();
		this.id = location.getId();
		this.name = location.getName();
		this.description = location.getDescription();
	}

	public LocationDTO() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LocationDTO [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
