package com.bxsciborgs.scoutingapp2016;

import android.content.Context;
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
    }

    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position) {
       holder.bTeam1.setText("111");
       holder.bTeam2.setText("222");
       holder.bTeam3.setText("333");

       holder.rTeam1.setText("111");
       holder.rTeam2.setText("222");
       holder.rTeam3.setText("333");




    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
