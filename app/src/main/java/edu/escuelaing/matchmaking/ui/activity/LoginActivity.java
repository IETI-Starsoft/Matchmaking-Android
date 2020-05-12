package edu.escuelaing.matchmaking.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.network.data.LoginWrapper;
import edu.escuelaing.matchmaking.network.data.Token;
import edu.escuelaing.matchmaking.storage.Storage;
import edu.escuelaing.matchmaking.ui.utils.StringUtils;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    private final RetrofitNetwork retrofitNetwork = new RetrofitNetwork();

    private Storage storage;

    private EditText email;

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        storage = new Storage(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void onLoginClicked(final View view) {
        final LoginWrapper loginWrapper = validInputFields();
        if (loginWrapper != null) {
            view.setEnabled(false);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Call<Token> call = retrofitNetwork.getAuthService().login(loginWrapper);
                        Response<Token> response = call.execute();
                        if (response.isSuccessful()) {
                            Token token = response.body();
                            storage.saveToken(token);
                            storage.saveEmail(email.getText().toString());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            showErrorMessage(view, R.string.credential_error);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        showErrorMessage(view, R.string.server_error_message);
                    }
                }
            });
        }
    }

    private void showErrorMessage(final View view, final int idString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
                Snackbar snackbar = Snackbar.make(view, getString(idString), Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.errorSnackbar));
                snackbar.show();
            }
        });

    }

    private LoginWrapper validInputFields() {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        if (!StringUtils.isValidEmail(email)) {
            this.email.setError(getString(R.string.invalid_email));
            return null;
        } else {
            this.email.setError(null);
            if (password.isEmpty()) {
                this.password.setError(getString(R.string.please_enter_a_password));
                return null;
            } else {
                this.password.setError(null);
            }
        }

        return new LoginWrapper(email, password);
    }

}
