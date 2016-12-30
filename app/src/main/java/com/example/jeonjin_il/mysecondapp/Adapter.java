package com.example.jeonjin_il.mysecondapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 12. 28..
 */

public class Adapter extends BaseAdapter  {

    ArrayList<WaterList> datas;
    LayoutInflater inflater;
    Context context;
    Button delete_button;
    DBHelper db;
    CustomButtonListener customListner;

    public Adapter(LayoutInflater inflater, ArrayList<WaterList> datas, Context context,CustomButtonListener listener) {
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
            convertView = inflater.inflate(R.layout.activity_wateritem, null);
        }
        delete_button = (Button) convertView.findViewById(R.id.listview_button);
        ImageView icon = (ImageView)  convertView.findViewById(R.id.listview_icon);
        TextView text_name = (TextView) convertView.findViewById(R.id.listview_name);
        TextView text_day = (TextView) convertView.findViewById(R.id.listview_day);
        TextView text_time = (TextView) convertView.findViewById(R.id.listview_time);


        WaterList temp = datas.get(position);

        text_name.setText(temp.getName());
        text_day.setText(temp.getDay());
        text_time.setText(temp.getTime());

        if(temp.getWater_id() == 1)
            icon.setImageResource(R.drawable.water_cup);
        else if(temp.getWater_id() == 2)
            icon.setImageResource(R.drawable.water_bottle);
        else if(temp.getWater_id() == 3)
            icon.setImageResource(R.drawable.coffee);
        else if(temp.getWater_id() == 4)
            icon.setImageResource(R.drawable.juice);
        else
            icon.setImageResource(R.drawable.milk);

        delete_button.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBHelper(context,"WATER.db",null,1);
                db.delete_history(datas.get(position).getId(), datas.get(position).getWater_id() , datas.get(position).getDay());
                datas.remove(position);
                Adapter.this.notifyDataSetChanged();
                customListner.onButtonClickListner(position,datas);
            }
        });
        return convertView;
    }


}