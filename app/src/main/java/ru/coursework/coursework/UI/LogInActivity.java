package ru.coursework.coursework.UI;

import android.support.v4.app.Fragment;

import ru.coursework.coursework.UI.fragment.LogInFragment;

/**
 * Created by Anton on 24.11.2015.
 */
public class LogInActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LogInFragment();
    }

}

