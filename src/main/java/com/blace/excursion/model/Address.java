package com.blace.excursion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@Column(name = "user_id")
	private Long id;
	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;
	@Column(length = 31)
	private String country;
	@Column(length = 31)
	private String city;
	@Column(length = 31)
	private String street;
	@Column(length = 6)
	private String number;

	public Address(User user, String country, String city, String street, String number) {
		super();
		this.user = user;
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
	}

	public Address() {
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", user=" + user + ", country=" + country + ", city=" + city + ", street=" + street
				+ ", number=" + number + "]";
	}

}
