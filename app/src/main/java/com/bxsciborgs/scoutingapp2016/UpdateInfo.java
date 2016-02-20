package com.bxsciborgs.scoutingapp2016;

/**
 * Created by subin on 1/19/16.
 */
import android.util.Log;

import com.google.gson.JsonObject;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateInfo {
    private int objectCount;
    //ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
    static List<Integer > teamNumbers = new ArrayList<Integer>();
    static List<String> teamNicknames = new ArrayList<String>();
    static String[] teamNumberArray = teamNicknames.toArray(new String[teamNicknames.size()]);
    static HashMap<String,Integer> averageRoundScore = new HashMap<String ,Integer>();
    static String[] teamNumber;
    static HashMap<String,Integer> teams = new HashMap<String, Integer>();
    private static final OkHttpClient client = new OkHttpClient();
    static String responseBody;
    static JSONArray res;

//Pulls all of the teams and prints the teamNumber and teamName
   /* public static List<JSONObject> getTeamMatches(int teamNumber){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
        query.whereEqualTo("number", teamNumber);
        query.getFirstInBackground(new FindCallback<ParseObject>(){
            @Override
            public void done(List<ParseObject> objects, ParseException e){

            }
        });
        return null;
        }
    */
public static void run() throws Exception {
    Request request = new Request.Builder()
            .url("http://thebluealliance.com/api/v2/event/2014nyny/matches")
            .addHeader("X-TBA-App-Id","frc1155:scouting-app:v01")
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            responseBody = response.body().string();
            try {
                res = new JSONArray(responseBody);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                res.getJSONObject(0).getJSONObject("alliances").getJSONObject("blue").getInt("score");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    });
}
    public static void query(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    teams.put(objects.get(i).getString("teamNickname"), objects.get(i).getInt("teamNumber"));
                    teamNumbers.add(i, objects.get(i).getInt("teamNumber"));
                    teamNicknames.add(i, objects.get(i).getString("teamNickname"));
                    try {
                        averageRoundScore.put(objects.get(i).getString("teamNickname"), objects.get(i).getJSONObject("TeamInfo").getJSONObject("stats").getInt("avgRoundScore"));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                  //Log.d("UpdateInfo", objects.get(i).getInt("teamNumber") + " : " + objects.get(i).getString("teamNickname"));


                }
            }
        });
    }
//Pushes info TeamInfo key on Parse database
    public static void pushTeamInfo(int teamNumber) throws JSONException {
        //"avgDefenseScore":null,"avgRoundScore":null
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
        query.whereEqualTo("teamNumber", teamNumber);
        final JSONObject template = new JSONObject();
        JSONArray rounds = new JSONArray();
        JSONObject stats = new JSONObject();
        stats.put("avgDefenseScore",1);
        stats.put("avgRoundScore",1);
        template.put("rounds", rounds);
        template.put("stats", stats);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put("TeamInfo", template);
                object.pinInBackground();
                object.saveInBackground();
            }
        });
    }





//Gets a team's object, edits, pushes
    public static JSONObject teamInfo;
    public static JSONObject updateTeam(int teamNumber){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
        query.fromLocalDatastore();
        query.whereEqualTo("teamNumber", teamNumber);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                 teamInfo= object.getJSONObject("TeamInfo");

            }
        });
        return teamInfo;
    }


    /*
    public static void query(String key) {
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
            query.orderByDescending("createdAt");
            query.whereExists(key);
            //query.whereEqualTo("number", VALUE);

            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e != null) {
                        e.printStackTrace();
                        Log.e("parseException", "error");
                        // object will be your game score
                    } else {
                        Log.i("teamNum", "" + object.get("number"));
                    }
                }
            });
//            query.findInBackground(new FindCallback<ParseObject>() {
//                public void done(List<ParseObject> objectList, ParseException e) {
//                    if (e == null) {
//
//                        ParseOb
//                    } else {
//                        Log.d("score", "Error: " + e.getMessage());
//                    }
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    public String parseTestMethod() {
        //get first object in teams class and get object id then return it
        return "";
    }


}