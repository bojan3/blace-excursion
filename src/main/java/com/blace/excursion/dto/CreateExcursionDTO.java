package com.blace.excursion.dto;

import java.util.Date;

public class CreateExcursionDTO {

	private Date date;
	private Integer maxNumberOfPersons;
	private Integer price;
	private Long locationId;

	public CreateExcursionDTO() {
	}
	
	public CreateExcursionDTO(Date date, Integer maxNumberOfPersons, Integer price, Long locationId) {
		super();
		this.date = date;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.price = price;
		this.locationId = locationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "CreateExcursionDTO [date=" + date + ", maxNumberOfPersons=" + maxNumberOfPersons + ", price=" + price
				+ ", locationId=" + locationId + "]";
	}

}
