package com.blace.excursion.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String text;
	@Column
	private Integer rate;
	@JsonIgnore
	@ManyToMany(mappedBy = "likedComments")
	private Set<Client> clients;
	@ManyToOne
	private PastExcursion pastExcursion;

	public Comment(String text, Integer rate, Set<Client> clients) {
		super();
		this.text = text;
		this.rate = rate;
		this.clients = clients;
	}

	public Comment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public PastExcursion getPastExcursion() {
		return pastExcursion;
	}

	public void setPastExcursion(PastExcursion pastExcursion) {
		this.pastExcursion = pastExcursion;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", rate=" + rate + ", clients=" + clients + "]";
	}

}
