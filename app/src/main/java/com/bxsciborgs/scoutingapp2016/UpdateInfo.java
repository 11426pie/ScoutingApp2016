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
    static String[] teamNicknames;
    static String[] teamNumber;


    public static List<String> getTeamNumbers(){
        return teamNumbers;
    }
    public static void query(String key){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("AndroidTest");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (int i=0;i<objects.size();i++){
                    Log.d("UpdateInfo", objects.get(i).getString("teamNickname"));
                }
            }
        });
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