package ru.coursework.coursework;

import android.support.v4.app.Fragment;

/**
 * Created by Anton on 19.11.2015.
 */
public class MakeOrderActivity extends  SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MakeOrderFragment();
    }
}
