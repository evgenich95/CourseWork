package ru.coursework.coursework.UI.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ru.coursework.coursework.R;

/**
 * Created by Anton on 20.11.2015.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE= "com.android.coursework.date";
    private static final String ARG_DATE = "date";
    private static final String CURRENT_ORDER = "current_order";
    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(Date date){

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void SendResult (int resultCode, Date date){
        if (getTargetFragment()==null)
            return;



        Intent i = new Intent();
        i.putExtra(EXTRA_DATE,date);


        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode,i);


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.alert_dialog,null);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_datepicker);
        mDatePicker.init(year,month,day,null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year,month,day).getTime();
                       SendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }
}
