package com.blace.excursion.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Excursion {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd.MM.yyyy.")
	@Column
	private Date date;
	@Column
	private Boolean cancelled;
	@Column
	private Integer maxNumberOfPersons;
	@Column
	private Integer price;
	@OneToMany(mappedBy = "excursion")
	private Set<Reservation> reservations;
	@OneToMany(mappedBy = "excursion")
	private Set<PastExcursion> pastExcursions;
	@ManyToOne
	private TourGuide tourGuide;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "excursion_locations", joinColumns = @JoinColumn(name = "excursion_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
	private Set<Location> locations;
	@ManyToOne
	private Vehicle vehicle;

	public Excursion() {
		super();
	}

	public Excursion(Date date, Boolean cancelled, Integer maxNumberOfPersons, Integer price, TourGuide tourGuide,
			Set<Location> locations, Vehicle vehicle) {
		super();
		this.date = date;
		this.cancelled = cancelled;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.price = price;
		this.tourGuide = tourGuide;
		this.locations = locations;
		this.vehicle = vehicle;
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

	public TourGuide getTourGuide() {
		return tourGuide;
	}

	public void setTourGuide(TourGuide tourGuide) {
		this.tourGuide = tourGuide;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean notPass() {
		return this.date.after(new Date());
	}

	@Override
	public String toString() {
		return "Excursion [id=" + id + ", date=" + date + ", cancelled=" + cancelled + ", maxNumberOfPersons="
				+ maxNumberOfPersons + ", price=" + price + ", reservations=" + reservations + ", pastExcursions="
				+ pastExcursions + ", tourGuide=" + tourGuide + ", locations=" + locations + ", vehicle=" + vehicle
				+ "]";
	}

}
