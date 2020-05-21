package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import android.view.View;
import android.widget.CheckBox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class CrearMatchViewModel extends ViewModel{

    private String retador;
    private String activity;
    private String location;
    private String description;
    private String date;
    private String time;
    private Integer bet;
    private boolean participants;


    public CrearMatchViewModel() {
    }


    public boolean isParticipants() {
        return participants;
    }

    public void setParticipants(boolean participants) {
        this.participants = participants;
    }

    public String getRetador() {
        return retador;
    }

    public void setRetador(String retador) {
        this.retador = retador;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

}
