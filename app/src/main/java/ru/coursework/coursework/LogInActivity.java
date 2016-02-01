package ru.coursework.coursework;

import android.support.v4.app.Fragment;

/**
 * Created by Anton on 24.11.2015.
 */
public class LogInActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LogInFragment();
    }
}

