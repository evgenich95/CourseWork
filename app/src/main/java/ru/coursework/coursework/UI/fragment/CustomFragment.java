package ru.coursework.coursework.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.coursework.coursework.UI.SingleFragmentActivity;
import ru.coursework.coursework.entity.MakeOrderFragmentMemento;
import ru.coursework.coursework.entity.Memento;

/**
 * Created by Anton on 21.02.2016.
 */
public abstract class CustomFragment extends Fragment {

    public abstract Memento createMemento ();

    public abstract void setMemento(  Memento state);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SingleFragmentActivity) this.getActivity()).fragmentHasCreated();
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {

        ((SingleFragmentActivity) this.getActivity()).fragmentHasDestrouyed();

        super.onDestroy();
    }
}
