package com.bxsciborgs.scoutingapp2016;

/**
 * Created by subin on 1/19/16.
 */
import android.util.Log;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class UpdateInfo {
    private int objectCount;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
    private String teamNumber;

    public void getTeams() {
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Templates");
            // query.whereEqualTo("playerName", "Dan Stemkoski");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> teamsList, ParseException e) {
                    if (e == null) {
                        Log.d("Updated Info", "Retrieved " + teamsList.get(0).toString());
                        try {
                            Log.d("Updated Info", "Retrieved " + teamsList.get(0).getJSONArray("RoundTemplate").getJSONObject(0).getJSONObject("telePoints").getBoolean("scaling"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        Log.d("Updated Info", "Retrieved " + teamsList.get(0).getJSONArray("TeamTemplate").toString());
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getJSONByTeam(String t){
        teamNumber = t;
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
            // query.whereEqualTo("playerName", "Dan Stemkoski");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objectList, ParseException e) {
                    if (e == null) {
                        Log.d("Updated Info", "Retrieved " + objectList.get(0).toString());
                        try {
                            Log.d("Updated Info", "Retrieved " + objectList.get(0).getJSONObject("Team1155").getInt("number"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        Log.d("Updated Info", "Retrieved " + objectList.get(0).getJSONObject("Team1155").toString());
                        for(int i = 0; i < objectList.size(); i++){
                            try {
                                if(objectList.get(i).getJSONObject("Team1155").getInt("number") == (Integer.parseInt(teamNumber))){
                                    Log.d("Updated Info", "NumberTaken " + objectList.get(0).getJSONObject("Team1155").toString());
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String parseTestMethod() {
        //get first object in teams class and get object id then return it
        return "";
    }

}