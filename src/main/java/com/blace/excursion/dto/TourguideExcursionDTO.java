package com.blace.excursion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class TourguideExcursionDTO {
    @JsonFormat(pattern = "dd.MM.yyyy.")
    private Date date;

    private Boolean approved;

    private Integer maxNumOfPerson;

    private Integer numOfPerson;

    private List<String> locationNames;

    private String mealName;

    private Integer price;

//    private List<String> vehicles;

//    private Integer costs;

//    private Integer earnings;


    public TourguideExcursionDTO() {
    }

    public TourguideExcursionDTO(Date date, Boolean approved, Integer maxNumOfPerson, List<String> locationNames, String mealName, Integer price, Integer numOfPerson) {
        this.date = date;
        this.approved = approved;
        this.maxNumOfPerson = maxNumOfPerson;
        this.locationNames = locationNames;
        this.mealName = mealName;
        this.price = price;
        this.numOfPerson = numOfPerson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getMaxNumOfPerson() {
        return maxNumOfPerson;
    }

    public void setMaxNumOfPerson(Integer maxNumOfPerson) {
        this.maxNumOfPerson = maxNumOfPerson;
    }

//    public Integer getNumOfPerson() {
//        return numOfPerson;
//    }
//
//    public void setNumOfPerson(Integer numOfPerson) {
//        this.numOfPerson = numOfPerson;
//    }

    public List<String> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<String> locationNames) {
        this.locationNames = locationNames;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

//    public List<String> getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(List<String> vehicles) {
//        this.vehicles = vehicles;
//    }
//
//    public Integer getCosts() {
//        return costs;
//    }
//
//    public void setCosts(Integer costs) {
//        this.costs = costs;
//    }
//
//    public Integer getEarnings() {
//        return earnings;
//    }
//
//    public void setEarnings(Integer earnings) {
//        this.earnings = earnings;
//    }


    public Integer getNumOfPerson() {
        return numOfPerson;
    }

    public void setNumOfPerson(Integer numOfPerson) {
        this.numOfPerson = numOfPerson;
    }
}
