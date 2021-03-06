package com.blace.excursion.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vehicle {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private Integer maxNumberOfPersons;
	@OneToMany(mappedBy = "vehicle")
	private Set<Excursion> excursions;

	public Vehicle(String name, Integer maxNumberOfPersons, Set<Excursion> excursions) {
		super();
		this.name = name;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.excursions = excursions;
	}

	public Vehicle() {
		super();
	}

	public Set<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(Set<Excursion> excursions) {
		this.excursions = excursions;
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

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", maxNumberOfPersons=" + maxNumberOfPersons + ", excursions="
				+ excursions + "]";
	}

}
