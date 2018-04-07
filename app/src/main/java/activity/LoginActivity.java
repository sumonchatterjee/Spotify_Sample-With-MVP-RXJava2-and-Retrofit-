package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import application.SpotifyApplication;
import data.SpotifyPreferences;
import data.api.Constant;
import spotify.com.spotifysample.R;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1337;

    private String mAccessToken;
    private String mAccessCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // The only thing that's different is we added the 5 lines below.
        if(TextUtils.isEmpty(SpotifyPreferences.getAccessToken(this))){
            AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(Constant.CLIENT_ID, AuthenticationResponse.Type.TOKEN, Constant.REDIRECT_URI);
            builder.setScopes(new String[]{"user-read-private", "streaming"});
            AuthenticationRequest request = builder.build();

            AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        }else {
            navigateToNext();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {

                mAccessToken = response.getAccessToken();
                SpotifyApplication.getInstance().setToken(mAccessToken);
                navigateToNext();
            }
        }
    }


    private void navigateToNext(){
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }

}
