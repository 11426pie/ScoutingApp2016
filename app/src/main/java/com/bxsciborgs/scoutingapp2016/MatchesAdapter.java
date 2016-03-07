package com.bxsciborgs.scoutingapp2016;
import org.json.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subin on 2/1/16.
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolder> {

    private String blueTeam1, blueTeam2, blueTeam3;
    private String redTeam1, redTeam2, redTeam3;

    public MatchesAdapter() {
    }

    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_matches, parent, false);

        return new MatchesViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        try {
            blueTeam1 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("blue").getJSONArray("teams").get(0).toString();
            blueTeam2 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("blue").getJSONArray("teams").get(1).toString();
            blueTeam3 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("blue").getJSONArray("teams").get(2).toString();


            redTeam1 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("red").getJSONArray("teams").get(0).toString();
            redTeam2 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("red").getJSONArray("teams").get(1).toString();
            redTeam3 = UpdateInfo.matchAlliances
                    .get(position).getJSONObject("Match "+position).getJSONObject("red").getJSONArray("teams").get(2).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return UpdateInfo.alliances.size();
    }

    @Override
    public void onBindViewHolder(final MatchesViewHolder holder, int position) {


        holder.bTeam1.setText(blueTeam1);
        holder.bTeam2.setText(blueTeam2);
        holder.bTeam3.setText(blueTeam3);

        holder.rTeam1.setText(redTeam1);
        holder.rTeam2.setText(redTeam2);
        holder.rTeam3.setText(redTeam3);

        final Bundle matchNum = new Bundle();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            MatchesViewHolder h =holder;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MatchActivity.class);
                //you can pass on the Pojo with PARCELABLE
                matchNum.putString("compLv", h.comp_Lvl.getText().toString());
                matchNum.putString("bluTeam1", h.bTeam1.getText().toString());
                matchNum.putString("bluTeam2", h.bTeam2.getText().toString());
                matchNum.putString("bluTeam3", h.bTeam3.getText().toString());
                matchNum.putString("redTeam1", h.rTeam1.getText().toString());
                matchNum.putString("redTeam2", h.rTeam2.getText().toString());
                matchNum.putString("readTeam3", h.rTeam3.getText().toString());
                matchNum.putString("matchNum", h.vMatchNum.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }
}


