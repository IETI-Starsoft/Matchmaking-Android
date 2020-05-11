package edu.escuelaing.matchmaking.storage;

import android.content.Context;
import android.content.SharedPreferences;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.data.Token;

public class Storage {
    private final String TOKEN_KEY = "TOKEN_KEY";

    private final String EMAIL = "EMAIL";

    private final SharedPreferences sharedPreferences;

    public Storage(Context context) {
        this.sharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, null);
    }

    public void saveToken(Token token) {
        sharedPreferences.edit().putString(TOKEN_KEY, token.getAccessToken()).apply();
    }

    public boolean containsToken() {
        return sharedPreferences.contains(TOKEN_KEY);
    }

    public void clear() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply();
        sharedPreferences.edit().remove(EMAIL).apply();
    }

    public void saveEmail(String email) {
        sharedPreferences.edit().putString(EMAIL, email).apply();
    }
}
