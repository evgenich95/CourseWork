package ru.coursework.coursework.UI;

import android.support.v4.app.Fragment;

import ru.coursework.coursework.UI.fragment.CustomFragment;
import ru.coursework.coursework.UI.fragment.RegestrationFragment;

/**
 * Created by Anton on 25.11.2015.
 */
public class RegestrationActivity extends SingleFragmentActivity {

    @Override
    protected CustomFragment createFragment() {
        return new RegestrationFragment();
    }
}
