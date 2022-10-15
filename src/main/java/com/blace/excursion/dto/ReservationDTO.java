package com.blace.excursion.dto;

import com.blace.excursion.model.Reservation;

public class ReservationDTO {

    //	private ReservationKey id;
    private Long id;
    private ExcursionDTO excursion;

    public ReservationDTO(Reservation reservation) {
        super();
        this.id = reservation.getId();
        this.excursion = new ExcursionDTO(reservation.getExcursion());
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

}
