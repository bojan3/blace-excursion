package com.blace.excursion.dto;

import com.blace.excursion.model.Client;
import com.blace.excursion.model.Comment;
import com.blace.excursion.model.User;

import java.util.Iterator;

public class CommentDTO {

    private Long id;
    private String clientName;
    private String text;
    private Integer rate;
    private Integer likeCount;
    private Boolean iLiked;

    public CommentDTO(Comment comment, Long accountId) {
        super();
        this.id = comment.getId();
        this.clientName = getClientName(comment);
        this.text = comment.getText();
        this.rate = comment.getRate();
        this.likeCount = countLikes(comment);
        this.iLiked = isILiked(comment, accountId);
    }

    private Integer countLikes(Comment comment) {
        return comment.getClients().size();
    }

    private String getClientName(Comment comment) {
        User user = comment.getPastExcursion().getClient().getUser();
        return user.getLastName() + " " + user.getFirstName();
    }

    private Boolean isILiked(Comment comment, Long accountId) {
        Iterator<Client> it = comment.getClients().iterator();
        while (it.hasNext())
            if (it.next().getId() == accountId)
                return true;
        return false;
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Boolean getiLiked() {
        return iLiked;
    }

    public void setiLiked(Boolean iLiked) {
        this.iLiked = iLiked;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "CommentDTO [id=" + id + ", text=" + text + ", rate=" + rate + ", likeCount=" + likeCount + ", iLiked="
                + iLiked + "]";
    }

}
