package edu.escuelaing.matchmaking.network;

import edu.escuelaing.matchmaking.network.services.AuthService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    private static final String BASE_URL = "http://192.168.1.11:8080/";

    private AuthService authService;

    public RetrofitNetwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( BASE_URL )
                .addConverterFactory(GsonConverterFactory.create()).build();

        authService = retrofit.create(AuthService.class);
    }

    public AuthService getAuthService() {
        return authService;
    }
}
