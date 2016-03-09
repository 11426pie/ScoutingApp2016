package com.bxsciborgs.scoutingapp2016;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
        /*BlueAlliance.sendRequestMatches(CompetitionCode.Javits, completion: {(matches: [JSON]) -> Void in
            dispatch_async(dispatch_get_main_queue(), {
                    var participatedMatches: [JSON] = []
            for match in matches {
                if BlueAlliance.getTeamsFromMatch(match, color: "blue").contains(self.teamNumber) {
                    participatedMatches.append(match)
                }else if BlueAlliance.getTeamsFromMatch(match, color: "red").contains(self.teamNumber){
                    participatedMatches.append(match)
                }
            }
            completion(result: participatedMatches)
            })
        })*/
        BlueAlliance.sendRequestMatches("nyny");
        JSONArray participatedMatches = new JSONArray();
        for (int i = 0; i < BlueAlliance.matches.length(); i++){
            if(BlueAlliance.getTeamsFromMatch(BlueAlliance.matches.get(i)), "blue"){
                participatedMatches.put(BlueAlliance.matches.get(i));
            }else if(BlueAlliance.getTeamsFromMatch(BlueAlliance.matches.get(i)), "red").contains(teamNumber){
                participatedMatches.put(BlueAlliance.matches.get(i));
            }
        }
    }

   // public static ________ getAllianceAndEnemyTeamsFromMatch(JSONObject match){




        /*var allianceTeams: [Int] = []
        var enemyTeams: [Int] = []

        if BlueAlliance.getTeamsFromMatch(match, color: "blue").contains(self.teamNumber) {
            for team in BlueAlliance.getTeamsFromMatch(match, color: "blue") {
                if (team != 1155) {
                    allianceTeams.append(team)
                }
            }
            for team in BlueAlliance.getTeamsFromMatch(match, color: "red") {
                enemyTeams.append(team)
            }
        }else if BlueAlliance.getTeamsFromMatch(match, color: "red").contains(self.teamNumber){
            for team in BlueAlliance.getTeamsFromMatch(match, color: "red") {
                if (team != 1155) {
                    allianceTeams.append(team)
                }
            }
            for team in BlueAlliance.getTeamsFromMatch(match, color: "blue") {
                enemyTeams.append(team)
            }
        }

        return (allianceTeams, enemyTeams)*/
    //}
}
