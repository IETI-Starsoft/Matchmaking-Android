package edu.escuelaing.matchmaking.network.services;

import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("api/users/email/{userEmail}")
    Call<User> getUser(@Path("userEmail") String userEmail);

}
