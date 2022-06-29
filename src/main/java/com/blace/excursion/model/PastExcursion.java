package com.blace.excursion.model;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

@Entity
public class PastExcursion {

	@EmbeddedId
	private PastExcursionKey id;
	@ManyToOne
	@MapsId("clientId")
	@JoinColumn(name = "client_id")
	private Client client;
	@ManyToOne
	@MapsId("excursionId")
	@JoinColumn(name = "excursion_id")
	private Excursion excursion;
	@OneToMany(mappedBy = "pastExcursion")
	private Set<Comment> comments;
	private Integer numberOfPersons;

	public PastExcursion(PastExcursionKey id, Client client, Excursion excursion, Set<Comment> comments,Integer numberOfPersons) {
		super();
		this.id = id;
		this.client = client;
		this.excursion = excursion;
		this.comments = comments;
		this.numberOfPersons = numberOfPersons;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public PastExcursion() {
		super();
	}

	public PastExcursionKey getId() {
		return id;
	}

	public void setId(PastExcursionKey id) {
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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PastExcursion [id=" + id + ", client=" + client + ", excursion=" + excursion + ", comments=" + comments
				+ "]";
	}

}
