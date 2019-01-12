package com.example.shu.dbms_project;

/**
 * Created by shubham on 12/16/2016.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jahid on 12/10/15.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Bundle b = getArguments();
        int s = b.getInt("id");
        Date da;
        TextView tv1= (TextView) getActivity().findViewById(s);
        int m=view.getMonth()+1;
        String s3=view.getYear()+"-"+m+"-"+view.getDayOfMonth();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            da = dateFormat.parse(s3);

            if (new Date().before(da)){
            tv1.setText(s3);}
            else{
                Toast.makeText(getActivity(),"Check return date",Toast.LENGTH_LONG).show();
                tv1.setText("");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
}
