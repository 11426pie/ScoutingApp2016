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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import bolts.TaskCompletionSource;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*TODO make the completion in pull return the JSONObject for usage, work on completion blocks in addAllTeams and createNewClass */
public class DBManager {


    public static void pull(String className, String rowKey, Object rowValue, final String finalKey){
        final TaskCompletionSource<JSONObject> result = new TaskCompletionSource();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        query.whereEqualTo(rowKey, rowValue);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null){
                    JSONObject json = object.getJSONObject(finalKey);
                    result.setResult(json);
                }else{
                    Log.d("DBManager", "Could not get JSON object.");
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

    public static JSONObject getJSON(String filename) throws IOException,JSONException {
        File f = new File(filename);
        InputStream is = new FileInputStream(filename);
        String jsonTxt = IOUtils.toString(is);
        JSONObject json = new JSONObject(jsonTxt);
        return json;
    }

    public static void addAllTeams(){
        BlueAlliance.sendRequestTeams("nyny");
        //TODO completion block with:
        /*for teamNum in teamNumbers {
            let teamProfile = Team(teamNumber: teamNum)
            teamProfile.sendSkeleton()
        }*/
    }

    public static void createNewClass(String className){
        BlueAlliance.sendRequestTeams("nyny");
        //TODO implement the following code into completion block
        /*for(int i = 0; i < teamNames.length; i++){
            ParseObject obj = new ParseObject(className);
            ParseObject.create("teamNumber") = teamNumbers[i];
            //...
        }*/
        /*BlueAlliance.sendRequestTeams(CompetitionCode.Javits, completion: {(teamNames: [String], teamNumbers: [Int])->Void in
            for i in 0..<teamNames.count {
                let object = PFObject(className: className)
                object["teamNumber"] = teamNumbers[i]
                object["teamKey"] = "frc\(teamNumbers[i])"
                object["teamNickname"] = teamNames[i]
                object.saveInBackground()
            }
        })*/
    }



}
