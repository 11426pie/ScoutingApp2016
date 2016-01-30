package com.bxsciborgs.scoutingapp2016;

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
class AutocompAdapter extends ArrayAdapter<String> {
    public AutocompAdapter(Context context, List<String> resource) {
        super(context, R.layout.custom_autocomplete, resource);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_autocomplete, parent, false);

        String singleTeam = getItem(position);
        TextView number = (TextView)customView.findViewById(R.id.teamNumber);
        TextView nickname= (TextView)customView.findViewById(R.id.teamNickname);

        number.setText(singleTeam);

        return customView;
    }
}
