package com.blace.excursion.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TourGuide {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private User user;
	@OneToMany(mappedBy = "tourGuide")
	private Set<Excursion> excursions;
	

	public TourGuide(User user, Set<Excursion> excursions) {
		super();
		this.user = user;
		this.excursions = excursions;
	}

	public TourGuide() {
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

	public Set<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(Set<Excursion> excursions) {
		this.excursions = excursions;
	}

	@Override
	public String toString() {
		return "TourGuide [id=" + id + ", user=" + user + ", excursions=" + excursions + "]";
	}

}
