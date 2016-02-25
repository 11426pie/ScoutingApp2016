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

import org.json.JSONException;

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
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        Parse.initialize(this, "KBqIB66cUvbVxjCLMQw1ug3AiTdldkjoDKlhpGuo", "EmsYKeBWl79WGbAdhtjWUUYCyJuL7iABao5lbzcM");
        //UpdateInfo.getTeam("Team1155");
       //UpdateInfo.query();



        /*
        try {
            //UpdateInfo.pushTeamInfo(1155);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
        try {
           UpdateInfo.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, Mode.class);
        startActivity(intent);
        finish();
    }
}
