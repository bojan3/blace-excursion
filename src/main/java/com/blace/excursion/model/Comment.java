package com.blace.excursion.model;

import javax.persistence.*;
import java.util.Set;

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
    //    @JsonIgnore
//    @ManyToMany(mappedBy = "likedComments")
//    private Set<Client> clients;
    @ManyToOne
    private PastExcursion pastExcursion;

    public Comment(String text, Integer rate, Set<Client> clients) {
        super();
        this.text = text;
        this.rate = rate;
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


    public PastExcursion getPastExcursion() {
        return pastExcursion;
    }

    public void setPastExcursion(PastExcursion pastExcursion) {
        this.pastExcursion = pastExcursion;
    }


}
