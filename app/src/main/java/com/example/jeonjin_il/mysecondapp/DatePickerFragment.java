package com.example.jeonjin_il.mysecondapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by jeonjin-il on 2016. 12. 30..
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    CustomButtonListener customListner;
    public DatePickerFragment(){

    }
    public DatePickerFragment(CustomButtonListener listener){
        customListner = listener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);



    return new DatePickerDialog(getActivity(), this, year, month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView tv = (TextView) getActivity().findViewById(R.id.second_date_text);
        //Set a message for user
        tv.setText("고르신 날짜는 : ");
        //Display the user changed time on TextView
        String data=   String.valueOf(year) +"/"+ String.valueOf(month+1) + "/" + String.valueOf(dayOfMonth) ;
        tv.setText(data);
        customListner.onDateClickLinstenr(data);
    }


}