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
class AutocompAdapter extends ArrayAdapter<String> {
    //private final Activity context;
    private final List<String> names;
   private final List<Integer> nums;

    public AutocompAdapter(Context context, List<String> name, List<Integer> num) {
        super(context, R.layout.custom_autocomplete, name);
        // TODO Auto-generated constructor stub

        //this.context=context;
        this.names=name;
        this.nums=num;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View customView=inflater.inflate(R.layout.custom_autocomplete, parent, false);

        TextView number = (TextView)customView.findViewById(R.id.teamNumber);
        TextView nickname= (TextView)customView.findViewById(R.id.teamNickname);
        String name = names.get(position);
        number.setText(name);
        nickname.setText(nums.get(position).toString());

        return customView;
    }
}
