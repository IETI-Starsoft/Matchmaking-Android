package edu.escuelaing.matchmaking.pojo;

import java.util.Date;

public class IndividualActivity extends Activity{
    private String idPlayer1;
    private String idPlayer2;

    public IndividualActivity(Date date, Date publicationDate, int bet, String description, String type, String location, Integer credits, String winner, String loser, State state, String owner,String idPlayer1, String idPlayer2) {
        super(date, publicationDate, bet, description, type, location, credits, winner, loser, state, owner);
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
    }


    public String getIdPlayer1() {
        return idPlayer1;
    }

    public void setIdPlayer1(String idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public String getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(String idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }
}
