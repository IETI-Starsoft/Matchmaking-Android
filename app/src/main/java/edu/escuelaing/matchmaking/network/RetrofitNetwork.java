package edu.escuelaing.matchmaking.network;

import java.io.IOException;

import edu.escuelaing.matchmaking.network.services.ActivityService;
import edu.escuelaing.matchmaking.network.services.AuthService;
import edu.escuelaing.matchmaking.network.services.TeamService;
import edu.escuelaing.matchmaking.network.services.UserService;
import edu.escuelaing.matchmaking.pojo.Team;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    public static final String BASE_URL = "http://matchmaking-iback.herokuapp.com/";

    private AuthService authService;

    private UserService userService;

    private TeamService teamService;

    private ActivityService activityService;

    public RetrofitNetwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( BASE_URL )
                .addConverterFactory(GsonConverterFactory.create()).build();

        authService = retrofit.create(AuthService.class);
    }

    public RetrofitNetwork(final String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain)
                    throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder().header("Accept", "application/json").header("Authorization",
                        "Bearer "
                                + token).method(
                        original.method(), original.body()).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
                        httpClient.build()).build();

        userService = retrofit.create(UserService.class);
        teamService = retrofit.create(TeamService.class);
        activityService = retrofit.create(ActivityService.class);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public UserService getUserService() {
        return userService;
    }

    public TeamService getTeamService() {return teamService;}

    public ActivityService getActivityService() {
        return activityService;
    }
}
