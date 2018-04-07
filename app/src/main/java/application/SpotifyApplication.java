package application;

import android.app.Application;

import data.SpotifyPreferences;

/**
 * Created by sumon.chatterjee on 04/04/18.
 */

public class SpotifyApplication extends Application {
    private static SpotifyApplication sInstance;

    String accessToken;

    @Override
    public void onCreate(){
        super.onCreate();

        sInstance = this;

        accessToken = retrieveTokenFromSharedPrefs();

    }

    public static SpotifyApplication getInstance() {
        return sInstance;
    }

    private String retrieveTokenFromSharedPrefs() {
        // get your access token from SharedPrefs
        return SpotifyPreferences.getAccessToken(this);
    }

    public void setToken(String token) {
        accessToken = token;
        SpotifyPreferences.setAcessToken(this,accessToken);
        // save your access token in the SharedPrefs
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean hasLoggedIn() {
        return getAccessToken() != null;
    }

    public boolean hasTokenExpired() {
        // ecc..
        return false;
    }
}
