package com.example.jeonjin_il.mysecondapp;

/**
 * Created by jeonjin-il on 2016. 12. 30..
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by jeonjin-il on 2016. 12. 28..
 */

public class Adapter_Second extends BaseAdapter {

    ArrayList<WaterList> datas;
    LayoutInflater inflater;
    Context context;
    DBHelper db;
    CustomButtonListener customListner;

    public Adapter_Second(LayoutInflater inflater, ArrayList<WaterList> datas, Context context, CustomButtonListener listener) {
        // TODO Auto-generated constructor stub

        this.datas = datas;
        this.inflater = inflater;
        this.context =context;
        customListner=listener;
    }

    public interface customButtonListener {
        public void onButtonClickListner(int position);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size(); //datas의 개수를 리턴
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub2
        return datas.get(position);//datas의 특정 인덱스 위치 객체 리턴
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_day_item, null);
        }
        ImageView icon = (ImageView)  convertView.findViewById(R.id.listview_icon2);
        TextView text_name = (TextView) convertView.findViewById(R.id.listview_name2);
        TextView text_time = (TextView) convertView.findViewById(R.id.listview_time2);
        TextView text_capacity = (TextView) convertView.findViewById(R.id.listview_capacity2);


        WaterList temp = datas.get(position);

        String capa = String.valueOf(temp.getCapacity()) + "ml";
        text_capacity.setText(capa);
        text_name.setText(temp.getName());
        text_time.setText(temp.getTime());

        if(temp.getWater_id() == 1)
            icon.setImageResource(R.drawable.water_cup_s);
        else if(temp.getWater_id() == 2)
            icon.setImageResource(R.drawable.water_bottle_s);
        else if(temp.getWater_id() == 3)
            icon.setImageResource(R.drawable.coffee_s);
        else if(temp.getWater_id() == 4)
            icon.setImageResource(R.drawable.juice_s);
        else
            icon.setImageResource(R.drawable.milk_s);


        return convertView;
    }


}