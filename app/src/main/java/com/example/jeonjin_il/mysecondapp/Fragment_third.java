package com.example.jeonjin_il.mysecondapp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment_third extends Fragment {

    Button date_button,time_start_button,time_end_button;
    public Fragment_third() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(com.example.jeonjin_il.mysecondapp.R.layout.fragment_fragment_third, container, false);

        date_button = (Button) view.findViewById(R.id.third_start_date_button);
        time_start_button = (Button) view.findViewById(R.id.third_start_time_button);
        time_end_button = (Button) view.findViewById(R.id.third_end_time_button);

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"DatePicker");
            }
        });

        time_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new StartTimePickerFragment();
                newFragment.show(getFragmentManager(),"TimePicker");
            }
        });

        time_end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new EndTimePickerFragment();
                newFragment.show(getFragmentManager(),"TimePicker");
            }
        });




        return view;
    }


}
