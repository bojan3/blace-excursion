package com.blace.excursion.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String email;

    private String address;

    private String phoneNumber;

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<Meal> meals;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Set<Meal> getMeals() {
        return meals;
    }
}
