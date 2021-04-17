package com.app.freedomexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.freedom.Freedom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize this library in onCreate method of LAUNCHER Activity.
           For Example : Splash Screen */
        Freedom.initialize(this)
                .setExpireAfterDays(3)
                .setWorkForAllVariant(false)
                .setMessage("This APK has expired, please contact the developer to get a new APK.")
                .setHaveToShowMessage(true)
                .setHaveToClearNotifications(false)
                .start();
    }
}