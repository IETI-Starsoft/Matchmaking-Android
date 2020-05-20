package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;
import retrofit2.Response;

public class EditarPerfilViewModel extends ViewModel {

    private MutableLiveData<String> userName;

    private MutableLiveData<String> userBio;

    private RetrofitNetwork retrofitNetwork;

    private String userEmail;

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public EditarPerfilViewModel() {
        userName = new MutableLiveData<>();
        userBio = new MutableLiveData<>();
    }

    public LiveData<String> getUserNameText() {
        return userName;
    }

    public LiveData<String> getUserBioText() {
        return userBio;
    }

    public void setRetrofitNetwork(RetrofitNetwork retrofitNetwork) {
        this.retrofitNetwork = retrofitNetwork;
        update();
    }

    private void update() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<User> call = retrofitNetwork.getUserService().getUser(userEmail);
                try {
                    Response<User> response = call.execute();
                    User user = response.body();
                    userName.postValue(user.getFirstName() + " " + user.getLastName());
                    userBio.postValue("Bio"); // CHANGE
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateUserData(String name, String bio) throws IOException {
        Call<User> callUser = retrofitNetwork.getUserService().getUser(userEmail);
        User user = callUser.execute().body();
        if (!name.isEmpty()){
            user.setFirstName(name);
            user.setLastName("");
        }
        if (!bio.isEmpty()){
            //user.setBio
        }
        Call<User> callUpdate = retrofitNetwork.getUserService().updateUser(user);
        Response<User> response = callUpdate.execute();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
