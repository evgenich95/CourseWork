package ru.coursework.coursework.UI;

import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.View;

import ru.coursework.coursework.UI.fragment.ListOrderFragmentI;

/**
 * Created by Anton on 18.10.2015.
 */
public class ListOrderActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new ListOrderFragmentI();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }
}


