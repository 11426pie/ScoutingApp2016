package com.bxsciborgs.scoutingapp2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MatchActivity extends AppCompatActivity {
    private Button blueTeam1Button, blueTeam2Button, blueTeam3Button,
            redTeam1Button,redTeam2Button,redTeam3Button;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MatchesActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle matchData = getIntent().getExtras();

        String blueTeam1 = matchData.getString("blueTeam1");
        String blueTeam2 = matchData.getString("blueTeam2");
        String blueTeam3 = matchData.getString("blueTeam3");


        String redTeam1 = matchData.getString("redTeam1");
        String redTeam2 = matchData.getString("redTeam2");
        String redTeam3 = matchData.getString("redTeam3");

        blueTeam1Button = (Button)findViewById(R.id.blueTeam1);
        blueTeam2Button = (Button)findViewById(R.id.blueTeam2);
        blueTeam3Button = (Button)findViewById(R.id.blueTeam3);

        redTeam1Button = (Button)findViewById(R.id.redTeam1);
        redTeam2Button = (Button)findViewById(R.id.redTeam2);
        redTeam3Button = (Button)findViewById(R.id.redTeam3);

        blueTeam1Button.setText(blueTeam1);
        blueTeam2Button.setText(blueTeam2);
        blueTeam3Button.setText(blueTeam3);

        redTeam1Button.setText(redTeam1);
        redTeam2Button.setText(redTeam2);
        redTeam3Button.setText(redTeam3);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match, menu);
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
