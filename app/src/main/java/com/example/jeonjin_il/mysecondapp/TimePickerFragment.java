package com.example.jeonjin_il.mysecondapp;

/**
 * Created by jeonjin-il on 2016. 12. 30..
 */
 import android.app.Dialog;
 import android.app.TimePickerDialog;
 import android.os.Bundle;
 import android.support.v4.app.DialogFragment;
 import android.text.format.DateFormat;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import java.util.Calendar;

/**
 * Created by jeonjin-il on 2016. 12. 30..
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    CustomButtonListener customListner;
    int type = 0;
    public TimePickerFragment() {
        // Required empty public constructor
    }

    public TimePickerFragment(int type , CustomButtonListener listener){
        customListner = listener;
        this.type = type;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView end_tv = (TextView) getActivity().findViewById(R.id.third_end_text);
        TextView start_tv = (TextView) getActivity().findViewById(R.id.third_start_text);
        String time = String.valueOf(hourOfDay) +":"+ String.valueOf(minute) ;
        if(type == 1) {
            start_tv.setText(time);
            customListner.onTimeClickLinstenr(time,true);
        }else if(type ==2 ){
            end_tv.setText(time);
            customListner.onTimeClickLinstenr(time,false);
        }
    }
}
