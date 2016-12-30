package com.example.jeonjin_il.mysecondapp;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 12. 28..
 */

public interface CustomButtonListener {
    public void onButtonClickListner(int position,ArrayList<WaterList> datas);
    public void onDateClickLinstenr(String date);
    public void onTimeClickLinstenr(String time,boolean type);
}
