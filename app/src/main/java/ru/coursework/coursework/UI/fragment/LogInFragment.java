package ru.coursework.coursework.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.coursework.coursework.R;
import ru.coursework.coursework.UI.ListOrderActivity;
import ru.coursework.coursework.UI.RegestrationActivity;
import ru.coursework.coursework.entity.Memento;

/**
 * Created by Anton on 24.11.2015.
 */
public class LogInFragment extends CustomFragment {

    Button mLoginButton;
    Button mRegestrationButton;

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
        View  registration_view = inflater.inflate(R.layout.log_in, container,false);



        mLoginButton = (Button) registration_view.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOrderActivity.class);
                startActivity(i);
            }
        });

        mRegestrationButton = (Button) registration_view.findViewById(R.id.regestration_button);
        mRegestrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RegestrationActivity.class);
                startActivity(i);
            }
        });

        return  registration_view;


    }
}
