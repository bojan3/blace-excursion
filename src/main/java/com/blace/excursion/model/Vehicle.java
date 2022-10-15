package com.blace.excursion.model;

import com.blace.excursion.dto.CreateVehicleDTO;

import javax.persistence.*;
import java.util.Set;

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
    @Column
    private Boolean deleted;

    @ManyToMany(mappedBy = "vehicles")
    private Set<Excursion> excursions;

    private Integer pricePerDay;

    public Vehicle() {
        super();
    }

    public Vehicle(String name, Integer maxNumberOfPersons, Set<Excursion> excursions, Integer pricePerDay) {
        super();
        this.name = name;
        this.maxNumberOfPersons = maxNumberOfPersons;
        this.excursions = excursions;
        this.deleted = false;
        this.pricePerDay = pricePerDay;
    }

    public Vehicle(CreateVehicleDTO createVehicleDTO) {
        this.name = createVehicleDTO.getName();
        this.maxNumberOfPersons = createVehicleDTO.getMaxNumberOfPersons();
        this.deleted = false;
        //TODO: prive per day add when you create the vehicle
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", maxNumberOfPersons=" + maxNumberOfPersons +
                '}';
    }
}
