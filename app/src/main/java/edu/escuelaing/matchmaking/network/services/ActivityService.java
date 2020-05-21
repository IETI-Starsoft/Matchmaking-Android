package edu.escuelaing.matchmaking.network.services;


import edu.escuelaing.matchmaking.pojo.Activity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ActivityService {
    @POST("/api/activities")
    Call<Activity> createActivity(@Body Activity activity);
}
