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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateInfo {
    private int objectCount;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
    ArrayList<Integer> finalOutput = new ArrayList<Integer>();
    List<ParseObject> allObjects;
    public ArrayList<Integer> getAllTeamNumbers() {

        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                System.out.println("There are "+ count+" queries!");
                objectCount = count;
            }
        });
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> teams, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + teams.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                allObjects = teams;
            }
        });

        return finalOutput;
    }

}