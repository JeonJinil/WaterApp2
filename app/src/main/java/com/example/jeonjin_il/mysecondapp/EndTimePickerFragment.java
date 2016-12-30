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

public class EndTimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public EndTimePickerFragment() {
        // Required empty public constructor
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
        TextView tv = (TextView) getActivity().findViewById(R.id.third_end_text);
        //Set a message for user
        tv.setText("서비스 종료시간 : ");
        //Display the user changed time on TextView
        tv.setText(tv.getText()+ "Hour : " + String.valueOf(hourOfDay)
                + "\nMinute : " + String.valueOf(minute) + "\n");
    }
}
