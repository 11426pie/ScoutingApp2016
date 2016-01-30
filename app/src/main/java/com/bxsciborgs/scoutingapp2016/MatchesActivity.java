package com.bxsciborgs.scoutingapp2016;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchesActivity extends AppCompatActivity implements  View.OnClickListener{
    private final OkHttpClient client = new OkHttpClient();
    public static final String TAG = "MatchesActivity";
    String[] teams;
    AutoCompleteTextView auto;
    ArrayAdapter<String> adapter;
    List<String> team;
    String[] t = {"1155","2265"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        findViewById(R.id.button).setOnClickListener(this);
        auto = (AutoCompleteTextView) findViewById(R.id.searchTeams);

        team = UpdateInfo.getTeamNumbers();
        UpdateInfo.query("Team694");
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, team);
        auto.setThreshold(1);
        auto.setAdapter(adapter);

        //auto.getOnItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_matches, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void loadData(){

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                break;
        }
    }

}
