package com.blace.excursion.model;

import javax.persistence.*;

@Entity
public class LocationApproveToken {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "excursion_id", referencedColumnName = "id")
    private Excursion excursion;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    public LocationApproveToken() {
        super();
    }

    public LocationApproveToken(String token, Excursion excursion, Location location) {
        this.token = token;
        this.excursion = excursion;
        this.location = location;
        this.approved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
