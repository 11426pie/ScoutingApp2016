package com.bxsciborgs.scoutingapp2016;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by subin on 1/29/16.
 */
class AutocompAdapter extends ArrayAdapter<String> {private final Activity context;
    private final String[] teamNums;
    private final String[] teamNicks;

    public AutocompAdapter(Activity context, String[] nums, String[] nick) {
        super(context, R.layout.custom_autocomplete, nick);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.teamNums=nums;
        this.teamNicks=nick;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_autocomplete, parent,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.teamNickname);
        TextView extratxt = (TextView) rowView.findViewById(R.id.teamNumber);

        txtTitle.setText(teamNums[position]);
        extratxt.setText(teamNicks[position]);
        return rowView;

    };
}
