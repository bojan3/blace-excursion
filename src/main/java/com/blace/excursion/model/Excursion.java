package com.blace.excursion.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
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
    private Set<Location> locations;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "excursion_vehicles", joinColumns = @JoinColumn(name = "excursion_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> vehicles;

    private Boolean approved;

    @OneToMany(mappedBy = "excursion", fetch = FetchType.EAGER)
    private Set<LocationApproveToken> LocationApproveTokens;

    @Column
    private Integer reservatedTicketsNum;

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
                     Set<Location> locations, Set<Vehicle> vehicles) {
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
        this.reservatedTicketsNum = 0;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<PastExcursion> getPastExcursions() {
        return pastExcursions;
    }

    public void setPastExcursions(Set<PastExcursion> pastExcursions) {
        this.pastExcursions = pastExcursions;
    }

    public TourGuide getTourGuide() {
        return tourGuide;
    }

    public void setTourGuide(TourGuide tourGuide) {
        this.tourGuide = tourGuide;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
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

    public Boolean notPass() {
        return this.date.after(new Date());
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getMinNumberOfPersons() {
        return minNumberOfPersons;
    }

    public void setMinNumberOfPersons(Integer minNumberOfPersons) {
        this.minNumberOfPersons = minNumberOfPersons;
    }

    public Set<LocationApproveToken> getLocationApproveTokens() {
        return LocationApproveTokens;
    }

    public void setLocationApproveTokens(Set<LocationApproveToken> locationApproveTokens) {
        LocationApproveTokens = locationApproveTokens;
    }

    public Integer getReservatedTicketsNum() {
        return reservatedTicketsNum;
    }

    public void setReservatedTicketsNum(Integer reservatedTicketsNum) {
        this.reservatedTicketsNum = reservatedTicketsNum;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Excursion [id=" + id + ", date=" + date + ", cancelled=" + cancelled + ", maxNumberOfPersons="
                + maxNumberOfPersons + ", price=" + price + ", reservations=" + reservations + ", pastExcursions="
                + pastExcursions + ", tourGuide=" + tourGuide + ", locations=" + locations + ", vehicles=" + vehicles
                + "]";
    }

}
