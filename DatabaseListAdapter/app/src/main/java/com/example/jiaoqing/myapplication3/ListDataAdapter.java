package com.example.jiaoqing.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaoqing on 16/8/16.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();

    static class LayoutHandler{
        TextView name, mobile, email;
    }

    public ListDataAdapter(Context context, int resource){
        super(context, resource);
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name = (TextView)row.findViewById(R.id.user_name);
            layoutHandler.mobile = (TextView)row.findViewById(R.id.user_mobile);
            layoutHandler.email = (TextView)row.findViewById(R.id.user_email);
            row.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler)row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.name.setText(dataProvider.getName());
        layoutHandler.mobile.setText(dataProvider.getMob());
        layoutHandler.email.setText(dataProvider.getEmail());

        return row;
    }
}
