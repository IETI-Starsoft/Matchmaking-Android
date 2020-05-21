package edu.escuelaing.matchmaking.network.services;
import edu.escuelaing.matchmaking.pojo.Activity;
import edu.escuelaing.matchmaking.pojo.GroupActivity;
import edu.escuelaing.matchmaking.pojo.IndividualActivity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ActivityService {
    @POST("/api/activities")
    Call<Activity> createIndividualActivity(@Body IndividualActivity activity);

    @POST("/api/activities")
    Call<Activity> createGroupActivity(@Body GroupActivity activity);
}
