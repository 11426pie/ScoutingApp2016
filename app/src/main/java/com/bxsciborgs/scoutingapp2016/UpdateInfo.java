package com.bxsciborgs.scoutingapp2016;

/**
 * Created by subin on 1/19/16.
 */
import android.util.Log;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UpdateInfo {
    private int objectCount;
    //ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
    static List<String> teamNumbers = new ArrayList<String>();
    static String[] arr;

    public static List<String> getTeamNumbers(){
        return teamNumbers;
    }

    public static void getTeam(String t){

        final String KEY = t;
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Teams");
            query.orderByDescending("createdAt");
            query.whereExists(KEY);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                        Log.d("score", "The getFirst request failed.");
                    } else {
                        ParseObject myObject = object.getParseObject(KEY);
                        Log.d("Object", myObject.toString());
                        ;
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

    public String parseTestMethod() {
        //get first object in teams class and get object id then return it
        return "";
    }

}