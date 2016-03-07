package com.bxsciborgs.scoutingapp2016;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Nik on 3/6/2016.
 */
//TODO get DBManager to return the DAMN json so template can be initialised
public class Round {
    int roundNumber;
    JSONObject template;

    public Round(int roundNumber){
        this.roundNumber = roundNumber;
        DBManager.pull("Templates", "templateType", "RoundTemplate", "templateJSON");
        //TODO json return
    }

    public HashMap<String, Object> getRound(){
        HashMap<String, Object> roundDict = new Gson().fromJson(template.toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
        return roundDict;
    }
}
