package com.blace.excursion.dto;

import com.blace.excursion.model.Reservation;
import com.blace.excursion.model.ReservationKey;

public class ReservationDTO {

	private ReservationKey id;
	private ExcursionDTO excursionDTO;

	public ReservationDTO(Reservation reservation) {
		super();
		this.id = reservation.getId();
		this.excursionDTO = new ExcursionDTO(reservation.getExcursion());
	}

	public ReservationKey getId() {
		return id;
	}

	public void setId(ReservationKey id) {
		this.id = id;
	}

	public ExcursionDTO getExcursionDTO() {
		return excursionDTO;
	}

	public void setExcursionDTO(ExcursionDTO excursionDTO) {
		this.excursionDTO = excursionDTO;
	}

	@Override
	public String toString() {
		return "ReservationDTO [id=" + id + ", excursionDTO=" + excursionDTO + "]";
	}

}
