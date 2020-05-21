package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.CheckBox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.Activity;
import edu.escuelaing.matchmaking.pojo.GroupActivity;
import edu.escuelaing.matchmaking.pojo.IndividualActivity;
import edu.escuelaing.matchmaking.pojo.State;
import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;
import retrofit2.Response;

public class CrearMatchViewModel extends ViewModel{

    private String userId;
    private String activity;
    private String location;
    private String description;
    private String date;
    private String time;
    private Integer indexTeam;
    private Integer bet;
    private String participants;
    private RetrofitNetwork retrofitNetwork;


    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );
    private ArrayList<Team> userTeams;
    private String email;

    public CrearMatchViewModel() {
    }



    public void setRetrofitNetwork(RetrofitNetwork retrofitNetwork) {
        this.retrofitNetwork = retrofitNetwork;
        update();
    }



    private void update() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<User> call = retrofitNetwork.getUserService().getUser(email);
                try {
                    Response<User> response = call.execute();
                    User user = response.body();
                    setUserId(user.getUserId());
                    Call<ArrayList<Team>> call2 = retrofitNetwork.getTeamService().getTeam(user.getUserId());
                    Response<ArrayList<Team>> response2 = call2.execute();
                    ArrayList<Team> teams = response2.body();
                    setUserTeams(teams);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void postIndividualActivity(final IndividualActivity activity){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<Activity> call = retrofitNetwork.getActivityService().createIndividualActivity(activity);

                System.out.println("activity " + activity.toString());
                try {
                    Response<Activity> response = call.execute();
                    System.out.println("response2 " + response);
                    Activity activity = response.body();
                    System.out.println("actividad " + activity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void postGroupActivity(final GroupActivity activity){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<Activity> call = retrofitNetwork.getActivityService().createGroupActivity(activity);
                try {
                    Response<Activity> response = call.execute();
                    System.out.println("response " + response);
                    Activity activity = response.body();
                    System.out.println("actividad " + activity);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void createIndividualActivity(){
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = this.date + 'T' +this.time + ":00";
        String publicationDate = "";
        try {
            publicationDate = (simpleDateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        IndividualActivity activity = new IndividualActivity("IndividualActivity",date,publicationDate,this.bet,this.description,
                this.activity,this.location,0,null,null, State.Available,getUserId(),getUserId(),null);
        postIndividualActivity(activity);
    }

    public void createGroupActivity(){
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = this.date + 'T' +this.time + ":00";
        String publicationDate = "";
        try {
            publicationDate = (simpleDateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
       GroupActivity activity = new GroupActivity("GroupActivity",date,publicationDate,this.bet,this.description,
                this.activity,this.location,0,null,null, State.Available,getUserId(),userTeams.get(indexTeam).getTeamId(),null);
        postGroupActivity(activity);
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Team> getUserTeams() {
        return userTeams;
    }


    public String getActivity() {
        return activity;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Integer getBet() {
        return bet;
    }

    public String getParticipants() {
        return participants;
    }
    public void setUserTeams(ArrayList<Team> userTeams) {
        this.userTeams = userTeams;
    }

    public Integer getIndexTeam() {
        return indexTeam;
    }

    public void setIndexTeam(Integer indexTeam) {
        this.indexTeam = indexTeam;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBet(String bet) {
        if (bet.equals("")) this.bet = 0;
        else this.bet = Integer.valueOf(bet);
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
