package edu.escuelaing.matchmaking.network.services;

import edu.escuelaing.matchmaking.network.data.LoginWrapper;
import edu.escuelaing.matchmaking.network.data.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST( "users/login" )
    Call<Token> login(@Body LoginWrapper loginWrapper );


}
