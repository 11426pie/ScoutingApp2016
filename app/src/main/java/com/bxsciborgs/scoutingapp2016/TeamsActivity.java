package com.bxsciborgs.scoutingapp2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;


import okhttp3.OkHttpClient;

public class TeamsActivity  extends AppCompatActivity{
    private final OkHttpClient client = new OkHttpClient();
    public static final String TAG = "MatchesActivity";
    String[] teams;
    ArrayAdapter<String> adapter;
    List<Integer> teamNumbers=UpdateInfo.teamNumbers;
    List<String> teamNicknames = UpdateInfo.teamNicknames;
    String[] t = {"1155","2265"};
    ListView listTeams;
    View settingsButton;
    SearchView searchTeams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        listTeams = (ListView)findViewById(R.id.listTeams);
        searchTeams = (SearchView)findViewById(R.id.searchTeams);

        adapter = new AutocompAdapter(this, teamNicknames, teamNumbers);
        listTeams.setAdapter(adapter);

        searchTeams.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        final Intent toTeamProf = new Intent(this, TeamProfile.class);

        listTeams.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) listTeams.getItemAtPosition(position);

                Bundle teamName = new Bundle();
                teamName.putString("nickname", value);
                toTeamProf.putExtras(teamName);

                startActivity(toTeamProf);

                Log.e("Team Clicked", value);
                //
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });
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


/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                break;
        }
    }
    */


}
