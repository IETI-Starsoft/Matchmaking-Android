package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;
import retrofit2.Response;


public class PerfilViewModel extends ViewModel {

    private MutableLiveData<String> userNameText;

    private MutableLiveData<Bitmap> userImage;

    private MutableLiveData<Integer> userRating;

    private MutableLiveData<String> userTotalFriends;

    private MutableLiveData<String> userCredits;

    private MutableLiveData<String> userTotalTeams;

    private RetrofitNetwork retrofitNetwork;

    private String userEmail;

    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public PerfilViewModel() {
        userNameText = new MutableLiveData<>();
        userImage = new MutableLiveData<>();
        userRating = new MutableLiveData<>();
        userTotalFriends = new MutableLiveData<>();
        userTotalTeams = new MutableLiveData<>();
        userCredits = new MutableLiveData<>();
        userNameText.setValue("Loading ...");
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
                    userNameText.postValue(user.getFirstName() + " " +  user.getLastName());
                    userRating.postValue(user.getRating());
                    userTotalFriends.postValue(String.valueOf(user.getFriends().size()));
                    userTotalTeams.postValue(String.valueOf(user.getTeams().size()));
                    userCredits.postValue(String.valueOf(user.getCredits()));
                    URL url = new URL(RetrofitNetwork.BASE_URL + "files/" + user.getUserId() + "/" + user.getImageFileURL());
                    Bitmap bmp = null;
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    userImage.postValue(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LiveData<String> getText() {
        return userNameText;
    }

    public LiveData<Bitmap> getUrl(){
        return userImage;
    }

    public void setEmail(String email) {
        userEmail = email;
    }

    public LiveData<Integer> getUserRating() {
        return userRating;
    }

    public MutableLiveData<String> getUserTotalFriends() {
        return userTotalFriends;
    }

    public MutableLiveData<String> getUserCredits() {
        return userCredits;
    }

    public MutableLiveData<String> getUserTotalTeams() {
        return userTotalTeams;
    }
}
