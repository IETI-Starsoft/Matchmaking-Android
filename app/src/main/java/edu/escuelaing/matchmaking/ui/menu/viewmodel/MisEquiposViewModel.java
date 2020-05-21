package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;

public class MisEquiposViewModel extends ViewModel{

    private MutableLiveData<List<Team>> teams;

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private RetrofitNetwork retrofitNetwork;

    private String email;

    private void update() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (teams == null) {
                    teams = new MutableLiveData<>();
                    Call<User> userCall = retrofitNetwork.getUserService().getUser(email);
                    try {
                        User user = userCall.execute().body();
                        String id = user.getUserId();
                        Call<List<Team>> teamsCall = retrofitNetwork.getUserService().getTeams(id);
                        teams.postValue(teamsCall.execute().body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public MutableLiveData<List<Team>> getTeams() {
        return teams;
    }

    public void setRetrofitNetwork(RetrofitNetwork retrofitNetwork) {
        this.retrofitNetwork = retrofitNetwork;
        update();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
