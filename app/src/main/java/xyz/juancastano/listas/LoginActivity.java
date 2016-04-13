package xyz.juancastano.listas;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends Activity {

    private static final long SPLASH_SCREEN_PLAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_PLAY);

    }

}
