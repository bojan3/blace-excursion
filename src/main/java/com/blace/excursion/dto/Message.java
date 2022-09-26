package com.blace.excursion.dto;

public class Message {

    private String text;

    private Boolean succes;

    public Message(String text, Boolean succes) {
        this.text = text;
        this.succes = succes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

}
