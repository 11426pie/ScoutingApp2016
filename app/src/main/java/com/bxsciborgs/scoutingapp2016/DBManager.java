package com.bxsciborgs.scoutingapp2016;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by subin on 3/1/16.
 */

public class DBManager {
    private static String responseBody;
    private static JSONArray matchesInfo;

    public static List<JSONObject> getMatchAlliances() {
        return matchAlliances;
    }

    public static JSONArray getMatchesInfo() {
        return matchesInfo;
    }

    private static List<JSONObject> matchAlliances;

    public static void pull(String className, String rowKey, Object rowValue, final String finalKey){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        query.whereEqualTo(rowKey, rowValue);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null){
                    JSONObject json = object.getJSONObject(finalKey);
                }
            }
        });
    }

    public static void push(String className, String rowKey, JSONObject rowValue, final String finalKey, JSONObject object){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        query.whereEqualTo(rowKey, rowValue);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null){
                    object.put(finalKey, object);
                    object.saveInBackground();
                }
            }
        });
    }

    public static void getBlueAllianceMatches() throws Exception{
        OkHttpClient client = new OkHttpClient();

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
                    matchesInfo = new JSONArray(responseBody);
                    for(int i=0;i<matchesInfo.length();i++){
                        JSONObject alliance = new JSONObject();
                        alliance.put("Match " + i, matchesInfo.getJSONObject(i).getJSONObject("alliances"));
                        matchAlliances.add(i, alliance);
                    }
                    Log.d("DBManager", matchAlliances.toString());

                } catch (JSONException e) {
                    // e.printStackTrace();
                    Log.d("DBManager", "OkHttp request Error!");
                }
            }
        });
    }
}
