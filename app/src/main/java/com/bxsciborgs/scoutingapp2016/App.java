package com.bxsciborgs.scoutingapp2016;

/**
 * Created by subin on 1/21/16.
 */
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;

import io.fabric.sdk.android.Fabric;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        // Initialize global stuff for Yourney
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "KBqIB66cUvbVxjCLMQw1ug3AiTdldkjoDKlhpGuo", "EmsYKeBWl79WGbAdhtjWUUYCyJuL7iABao5lbzcM");
        //UpdateInfo.getTeam("Team1155");
        UpdateInfo.query();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
