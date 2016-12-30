package com.example.jeonjin_il.mysecondapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentTransaction transaction;
    private DBHelper db;
    private Handler handler;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("종료 확인 대화 상자")
                .setMessage("앱을 종료 하시 겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.jeonjin_il.mysecondapp.R.layout.activity_main);
        //DB
        db = new DBHelper(this, "WATER.db", null, 1);
        db.init();


        //BottomNavigation
        BottomNavigation bottomNavigation = (BottomNavigation) findViewById(com.example.jeonjin_il.mysecondapp.R.id.bottom_navigation);
        bottomNavigation.setDefaultItem((byte) 2);
        setDefault();
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {

                switch (itemId) {
                    case com.example.jeonjin_il.mysecondapp.R.id.tab_first:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(com.example.jeonjin_il.mysecondapp.R.id.frame_fragment_containers, new Fragment_first());
                        break;
                    case com.example.jeonjin_il.mysecondapp.R.id.tab_second:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(com.example.jeonjin_il.mysecondapp.R.id.frame_fragment_containers, new Fragment_second());
                        break;
                    case com.example.jeonjin_il.mysecondapp.R.id.tab_third:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(com.example.jeonjin_il.mysecondapp.R.id.frame_fragment_containers, new Fragment_third());
                        break;

                }
                transaction.commit();
            }
        });

        //광고
       AdView mAdView = (AdView) findViewById(com.example.jeonjin_il.mysecondapp.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }
    public void setDefault(){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(com.example.jeonjin_il.mysecondapp.R.id.frame_fragment_containers,new Fragment_first());
        transaction.commit();
    }


}

