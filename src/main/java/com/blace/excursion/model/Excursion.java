package com.blace.excursion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Excursion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy.")
    @Column
    private Date date;
    @Column
    private Boolean cancelled;

    @Column
    private Integer minNumberOfPersons;
    @Column
    private Integer maxNumberOfPersons;
    @Column
    private Integer price;
    @OneToMany(mappedBy = "excursion")
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "excursion")
    private Set<PastExcursion> pastExcursions;
    @ManyToOne
    private TourGuide tourGuide;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "excursion_locations", joinColumns = @JoinColumn(name = "excursion_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Location> locations;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "excursion_vehicles", joinColumns = @JoinColumn(name = "excursion_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> vehicles;

    private Boolean approved;

    @OneToMany(mappedBy = "excursion", fetch = FetchType.EAGER)
    private Set<LocationApproveToken> LocationApproveTokens;

    @Column
    private Integer reservedTicketsNum;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Excursion() {
        super();
    }

    public Excursion(Date date, Boolean cancelled, Integer minNumberOfPersons, Integer maxNumberOfPersons, Integer price, TourGuide tourGuide,
                     List<Location> locations, Set<Vehicle> vehicles) {
        super();
        this.date = date;
        this.cancelled = cancelled;
        this.minNumberOfPersons = minNumberOfPersons;
        this.maxNumberOfPersons = maxNumberOfPersons;
        this.price = price;
        this.tourGuide = tourGuide;
        this.locations = locations;
        this.vehicles = vehicles;
        this.approved = false;
        this.reservedTicketsNum = 0;
    }

}
