package com.blace.excursion.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "client")
    private Set<PastExcursion> pastExcursions;

    public Client(User user) {
        super();
        this.user = user;
    }

    public Client() {
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

}
