package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.Activity;
import edu.escuelaing.matchmaking.pojo.GroupActivity;
import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;

public class BuscarMatchViewModel extends ViewModel{

    private MutableLiveData<List<Activity>> activities;

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private RetrofitNetwork retrofitNetwork;

    private String email;

    public BuscarMatchViewModel() {

    }

    private void update() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (activities == null) {
                    activities = new MutableLiveData<>();
                    Call<List<Activity>> activitiesCall = retrofitNetwork.getActivityService().getActivities();
                    try {
                        List<Activity> activityList = activitiesCall.execute().body();
                        for (Activity activity : activityList) {
                            Call<User> userCall =  retrofitNetwork.getUserService().getUserById(activity.getOwner());
                            User user = userCall.execute().body();
                            String ret = user.getFirstName() + " " + user.getLastName();
                            activity.setOwner(ret);
                        }
                        activities.postValue(activityList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public MutableLiveData<List<Activity>> getActivities() {
        return activities;
    }

    public void setRetrofitNetwork(RetrofitNetwork retrofitNetwork) {
        this.retrofitNetwork = retrofitNetwork;
        update();
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
