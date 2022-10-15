package com.blace.excursion.dto;

import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Location;
import com.blace.excursion.model.Reservation;
import com.blace.excursion.model.TourGuide;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public class ExcursionDTO {
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy.")
    private Date date;
    private Integer maxNumberOfPersons;
    private Integer numberOfPerosns;
    private Integer price;
    private String tourGuideName;
    private List<LocationDTO> locations;

    public ExcursionDTO(Excursion excursion) {
        super();
        this.id = excursion.getId();
        this.date = excursion.getDate();
        this.maxNumberOfPersons = excursion.getMaxNumberOfPersons();
        this.numberOfPerosns = calculateNumberOfPersons(excursion);
        this.price = excursion.getPrice();
        this.tourGuideName = getTourGuideName(excursion.getTourGuide());
        this.locations = locationsToDTO(excursion.getLocations());
    }

    private List<LocationDTO> locationsToDTO(Set<Location> locations) {
        List<LocationDTO> locationDTOs = new ArrayList<>();
        Iterator<Location> it = locations.iterator();
        while (it.hasNext())
            locationDTOs.add(new LocationDTO(it.next()));
        return locationDTOs;
    }

    public ExcursionDTO() {
    }

    private String getTourGuideName(TourGuide tourGuide) {
        return tourGuide.getUser().getFirstName() + tourGuide.getUser().getLastName();
    }

    private Integer calculateNumberOfPersons(Excursion excursion) {
        Integer numberOfPersons = 0;

        Set<Reservation> reservations = excursion.getReservations();

        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            numberOfPersons += it.next().getNumberOfPersons();
        }

        return numberOfPersons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getNumberOfPerosns() {
        return numberOfPerosns;
    }

    public void setNumberOfPerosns(Integer numberOfPerosns) {
        this.numberOfPerosns = numberOfPerosns;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTourGuideName() {
        return tourGuideName;
    }

    public void setTourGuideName(String tourGuideName) {
        this.tourGuideName = tourGuideName;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }

}
