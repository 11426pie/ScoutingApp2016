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
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.bxsciborgs.scoutingapp2016.BlueAlliance.teamNicknames;
import static com.bxsciborgs.scoutingapp2016.BlueAlliance.teamNumbers;

//TODO completion IF NEEDED
public class DBManager {
    public static JSONObject pulledJson = new JSONObject();

    public static void pull(String className, String rowKey, Object rowValue, final String finalKey){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        query.whereEqualTo(rowKey, rowValue);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null){
                   pulledJson = object.getJSONObject(finalKey);

                }else{
                    Log.d("DBManager", "Could not get JSON object.");
                }
            }
        });
    }

    public static void push(String className, String rowKey, Object rowValue, final String finalKey, final HashMap<String,Object> dictObject){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        query.whereEqualTo(rowKey, rowValue);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null){
                    object.put(finalKey, dictObject);
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
        for(int i = 0; i < teamNumbers.size(); i++){
            Team teamProfile = new Team(teamNumbers.get(i));
            teamProfile.sendSkeleton();
        }
    }

    public static void createNewClass(String className){
        BlueAlliance.sendRequestTeams("nyny");
        for(int i = 0; i < teamNicknames.size(); i++){
            ParseObject obj = new ParseObject(className);
            obj.put("teamNumber", teamNumbers.get(i));
            obj.put("teamKey", "frc" + teamNumbers.get(i));
            obj.put("teamNickname", teamNicknames.get(i));
            obj.saveInBackground();
        }
    }



}
