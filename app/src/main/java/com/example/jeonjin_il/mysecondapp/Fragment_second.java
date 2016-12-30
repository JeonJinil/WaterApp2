package com.example.jeonjin_il.mysecondapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;

import java.util.ArrayList;

public class Fragment_second extends Fragment {
    private IconRoundCornerProgressBar progress;
    private  Handler handler;
    private TextView board;
    Button date_button;
    ListView listview;
    View view;
    CustomButtonListener customButtonListener;
    DBHelper db;
    private int Nownum = 0,last=0;
    private int Endnum = 2000;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(com.example.jeonjin_il.mysecondapp.R.layout.fragment_fragment_second,container,false);
        db = new DBHelper(getActivity().getApplicationContext(), "WATER.db", null, 1);
        board = (TextView)view.findViewById(R.id.second_borad);

        progress = (IconRoundCornerProgressBar) view.findViewById(R.id.first_progress);
        progress.setProgressColor(Color.parseColor("#56d2c2"));
        progress.setProgressBackgroundColor(Color.parseColor("#757575"));
        progress.setIconBackgroundColor(Color.parseColor("#38c0ae"));


        customButtonListener = new CustomButtonListener() {
            @Override
            public void onButtonClickListner(int position,  ArrayList<WaterList> tempdata) {

            }
            @Override
            public void onDateClickLinstenr(String date) {
                final ArrayList<WaterList> datas = db.getDatas(date);

                listview = (ListView) view.findViewById(R.id.second_listview);
                Adapter_Second list_adapterFirst = new Adapter_Second(inflater, datas, getActivity(),customButtonListener);
                listview.setAdapter(list_adapterFirst);

                make_board(datas);
            }

            @Override
            public void onTimeClickLinstenr(String time,boolean type) {

            }
        };


        date_button = (Button) view.findViewById(R.id.second_start_date_button);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment(customButtonListener);
                newFragment.show(getFragmentManager(),"DatePicker");
            }
        });




        // Inflate the layout for this fragment
        return view;
    }


    public void make_board(ArrayList<WaterList> datas){

        Nownum = 0;
        for (int i = 0; i < datas.size(); i++) {
            Nownum += (int) (datas.get(i).getCapacity() * (datas.get(i).getPercentage() / 100f));
        }

        handler = new Handler();
        handler.postDelayed(mMyTask,1);


    }

    private Runnable mMyTask = new Runnable() {
        public void run() {
            Endnum = (int)db.getKG()*30;
            if(last < Nownum) {
                progress.setMax(Endnum);
                progress.setProgress(last);
                last+=1;
                handler.postDelayed(this, 1);
                String temp = String.valueOf(last) + " / " + String.valueOf(Endnum);
                board.setText(temp);

            }
            else if(last > Nownum) {
                progress.setMax(Endnum);
                progress.setProgress(last);
                last-=1;
                String temp = String.valueOf(last) + " / " + String.valueOf(Endnum);
                board.setText(temp);
                handler.postDelayed(this,1);
            }
            else{
                String temp = String.valueOf(Nownum) + " / " + String.valueOf(Endnum);
                board.setText(temp);
            }

        }
    };

}
