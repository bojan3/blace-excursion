package com.blace.excursion.dto;

import java.util.Date;
import java.util.List;

public class CreateExcursionDTO {

    private Date date;
    private Integer maxNumberOfPersons;
    private Integer price;
    private List<Long> locationIds;

    private Integer minNumberOfPersons;

    private Long mealId;

    private List<Long> vehicleIds;

    public CreateExcursionDTO() {
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

    public List<Long> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<Long> locationIds) {
        this.locationIds = locationIds;
    }

    @Override
    public String toString() {
        return "CreateExcursionDTO [date=" + date + ", maxNumberOfPersons=" + maxNumberOfPersons + ", price=" + price
                + ", locationIds=" + locationIds + "]";
    }

    public Integer getMinNumberOfPersons() {
        return minNumberOfPersons;
    }

    public void setMinNumberOfPersons(Integer minNumberOfPersons) {
        this.minNumberOfPersons = minNumberOfPersons;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public List<Long> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<Long> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
