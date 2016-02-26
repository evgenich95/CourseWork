package ru.coursework.coursework.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.coursework.coursework.R;
import ru.coursework.coursework.UI.fragment.ISaveStateFragment;
import ru.coursework.coursework.entity.IMemento;

/**
 * Created by Anton on 18.10.2015.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    private final static String KEY_STATE = "KeyState";

    protected abstract Fragment createFragment();
    protected IMemento state;
    private Fragment fragment;
    private ISaveStateFragment originator;
    protected Boolean needSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        fragment =  fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

        //определяем, нужно ли сохранять состояние управляемого фрагмента
        if (fragment instanceof ISaveStateFragment) {
            needSave = true;
            originator = (ISaveStateFragment) fragment;

            //если мы что-то сохранили в пакет аргументов класса
            if (savedInstanceState != null) {
                this.state = (IMemento) savedInstanceState.getSerializable(KEY_STATE);
                RestoreState();
            }
        }
        else {
            needSave = false;
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (needSave) {
            SaveState();
            outState.putSerializable(KEY_STATE, this.state);
        }

        super.onSaveInstanceState(outState);
    }


    public void RestoreState(){

        if (this.state != null) {
            originator.setMemento(this.state);
            this.state = null;
        }
    }
    public void SaveState() {

        this.state = originator.createMemento();

    }
}


