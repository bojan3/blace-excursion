package com.blace.excursion.dto;

import java.util.Date;

public class VehicleFilter {

    private Date date;

    private Integer maxNumberOfPersons;

    public VehicleFilter(Date date, Integer maxNumberOfPersons) {
        this.date = date;
        this.maxNumberOfPersons = maxNumberOfPersons;
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
}
