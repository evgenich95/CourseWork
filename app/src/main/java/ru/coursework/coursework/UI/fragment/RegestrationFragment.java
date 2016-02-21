package ru.coursework.coursework.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.coursework.coursework.R;
import ru.coursework.coursework.UI.LogInActivity;
import ru.coursework.coursework.entity.Memento;

/**
 * Created by Anton on 25.11.2015.
 */
public class RegestrationFragment extends CustomFragment {

    @Override
    public Memento createMemento() {
        return null;
    }

    @Override
    public void setMemento(Memento state) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regestration_layout,null);

        TextView memberLogin = (TextView) view.findViewById(R.id.member_login_TextView);
        memberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),LogInActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
