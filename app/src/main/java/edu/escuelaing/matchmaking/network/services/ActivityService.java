package edu.escuelaing.matchmaking.network.services;
import java.util.List;

import edu.escuelaing.matchmaking.pojo.Activity;
import edu.escuelaing.matchmaking.pojo.GroupActivity;
import edu.escuelaing.matchmaking.pojo.IndividualActivity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ActivityService {
    @POST("/api/activities")
    Call<Activity> createIndividualActivity(@Body IndividualActivity activity);

    @POST("/api/activities")
    Call<Activity> createGroupActivity(@Body GroupActivity activity);

    @GET("/api/activities")
    Call<List<Activity>> getActivities();

}
