package com.bxsciborgs.scoutingapp2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TeamProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_profile);

        //{"rounds":[{
        // "autoPoints":{"highGoal":false,"lowGoal":false,"movedToDefense":false,"passedDefense":false},
        // "comments":"",
        // "finalScore":0,
        // "roundNumber":5,
        // "telePoints":{"challenge":false,
        //      "defenses":{"numTimesCrossedChevalDeFrise":0,
        //      "numTimesCrossedDrawbridge":0,
        //      "numTimesCrossedLowBar":0,
        //      "numTimesCrossedMoat":0,
        //      "numTimesCrossedPortcullis":0,
        //      "numTimesCrossedRamparts":0,
        //      "numTimesCrossedRockWall":0,
        //      "numTimesCrossedRoughTerrain":0,
        //      "numTimesCrossedSallyPort":0},
        // "goals":{"high":0,"low":0},"scale":false}}],
        // "stats":{"avgDefenseScore":null,"avgRoundScore":null}}

        Intent i = getIntent();
        String selectedNickname = i.getStringExtra("nickname");

        TextView teamNickname = (TextView)findViewById(R.id.nickname);
        teamNickname.setText(selectedNickname);
        TextView teamNumber = (TextView)findViewById(R.id.teamnumber);
        teamNumber.setText(UpdateInfo.teams.get(selectedNickname).toString());
        TextView averageScore = (TextView)findViewById(R.id.averagescore);
//        averageScore.setText(UpdateInfo.averageRoundScore.get(selectedNickname).toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_profile, menu);
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
}
