package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public android.support.v4.app.FragmentManager getFragmentsManager() {
        return getSupportFragmentManager();
    }

}
