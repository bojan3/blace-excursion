package com.blace.excursion.dto;

import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Location;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class LocationDTO {

    private Long id;
    private String name;
    private String description;
    private Boolean canDelete;

    private Integer ticketPrice;

    public LocationDTO(Location location) {
        super();
        this.id = location.getId();
        this.name = location.getName();
        this.description = location.getDescription();
        this.canDelete = checkCanDelete(location.getExcursions());
        this.ticketPrice = location.getTicketPrice();
    }

    private Boolean checkCanDelete(Set<Excursion> excursions) {
        Iterator<Excursion> it = excursions.iterator();
        while (it.hasNext()) {
            if (it.next().getDate().before(new Date()))
                return false;
        }
        return true;
    }

    public LocationDTO() {
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

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", canDelete=" + canDelete +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
