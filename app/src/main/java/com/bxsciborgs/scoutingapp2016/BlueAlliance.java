package com.bxsciborgs.scoutingapp2016;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nik on 3/6/2016.
 */

//TODO - sort matches in sendRequestMatches
public class BlueAlliance {
    //javits = "nyny"
    //florida = "flwp"
    private static String responseBody;
    private static JSONArray matchesInfo;
    private static JSONArray matches;
    private static JSONArray teamsInfo;

    public static List<JSONObject> getMatchAlliances() {
        return matchAlliances;
    }

    public static JSONArray getMatchesInfo() {
        return matchesInfo;
    }

    private static List<JSONObject> matchAlliances;
    static List<Integer> teamNumbers = new ArrayList<Integer>();
    static List<String> teamNicknames = new ArrayList<String>();

    public static void sendRequestMatches(String competitionCode){
        String reqURL = "http://thebluealliance.com/api/v2/event/2015" + competitionCode + "/matches";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(reqURL).addHeader("X-TBA-App-Id", "frc1155:scouting-app:v01").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseBody = response.body().string();

                try {
                    matchesInfo = new JSONArray(responseBody);
                    matches = new JSONArray();
                    for(int i=0;i<matchesInfo.length();i++){
                        if(matchesInfo.getJSONObject(i).get("comp_level").equals("qm")){
                            matches.put(matchesInfo.get(i));
                        }
                        /*JSONObject alliance = new JSONObject();
                        alliance.put("Match " + i, matchesInfo.getJSONObject(i).getJSONObject("alliances"));
                        matchAlliances.add(i, alliance);*/
                    }
                    //TODO SORT MATCHES

                    Log.d("BlueAlliance", matches.toString());
                } catch (JSONException e) {
                    // e.printStackTrace();
                    Log.d("BlueAlliance", "OkHttp request Error!");
                }
            }
        });
    }

    public static void sendRequestTeams(String competitionCode){
        String reqURL = "http://thebluealliance.com/api/v2/event/2015" + competitionCode + "/teams";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(reqURL).addHeader("X-TBA-App-Id", "frc1155:scouting-app:v01").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseBody = response.body().string();

                try {
                    teamsInfo = new JSONArray(responseBody);
                    for(int i = 0; i < teamsInfo.length(); i++){
                        teamNumbers.add(teamsInfo.getJSONObject(i).getInt("team_number"));
                        teamNicknames.add(teamsInfo.getJSONObject(i).getString("nickname"));
                    }
                    Log.d("BlueAlliance", matchAlliances.toString());

                } catch (JSONException e) {
                    // e.printStackTrace();
                    Log.d("BlueAlliance", "OkHttp request Error!");
                }
            }
        });
    }
    public static int[] getTeamsFromMatch(JSONObject match, String color) throws JSONException {
        JSONArray teamArrayJSON = match.getJSONObject("alliances").getJSONObject("color").getJSONArray("teams");
        String[] teamArray = new String[teamArrayJSON.length()];
        for(int i = 0; i < teamArrayJSON.length(); i++){
            teamArray[i] = teamArrayJSON.getString(i);
        }
        int[] teamNumbers = new int[teamArray.length];
        for(int j = 0; j < teamNumbers.length; j++){
            teamNumbers[j] = Integer.parseInt(teamArray[j].replaceAll("frc", ""));
        }
        return teamNumbers;
    }

}
