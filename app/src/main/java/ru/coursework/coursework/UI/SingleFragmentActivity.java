package ru.coursework.coursework.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.coursework.coursework.R;
import ru.coursework.coursework.UI.fragment.CustomFragment;
import ru.coursework.coursework.UI.fragment.MakeOrderFragment;
import ru.coursework.coursework.entity.MakeOrderFragmentMemento;
import ru.coursework.coursework.entity.Memento;

/**
 * Created by Anton on 18.10.2015.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract CustomFragment createFragment();

    private Memento state;
    private CustomFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        Fragment tempFragment =  fm.findFragmentById(R.id.fragmentContainer);
        fragment = (CustomFragment) tempFragment;
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

    }

    public void fragmentHasCreated (){

        if (state != null)
            fragment.setMemento(state);

        state = null;
    }

    public void fragmentHasDestrouyed () {

        this.state = fragment.createMemento();

    }
}


