package edu.escuelaing.matchmaking.network.services;

import java.util.List;

import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.pojo.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("api/users/email/{userEmail}")
    Call<User> getUser(@Path("userEmail") String userEmail);

    @PUT("api/users")
    Call<User> updateUser(@Body User user);

    @GET("api/users/id/{userId}/teams")
    Call<List<Team>> getTeams(@Path("userId") String userId);


}
