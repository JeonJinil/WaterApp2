package com.example.jeonjin_il.mysecondapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.google.android.gms.internal.a.R;

public class Fragment_second extends Fragment {
    private View view;
    private TextView textView,textView2;

    DBHelper db;
    public Fragment_second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DBHelper(getActivity().getApplicationContext(), "WATER.db", null, 1);

        view = inflater.inflate(com.example.jeonjin_il.mysecondapp.R.layout.fragment_fragment_second,container,false);
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


        // Inflate the layout for this fragment
        return view;
    }


}
