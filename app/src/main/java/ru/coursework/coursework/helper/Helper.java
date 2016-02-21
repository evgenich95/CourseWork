package ru.coursework.coursework.helper;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anton on 29.11.2015.
 */
public class Helper {


    static public void updateTextViewbyDate(TextView text, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-dd-MMMM", Locale.getDefault());
        String stroka=sdf.format(date);
        text.setText(stroka);

    }

    static public void changeViewVisible (ArrayList<View> viewArrayList, int onVisible){
        for (View v: viewArrayList
             ) {v.setVisibility(onVisible);

        }
    }


}
