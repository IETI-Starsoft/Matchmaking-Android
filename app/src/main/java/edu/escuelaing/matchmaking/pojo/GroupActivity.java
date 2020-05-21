package edu.escuelaing.matchmaking.pojo;

import java.util.Date;

public class GroupActivity extends Activity{

    private String idTeam1;
    private String idTeam2;

    public GroupActivity(Date date, Date publicationDate, int bet, String description, String type, String location, Integer credits, String winner, String loser, State state, String owner,String idTeam1, String idTeam2) {
        super(date, publicationDate, bet, description, type, location, credits, winner, loser, state, owner);
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
    }

    public String getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(String idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public String getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(String idTeam2) {
        this.idTeam2 = idTeam2;
    }
}
