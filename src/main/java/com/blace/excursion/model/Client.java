package com.blace.excursion.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Client {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private User user;
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;
	@OneToMany(mappedBy = "client")
	private Set<PastExcursion> pastExcursions;
	@ManyToMany
	@JoinTable(name = "liked_commnets", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private Set<Comment> likedComments;

	public Client(User user, Set<Reservation> reservations, Set<PastExcursion> pastExcursions,
			Set<Comment> likedComments) {
		super();
		this.user = user;
		this.reservations = reservations;
		this.pastExcursions = pastExcursions;
		this.likedComments = likedComments;
	}

	public Client() {
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

	public Set<Comment> getLikedComments() {
		return likedComments;
	}

	public void setLikedComments(Set<Comment> likedComments) {
		this.likedComments = likedComments;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", user=" + user + ", reservations=" + reservations + ", pastExcursions="
				+ pastExcursions + ", likedComments=" + likedComments + "]";
	}

}
