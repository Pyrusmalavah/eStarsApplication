package com.pyrusoft.pybot.pysecurity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login_fragment extends Fragment {

    private TextView RegText;
    private TextInputEditText textInputUsername, textInputPassword;
    private TextInputLayout textUnameLayout, textPasswordLayout;
    private Button BtnLogin;
    private TextView BtnSkip;

//this class needs to communicate with authentication activity

    OnLoginFormActivityListener loginFormActivityListener;

    public interface OnLoginFormActivityListener {
        //define three abstract methods
        public void performRegister();

        public void performLogin(String fname,String mname,String sname,String uname,String email,String mobile,String emergencynum,String phycaddress);

        public void skipLogin();


    }

    public Login_fragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        RegText = view.findViewById(R.id.txt_reg);
        textInputUsername = view.findViewById(R.id.txt_uname);
        textInputPassword = view.findViewById(R.id.txt_password);
        BtnLogin = view.findViewById(R.id.btn_login);
        BtnSkip = view.findViewById(R.id.txt_skip);


        BtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFormActivityListener.skipLogin();
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });


        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginFormActivityListener.performRegister();

            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin() {
        String user_name = textInputUsername.getText().toString();
        String password = textInputPassword.getText().toString();

        Call<User> call = AuthenticationActivity.apiInterface.performUserLogin(user_name, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    AuthenticationActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName(),response.body().getMname(),response.body().getSname(),
                            response.body().getUname(),response.body().getEmail(),response.body().getMobile(),response.body().getEmergency(),
                            response.body().getPhycaddress());
                } else if (response.body().getResponse().equals("failed")) {

                    AuthenticationActivity.prefConfig.displayToast("Incorrect Username or Password");

                    // AuthenticationActivity.prefConfig.writeLoginStatus(true);
                    //  loginFormActivityListener.performLogin(response.body().getName());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        textInputUsername.setText(null);
        textInputPassword.setText(null);


    }



    /*

    private boolean validateEmail() {
        String emailInput = textEmailLayout.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textEmailLayout.setError("Field can't be empty");
            return false;
        } else {
            textEmailLayout.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String emailInput = textPasswordLayout.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textPasswordLayout.setError("Field can't be empty");
            return false;
        } else {
            textPasswordLayout.setError(null);
            return true;
        }
    }


    public void confirmInput(View view) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }
    }
    */

}
