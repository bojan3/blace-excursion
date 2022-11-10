package com.blace.excursion.dto;

import java.util.List;

public class RestaurantDTO {

    private Long id;
    private String name;

    private List<MealDTO> meals;

    public RestaurantDTO(Long id, String name, List<MealDTO> meals) {
        this.id = id;
        this.name = name;
        this.meals = meals;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MealDTO> getMeals() {
        return meals;
    }
}
