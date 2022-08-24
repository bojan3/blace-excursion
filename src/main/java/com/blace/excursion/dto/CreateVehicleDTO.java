package com.blace.excursion.dto;

public class CreateVehicleDTO {

	private String name;

	private Integer maxNumberOfPersons;

	public CreateVehicleDTO(String name, Integer maxNumberOfPersons) {
		super();
		this.name = name;
		this.maxNumberOfPersons = maxNumberOfPersons;
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

}
