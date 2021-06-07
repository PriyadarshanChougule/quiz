package com.example.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SetsAdapter extends BaseAdapter {

    private int numberOfSets;
    SetsAdapter(int numberOfSets){
        this.numberOfSets=numberOfSets;
    }
    @Override
    public int getCount() {
        return numberOfSets;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_layout,parent,false);
        }
        else{
            view = convertView;
        }
        ((TextView) view.findViewById(R.id.setNo_tv)).setText(String.valueOf(position+1));
        return view;
    }
}
