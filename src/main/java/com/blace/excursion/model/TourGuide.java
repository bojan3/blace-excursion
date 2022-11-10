package com.blace.excursion.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TourGuide {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @Column
    private Boolean deleted;
    @OneToMany(mappedBy = "tourGuide")
    private Set<Excursion> excursions;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private Integer version;

    public TourGuide(User user, Set<Excursion> excursions) {
        super();
        this.user = user;
        this.excursions = excursions;
        this.deleted = false;
    }

    public TourGuide() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
