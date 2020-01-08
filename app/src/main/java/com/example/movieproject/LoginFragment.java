package com.example.movieproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieproject.Model.User;


public class LoginFragment extends Fragment {

    EditText et_login_pwd, et_login_email;
    TextView tv_reg;
    Button btn_login;
    SqliteHelper sqliteHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        et_login_email = v.findViewById(R.id.et_login_email);
        et_login_pwd = v.findViewById(R.id.et_login_pwd);
        tv_reg = v.findViewById(R.id.tv_reg);
        btn_login = v.findViewById(R.id.btn_login);

        sqliteHelper = new SqliteHelper(getContext());

        btn_login.setOnClickListener(onClickListenerLogin);
        tv_reg.setOnClickListener(onClickListenerReg);

        return v;
    }

    private View.OnClickListener onClickListenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sqliteHelper = new SqliteHelper(getContext());
            if (validate()) {

                String Email = et_login_email.getText().toString();
                String Password = et_login_pwd.getText().toString();

                User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                if (currentUser != null) {
                    Toast.makeText(getContext(), "Successfully Logged in!", Toast.LENGTH_LONG).show();
                    Fragment fragment = new HomeFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_id, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {
                    Toast.makeText(getContext(), "Failed to log in , please try again", Toast.LENGTH_LONG).show();

                }
            }
        }
    };

    private View.OnClickListener onClickListenerReg = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                    Fragment fragment = new RegisterFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_id, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

        }
    };

    public boolean validate() {
        boolean valid = false;

        String Email = et_login_email.getText().toString();
        String Password = et_login_pwd.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            et_login_email.setError("Please enter valid email!");
        } else {
            valid = true;
            et_login_email.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            et_login_pwd.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                et_login_pwd.setError(null);
            } else {
                valid = false;
                et_login_pwd.setError("Password is to short!");
            }
        }

        return valid;
    }
}
