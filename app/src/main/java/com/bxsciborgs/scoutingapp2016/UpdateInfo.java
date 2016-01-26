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
import java.util.Arrays;
import java.util.List;


public class UpdateInfo {
    private int objectCount;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
    static List<String> teamNumbers = new ArrayList<String>();
    static String[] arr;

    public static List<String> getTeamNumbers(){
        return teamNumbers;
    }
    public static void getTeams() {
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Templates");
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
    public static void getJSONByTeam(String t){

        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("AndroidTest");
            // query.whereEqualTo("playerName", "Dan Stemkoski");
            query.selectKeys(Arrays.asList("teamNumber"));
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objectList, ParseException e) {
                    if (e == null) {

                        for(int i = 0; i < objectList.size(); i++){
                                //if(objectList.get(i).getJSONObject("Team1155").getInt("number") == (Integer.parseInt(teamNumber))){
                                    Log.d("Updated Info", "Team " + objectList.get(i).get("teamNumber").toString());
                                //}
                            teamNumbers.add(objectList.get(i).getInt("teamNumber") + "");

                        }
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("UpdateInfo", teamNumbers.toString());
    }

    public String parseTestMethod() {
        //get first object in teams class and get object id then return it
        return "";
    }

}