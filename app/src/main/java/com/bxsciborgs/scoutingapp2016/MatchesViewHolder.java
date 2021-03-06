package com.bxsciborgs.scoutingapp2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by subin on 2/1/16.
 */
public class MatchesViewHolder extends RecyclerView.ViewHolder{

    protected TextView vMatchNum, comp_Lvl;

    protected  TextView bTeam1, bTeam2, bTeam3;
    protected TextView rTeam1, rTeam2, rTeam3;

    public MatchesViewHolder(View itemView) {
        super(itemView);
        bTeam1 = (TextView)itemView.findViewById(R.id.team1);
        bTeam2 = (TextView)itemView.findViewById(R.id.team2);
        bTeam3 = (TextView)itemView.findViewById(R.id.team3);

        rTeam1 = (TextView)itemView.findViewById(R.id.team4);
        rTeam2 = (TextView)itemView.findViewById(R.id.team5);
        rTeam3 = (TextView)itemView.findViewById(R.id.team6);
    }


}
