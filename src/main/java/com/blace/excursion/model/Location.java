package com.blace.excursion.model;

import com.blace.excursion.dto.CreateLocationDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Boolean deleted;
    @ManyToMany(mappedBy = "locations")
    private Set<Excursion> excursions;

    private String email;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private Set<LocationApproveToken> LocationApproveTokens;

    @Column
    private Integer ticketPrice;

    public Location() {
        super();
    }

    public Location(String name, String description, Set<Excursion> excursions) {
        super();
        this.name = name;
        this.description = description;
        this.excursions = excursions;
        this.deleted = false;
    }

    public Location(CreateLocationDTO createLocationDTO) {
        this.name = createLocationDTO.getName();
        this.description = createLocationDTO.getDescription();
        this.email = createLocationDTO.getEmail();
        this.deleted = false;
        this.ticketPrice = createLocationDTO.getTicketPrice();
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(Set<Excursion> excursions) {
        this.excursions = excursions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<LocationApproveToken> getLocationApproveTokens() {
        return LocationApproveTokens;
    }

    public void setLocationApproveTokens(Set<LocationApproveToken> locationApproveTokens) {
        LocationApproveTokens = locationApproveTokens;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
