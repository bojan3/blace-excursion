package com.blace.excursion.dto;

public class CreateLocationDTO {

    private String name;

    private String description;

    private String email;

    private Integer ticketPrice;

    public CreateLocationDTO(String name, String description, String email, Integer ticketPrice) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.ticketPrice = ticketPrice;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}