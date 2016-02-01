package ru.coursework.coursework;

import android.support.v4.app.Fragment;

/**
 * Created by Anton on 25.11.2015.
 */
public class RegestrationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RegestrationFragment();
    }
}
