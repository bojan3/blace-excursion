package com.blace.excursion.dto;

import com.blace.excursion.dto.excursion.ExcursionDTO;
import com.blace.excursion.model.Reservation;

public class ReservationDTO {
    private Long id;
    private ExcursionDTO excursion;

    private Integer numberOfPersons;

    public ReservationDTO(Reservation reservation) {
        super();
        this.id = reservation.getId();
        this.excursion = new ExcursionDTO();
        this.numberOfPersons = reservation.getNumberOfPersons();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExcursionDTO getExcursion() {
        return excursion;
    }

    public void setExcursion(ExcursionDTO excursion) {
        this.excursion = excursion;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }
}
