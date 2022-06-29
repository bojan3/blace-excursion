package com.blace.excursion.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.util.backoff.BackOff;

@Entity
public class Reservation {

	@EmbeddedId
	private ReservationKey id;
	@ManyToOne
	@MapsId("clientId")
	@JoinColumn(name = "client_id")
	private Client client;
	@ManyToOne
	@MapsId("excursionId")
	@JoinColumn(name = "excursion_id")
	private Excursion excursion;
	@Column
	private Integer numberOfPersons;
	@Column
	private Boolean cancelled;

	public Reservation(ReservationKey reservationKey, Client client, Excursion excursion, Integer numberOfPersons) {
		super();
		this.id = reservationKey;
		this.client = client;
		this.excursion = excursion;
		this.numberOfPersons = numberOfPersons;
		this.cancelled = false;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Reservation() {
		super();
	}

	public ReservationKey getId() {
		return id;
	}

	public void setId(ReservationKey id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", client=" + client + ", excursion=" + excursion + ", numberOfPersons="
				+ numberOfPersons + ", cancelled=" + cancelled + "]";
	}

}
