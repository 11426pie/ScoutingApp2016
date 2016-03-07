package com.bxsciborgs.scoutingapp2016;

import org.json.JSONObject;

/**
 * Created by Nik on 3/7/2016.
 */

//TODO basically this whole class's methods
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
        /*DBManager.pull("Templates", rowKey: "templateType", rowValue: "TeamTemplate", finalKey: "templateJSON", completion: {(result)->Void in
            var teamJSON = result
            DBManager.push(ParseClass.TeamsTest.rawValue, rowKey: "teamNumber", rowValue: self.teamNumber, finalKey: "TeamInfo", object: teamJSON.dictionaryObject!)
        })*/
        //TODO more of this completion type stuff
    }

    public static void createRound(int roundNum){
        currentRound = new Round(roundNum);
    }

    public static void submitCurrentRound(){
        /*DBManager.pull(ParseClass.TeamsTest.rawValue, rowKey: "teamNumber", rowValue: self.teamNumber, finalKey: "TeamInfo", completion: {(result)->Void in
            var teamJSON = result
            teamJSON["rounds"].arrayObject?.append(self.currentRound.getRound()) //appends current round being edited
            DBManager.push(ParseClass.TeamsTest.rawValue, rowKey: "teamNumber", rowValue: self.teamNumber, finalKey: "TeamInfo", object: teamJSON.dictionaryObject!)
        })*/
    }

    public static void getAllRounds(){
        /*DBManager.pull(ParseClass.TeamsTest.rawValue, rowKey: "teamNumber", rowValue: self.teamNumber, finalKey: "TeamInfo", completion: {(result)->Void in
            var teamJSON = result
            completion(result: teamJSON["rounds"].array!)
        })
        print("No rounds found")*/
    }

    public static void getAllParticipatingMatches(){
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
    }

    public static ________ getAllianceAndEnemyTeamsFromMatch(JSONObject match){




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
    }
}
