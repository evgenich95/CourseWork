package ru.coursework.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Anton on 24.11.2015.
 */
public class LogInFragment extends Fragment {

    Button mLoginButton;
    Button mRegestrationButton;

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
