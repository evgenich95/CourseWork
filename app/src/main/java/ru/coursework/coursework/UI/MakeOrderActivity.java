package ru.coursework.coursework.UI;

import android.support.v4.app.Fragment;

import ru.coursework.coursework.UI.fragment.MakeOrderFragment;

/**
 * Created by Anton on 19.11.2015.
 */
public class MakeOrderActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MakeOrderFragment();
    }
}
