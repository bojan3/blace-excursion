package com.blace.excursion.dto;

import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Vehicle;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class VehicleDTO {

    private Long id;

    private String name;

    private Integer maxNumberOfPersons;

    private Boolean canDelete;

    private Integer pricePerDay;

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.maxNumberOfPersons = vehicle.getMaxNumberOfPersons();
        this.canDelete = checkCanDelete(vehicle.getExcursions());
        this.pricePerDay = vehicle.getPricePerDay();
    }

    private Boolean checkCanDelete(Set<Excursion> excursions) {
        Iterator<Excursion> it = excursions.iterator();
        while (it.hasNext()) {
            if (it.next().getDate().before(new Date()))
                return false;
        }
        return true;
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

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
