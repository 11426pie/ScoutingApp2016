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
            blueTeam1 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("blue").getJSONArray("teams").get(0).toString().replace("frc","");
            blueTeam2 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match " + position).getJSONObject("blue").getJSONArray("teams").get(1).toString().replace("frc", "");
            blueTeam3 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match " + position).getJSONObject("blue").getJSONArray("teams").get(2).toString().replace("frc", "");


            redTeam1 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("red").getJSONArray("teams").get(0).toString().replace("frc","");
            redTeam2 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("red").getJSONArray("teams").get(1).toString().replace("frc","");
            redTeam3 = UpdateInfo.matchAlliances.get(position).getJSONObject("Match "+position).getJSONObject("red").getJSONArray("teams").get(2).toString().replace("frc","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return UpdateInfo.matchAlliances.size();
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
                intent.putExtra("blueTeam1", h.bTeam1.getText().toString());
                intent.putExtra("blueTeam2", h.bTeam2.getText().toString());
                intent.putExtra("blueTeam3", h.bTeam3.getText().toString());
                intent.putExtra("redTeam1", h.rTeam1.getText().toString());
                intent.putExtra("redTeam2", h.rTeam2.getText().toString());
                intent.putExtra("redTeam3", h.rTeam3.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }
}


