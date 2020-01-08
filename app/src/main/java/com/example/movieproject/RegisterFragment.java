package com.example.movieproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieproject.Model.User;


public class RegisterFragment extends Fragment {

    EditText et_reg_pwd, et_reg_email, et_reg_username;
    TextView tv_login;
    Button btn_reg;
    SqliteHelper sqliteHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        et_reg_email = v.findViewById(R.id.et_reg_email);
        et_reg_pwd = v.findViewById(R.id.et_reg_pwd);
        et_reg_username = v.findViewById(R.id.et_reg_username);
        tv_login = v.findViewById(R.id.tv_login);
        btn_reg = v.findViewById(R.id.btn_reg);

        sqliteHelper = new SqliteHelper(getContext());

        btn_reg.setOnClickListener(onClickListenerRegister);
        tv_login.setOnClickListener(onClickListenerLogin);

        return v;

    }

    private View.OnClickListener onClickListenerRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sqliteHelper = new SqliteHelper(getContext());

            if (validate()) {
                String UserName = et_reg_username.getText().toString();
                String Email = et_reg_email.getText().toString();
                String Password = et_reg_pwd.getText().toString();

                if (!sqliteHelper.isEmailExists(Email)) {

                    sqliteHelper.addUser(new User(null, UserName, Email, Password));
                    Toast.makeText(getContext(), "User created successfully! Please Login ", Toast.LENGTH_LONG).show();

                    Fragment fragment = new LoginFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_id, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {

                    Toast.makeText(getContext(), "User already exists with same email ", Toast.LENGTH_LONG).show();
                }


            }
        }
    };

    private View.OnClickListener onClickListenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Fragment fragment = new LoginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_id, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    };



    public boolean validate() {
        boolean valid = false;

        String UserName = et_reg_username.getText().toString();
        String Email = et_reg_email.getText().toString();
        String Password = et_reg_pwd.getText().toString();

        if (UserName.isEmpty()) {
            valid = false;
            et_reg_username.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                et_reg_username.setError(null);
            } else {
                valid = false;
                et_reg_username.setError("Username is to short!");
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            et_reg_email.setError("Please enter valid email!");
        } else {
            valid = true;
            et_reg_email.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            et_reg_pwd.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                et_reg_pwd.setError(null);
            } else {
                valid = false;
                et_reg_pwd.setError("Password is to short!");
            }
        }

        return valid;
    }
}