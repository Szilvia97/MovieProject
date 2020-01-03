package com.example.movieproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterFragment extends Fragment {

    EditText et_reg_pwd, et_reg_email, et_reg_username;
    TextView tv_login;
    Button btn_reg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        et_reg_email = v.findViewById(R.id.et_reg_email);
        et_reg_pwd = v.findViewById(R.id.et_reg_pwd);
        et_reg_username = v.findViewById(R.id.et_reg_username);
        tv_login = v.findViewById(R.id.tv_login);
        btn_reg = v.findViewById(R.id.btn_reg);

        return v;

    }


}
