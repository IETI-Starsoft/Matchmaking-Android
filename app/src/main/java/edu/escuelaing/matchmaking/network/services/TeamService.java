package edu.escuelaing.matchmaking.network.services;

import java.util.ArrayList;

import edu.escuelaing.matchmaking.pojo.Team;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeamService {
    @GET("api/team/captain/{captainId}")
    Call<ArrayList<Team>> getTeam(@Path("captainId") String captainId);
}
