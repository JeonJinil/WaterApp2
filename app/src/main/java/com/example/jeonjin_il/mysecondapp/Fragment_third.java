package com.example.jeonjin_il.mysecondapp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class Fragment_third extends Fragment {
    private TextView textView,textView2;
    CustomButtonListener customButtonListener;
    DBHelper db;
    Button date_button,time_start_button,time_end_button;
    TextView start_text, end_text;
    public Fragment_third() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(com.example.jeonjin_il.mysecondapp.R.layout.fragment_fragment_third, container, false);
        db = new DBHelper(getActivity().getApplicationContext(), "WATER.db", null, 1);
        start_text = (TextView) view.findViewById(R.id.third_start_text);
        end_text = (TextView) view.findViewById(R.id.third_end_text);

        String start_time  = db.get_startTime();
        String end_time = db.get_endTime();

        start_text.setText(start_time);
        end_text.setText(end_time);

        customButtonListener = new CustomButtonListener() {
            @Override
            public void onButtonClickListner(int position,  ArrayList<WaterList> tempdata) {
            }

            @Override
            public void onDateClickLinstenr(String date) {

            }

            @Override
            public void onTimeClickLinstenr(String time,boolean type) {
                if(type)
                    db.Set_startTime(time);
                else
                    db.Set_endTime(time);
            }
        };


        time_start_button = (Button) view.findViewById(R.id.third_start_time_button);
        time_end_button = (Button) view.findViewById(R.id.third_end_time_button);


        time_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(1,customButtonListener);
                newFragment.show(getFragmentManager(),"TimePicker");
            }
        });

        time_end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(2,customButtonListener);
                newFragment.show(getFragmentManager(),"TimePicker");
            }
        });


        ///////////////

        db = new DBHelper(getActivity().getApplicationContext(), "WATER.db", null, 1);

        textView = (TextView) view.findViewById(com.example.jeonjin_il.mysecondapp.R.id.second_text);
        textView2 = (TextView) view.findViewById(com.example.jeonjin_il.mysecondapp.R.id.second_text2);

        Button button = (Button) view.findViewById(com.example.jeonjin_il.mysecondapp.R.id.second_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String temp = textView.getText().toString();
                final int temp2 = Integer.parseInt(textView2.getText().toString());

                db.changeWater(1,temp,temp2);

            }
        });



        return view;
    }


}
