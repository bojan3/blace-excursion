package com.blace.excursion.dto;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Vehicle;

public class VehicleDTO {

	private String name;

	private Integer maxNumberOfPersons;
	
	private Boolean canDelete;

	public VehicleDTO(Vehicle vehicle) {
		this.name = vehicle.getName();
		this.maxNumberOfPersons = vehicle.getMaxNumberOfPersons();
		this.canDelete = checkCanDelete(vehicle.getExcursions());
		
	}
	
	private Boolean checkCanDelete(Set<Excursion> excursions) {
		Iterator<Excursion> it = excursions.iterator();
		while (it.hasNext()) {
			if (it.next().getDate().before(new Date()))
				return false;
		}
		return true;
	}

}
