package ru.coursework.coursework.UI;

import android.support.v4.app.Fragment;

import ru.coursework.coursework.UI.fragment.RegestrationFragment;

/**
 * Created by Anton on 25.11.2015.
 */
public class RegistrationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RegestrationFragment();
    }

}
