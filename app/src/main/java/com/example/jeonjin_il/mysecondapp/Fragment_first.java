package com.example.jeonjin_il.mysecondapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Fragment_first extends Fragment  {

    private DBHelper db;
    private  Handler handler,servicehandler;
    private ListView listview;
    private LayoutInflater inflater;
    private View view;
    private int Nownum = 0,last=0;
    private int Endnum = 2000;
    private TextView board;
    private IconRoundCornerProgressBar progress;
    private FloatingActionButton actionA,actionB,actionC,actionD,actionE;

    public Fragment_first() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_fragment_first, container, false);

        db = new DBHelper(getActivity().getApplicationContext(), "WATER.db", null, 1);
        board = (TextView) view.findViewById(R.id.borad);


        progress = (IconRoundCornerProgressBar) view.findViewById(R.id.first_progress);
        progress.setProgressColor(Color.parseColor("#56d2c2"));
        progress.setProgressBackgroundColor(Color.parseColor("#757575"));
        progress.setIconBackgroundColor(Color.parseColor("#38c0ae"));

        make_listview(1, false);
        actionA = (FloatingActionButton) view.findViewById(R.id.action_a);
        actionB = (FloatingActionButton) view.findViewById(R.id.action_b);
        actionC = (FloatingActionButton) view.findViewById(R.id.action_c);
        actionD = (FloatingActionButton) view.findViewById(R.id.action_d);
        actionE = (FloatingActionButton) view.findViewById(R.id.action_e);
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);
        setActionname();

        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = db.getWaterListString(1);
                Toast.makeText(getActivity(), temp, Toast.LENGTH_LONG).show();
                menuMultipleActions.toggle();
                make_listview(1, true);
            }
        });
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = db.getWaterListString(2);
                Toast.makeText(getActivity(), temp, Toast.LENGTH_LONG).show();
                menuMultipleActions.toggle();
                make_listview(2, true);
            }
        });
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = db.getWaterListString(3);
                Toast.makeText(getActivity(), temp, Toast.LENGTH_LONG).show();
                menuMultipleActions.toggle();
                make_listview(3, true);
            }
        });
        actionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = db.getWaterListString(4);
                Toast.makeText(getActivity(), temp, Toast.LENGTH_LONG).show();
                menuMultipleActions.toggle();
                make_listview(4, true);
            }
        });
        actionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = db.getWaterListString(5);
                Toast.makeText(getActivity(), temp, Toast.LENGTH_LONG).show();
                menuMultipleActions.toggle();
                make_listview(5, true);
            }
        });


        //service
//        servicehandler = new Handler();
//        servicehandler.postDelayed(mMyService,5000);

        return view;
    }

    public void make_listview(int id, boolean insert) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
        String formatTime = new SimpleDateFormat("HH:mm").format(date);
        if (insert)
            db.insert_history(id, formatDate, formatTime);

        final ArrayList<WaterList> datas = db.getDatas(formatDate);

        CustomButtonListener customButtonListener = new CustomButtonListener() {
            @Override
            public void onButtonClickListner(int position,  ArrayList<WaterList> tempdata) {
                make_board(tempdata);
            }

            @Override
            public void onDateClickLinstenr(String date) {

            }

            @Override
            public void onTimeClickLinstenr(String time,boolean type) {

            }
        };
        listview = (ListView) view.findViewById(R.id.main_listview);
        Adapter_First list_adapterFirst = new Adapter_First(inflater, datas, getActivity(),customButtonListener);
        listview.setAdapter(list_adapterFirst);


        make_board(datas);

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

    private Runnable mMyService = new Runnable() {
        public void run() {
            //Service
            getActivity().startService(new Intent(getActivity(), MyService.class));
            getActivity().stopService(new Intent(getActivity(), MyService.class));
//            servicehandler.postDelayed(mMyService,5000);
        }
    };

    public void setActionname(){
        actionA.setTitle(db.getWaterListString(1)+"\n"+db.getWaterListInt(1)+"ml"+"\n"+"수분 : 100%");
        actionB.setTitle(db.getWaterListString(2)+"\n"+db.getWaterListInt(2)+"ml"+"\n"+"수분 : 100%");
        actionC.setTitle(db.getWaterListString(3)+"\n"+db.getWaterListInt(3)+"ml"+"\n"+"수분 : -50%");
        actionD.setTitle(db.getWaterListString(4)+"\n"+db.getWaterListInt(4)+"ml"+"\n"+"수분 : 80%");
        actionE.setTitle(db.getWaterListString(5)+"\n"+db.getWaterListInt(5)+"ml"+"\n"+"수분 : 80%");
    }

}
