package com.bxsciborgs.scoutingapp2016;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{
    public static final String TAG = "LoginActivity";
    public boolean parseCreate;
    public final int RC_SIGN_IN = 9001;
    public TextView mStatusTextView;
    GoogleApiClient mGoogleApiClient;
    public Toast toast;
    private SharedPreferences googleData;
    Intent toMatchesAct;
    UpdateInfo update;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ParseUser user = new ParseUser();
        user.setUsername("SampleUser");
        user.setEmail("sample@sample.com");
        user.setPassword("");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Log.d(TAG, "Signed In!");
                }
            }
        });



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("1008849373609-ejp7e41mrovl1p4f41vsdml4r5d4rcnk.apps.googleusercontent.com")
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, null /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        toMatchesAct = new Intent(this, Mode.class);

        googleData = getSharedPreferences("User",MODE_PRIVATE);
        if(googleData.contains("accountName")){
            startActivity(toMatchesAct);
            finish();
        }

      //  ArrayList<String> obj = update.getAllTeamNumbers();
      //  for(int i = 0;i < obj.size();i++) {
       //     Log.d(TAG, "Teams: " + obj.get(i));
       // }



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, requestCode + "");
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "Result:" + result.getStatus().toString());
            handleSignInResult(result);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            final GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(acct.getDisplayName() + " has signed in!");
            updateUI(true, acct);
            Log.d(TAG, acct.getDisplayName());
            googleData = getSharedPreferences("User",MODE_PRIVATE);
            googleData.edit().putString("accountName", acct.getDisplayName());
            /*
            ParseQuery userQuery = ParseUser.getQuery();
            userQuery.whereEqualTo("email", acct.getEmail());
            userQuery.findInBackground(new FindCallback<ParseUser>() {
                public void done(List<ParseUser> objects, ParseException e) {
                    if (e == null) {
                        // The query was successful.
                        try {
                            ParseUser.logIn(acct.getDisplayName(),"");
                            Log.d(TAG, acct.getDisplayName() + " Logged In!");
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        // Sign up a new Parse user


                        try {
                            user.signUp();
                            Log.d(TAG,acct.getDisplayName() + " Signed Up!");
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            });*/



        } else {
            // Signed out, show unauthenticated UI
            updateUI(false, null);
        }
    }

    private void updateUI(boolean signedIn, GoogleSignInAccount g) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            //TRANSFER TO MAIN PAGE



            startActivity(toMatchesAct);
            finish();

        } else {
            toast = Toast.makeText(getApplicationContext(), "Sign Out", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
            toast.show();
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
            //mStatusTextView.setText("Signed Out!");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false,null);
                        // [END_EXCLUDE]
                    }
                });
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
        }
    }


}
