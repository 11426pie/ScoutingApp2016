package com.bxsciborgs.scoutingapp2016;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Nik on 3/7/2016.
 */

//TODO completion if NEEDED
public class Team {
    static int teamNumber;
    private static JSONObject teamTemplate;
    private static JSONObject roundTemplate;
    private static JSONObject teamJSON;
    private static Round currentRound;
    static JSONArray participatingMatches;

    public Team(int teamNumber){
        this.teamNumber = teamNumber;
    }

    public void sendSkeleton(){
        DBManager.pull("Templates", "templateType", "TeamTemplate", "templateJSON");
        JSONObject teamJSON = DBManager.pulledJson;
        HashMap<String,Object> teamDict = new Gson().fromJson(teamJSON.toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
        DBManager.push("TeamsTEST", "teamNumber", teamNumber, "TeamInfo", teamDict);
    }

    public static void createRound(int roundNum){
        currentRound = new Round(roundNum);
    }

    public static void submitCurrentRound() throws JSONException {
        DBManager.pull("TeamsTEST", "teamNumber", teamNumber, "TeamInfo");
        JSONObject teamJSON = DBManager.pulledJson;
        //Gson gson = new Gson();
        JSONObject roundJSON = new JSONObject(currentRound.getRound());
        teamJSON.getJSONArray("rounds").put(roundJSON);
        HashMap<String,Object> teamDict = new Gson().fromJson(teamJSON.toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
        DBManager.push("TeamsTEST","teamNumber",teamNumber,"TeamInfo",teamDict);
    }

    public static JSONArray getAllRounds() throws JSONException {
        DBManager.pull("TeamsTEST", "teamNumber", teamNumber, "TeamInfo");
        JSONObject teamJSON = DBManager.pulledJson;
        return teamJSON.getJSONArray("rounds");

    }

    public static void getAllParticipatingMatches() throws JSONException {
        BlueAlliance.sendRequestMatches("nyny");
        JSONArray participatedMatches = new JSONArray();
        for (int i = 0; i < BlueAlliance.matches.length(); i++){
            if(ArrayUtils.indexOf(BlueAlliance.getTeamsFromMatch(BlueAlliance.matches.getJSONObject(i), "blue"),teamNumber) >= 0){
                participatedMatches.put(BlueAlliance.matches.get(i));
            }else if(ArrayUtils.indexOf(BlueAlliance.getTeamsFromMatch(BlueAlliance.matches.getJSONObject(i), "red"),teamNumber) >= 0){
                participatedMatches.put(BlueAlliance.matches.get(i));
            }
        }
        participatingMatches = participatedMatches;
    }

    public static ArrayList<ArrayList<Integer>> getAllianceAndEnemyTeamsFromMatch(JSONObject match) throws JSONException {
        ArrayList<Integer> allianceTeams = new ArrayList<Integer>();
        ArrayList<Integer> enemyTeams = new ArrayList<Integer>();
        if (ArrayUtils.indexOf(BlueAlliance.getTeamsFromMatch(match, "blue"), teamNumber) >= 0) {
            for (int i = 0; i < BlueAlliance.getTeamsFromMatch(match, "blue").length; i++) {
                if (BlueAlliance.getTeamsFromMatch(match, "blue")[i] != 1155) {
                    allianceTeams.add(BlueAlliance.getTeamsFromMatch(match, "blue")[i]);
                }
            }
            for (int i = 0; i < BlueAlliance.getTeamsFromMatch(match, "red").length; i++) {
                enemyTeams.add(BlueAlliance.getTeamsFromMatch(match, "red")[i]);
            }
        } else if (ArrayUtils.indexOf(BlueAlliance.getTeamsFromMatch(match, "red"), teamNumber) >= 0) {
            for (int i = 0; i < BlueAlliance.getTeamsFromMatch(match, "red").length; i++) {
                if (BlueAlliance.getTeamsFromMatch(match, "blue")[i] != 1155) {
                    allianceTeams.add(BlueAlliance.getTeamsFromMatch(match, "red")[i]);
                }
            }
            for (int i = 0; i < BlueAlliance.getTeamsFromMatch(match, "blue").length; i++) {
                enemyTeams.add(BlueAlliance.getTeamsFromMatch(match, "blue")[i]);
            }
        }
        ArrayList<ArrayList<Integer>> bothTeams = new ArrayList<ArrayList<Integer>>();
        bothTeams.add(allianceTeams);
        bothTeams.add(enemyTeams);
        return bothTeams;
    }



}
