package edu.escuelaing.matchmaking.pojo;

import java.util.Date;

public abstract class Activity {
    private Date date;
    private Date publicationDate;
    private int bet;
    private String description;
    private String type;
    private String location;
    private Integer credits;
    private String winner;
    private String loser;
    private State state;
    private String owner;




    public Activity(Date date, Date publicationDate, int bet, String description, String type, String location, Integer credits, String winner, String loser, State state, String owner) {
        this.date = date;
        this.publicationDate = publicationDate;
        this.bet = bet;
        this.description = description;
        this.type = type;
        this.location = location;
        this.credits = credits;
        this.winner = winner;
        this.loser = loser;
        this.state = state;
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
