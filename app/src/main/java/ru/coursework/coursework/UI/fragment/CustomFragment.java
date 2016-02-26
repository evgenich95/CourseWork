package ru.coursework.coursework.UI.fragment;

import android.support.v4.app.Fragment;

import ru.coursework.coursework.entity.IMemento;

/**
 * Created by Anton on 21.02.2016.
 */
public abstract class CustomFragment extends Fragment {

    public abstract IMemento createMemento ();

    public abstract void setMemento(IMemento state);


}
