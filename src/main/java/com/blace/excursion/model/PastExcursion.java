package com.blace.excursion.model;

import javax.persistence.*;

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
    private Integer numberOfPersons;

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

}
