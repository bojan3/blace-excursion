package com.blace.excursion.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.blace.excursion.dto.CreateLocationDTO;

@Entity
public class Location {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private Boolean deleted;
	@OneToMany(mappedBy = "location")
	private Set<Excursion> excursions;

	public Location() {
		super();
	}

	public Location(String name, String description, Set<Excursion> excursions) {
		super();
		this.name = name;
		this.description = description;
		this.excursions = excursions;
		this.deleted = false;
	}

	public Location(CreateLocationDTO createLocationDTO) {
		this.name = createLocationDTO.getName();
		this.description = createLocationDTO.getDescription();
		this.deleted = false;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Set<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(Set<Excursion> excursions) {
		this.excursions = excursions;
	}

}
