package com.pyrusoft.pybot.pysecurity;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private TextInputEditText F_Name, M_Name, S_Name, U_Name,Phyc_Add, Email_Add, Mobile, emergency, pass, confirm_pass;
    private Button BtnRegister;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        F_Name = v.findViewById(R.id.txt_fname);
        M_Name = v.findViewById(R.id.txt_sname);
        S_Name = v.findViewById(R.id.txt_lname);
        U_Name = v.findViewById(R.id.txt_uname);
        Phyc_Add = v.findViewById(R.id.p_address);
        Email_Add = v.findViewById(R.id.e_address);
        Mobile = v.findViewById(R.id.mobile);
        emergency = v.findViewById(R.id.emergency);
        pass = v.findViewById(R.id.txt_password);
        confirm_pass = v.findViewById(R.id.txt_cpassword);

        BtnRegister = v.findViewById(R.id.btn_register);


        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performRegistration();
            }
        });

        return v;
    }


    public void performRegistration() {
        String first_name = F_Name.getText().toString();
        String midlle_name = M_Name.getText().toString();
        String sur_name = S_Name.getText().toString();
        String user_name = U_Name.getText().toString();
        String physical_address = Phyc_Add.getText().toString();
        String email_address = Email_Add.getText().toString();
        String mobile_no = Mobile.getText().toString();
        String emergency_no = emergency.getText().toString();
        String password = pass.getText().toString();
        String confirm_password = confirm_pass.getText().toString();

        Call<User> call = AuthenticationActivity.apiInterface.performRegistration(first_name, midlle_name, sur_name,user_name, physical_address, email_address, mobile_no, emergency_no, password, confirm_password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok")) {
                    AuthenticationActivity.prefConfig.displayToast("Registration Successful");
                } else if (response.body().getResponse().equals("exist")) {
                    AuthenticationActivity.prefConfig.displayToast("User already exists");
                } else if (response.body().getResponse().equals("error")) {
                    AuthenticationActivity.prefConfig.displayToast("Problem connecting to server");
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        F_Name.setText(null);
        M_Name.setText(null);
        S_Name.setText(null);
        U_Name.setText(null);
        Phyc_Add.setText(null);
        Email_Add.setText(null);
        Mobile.setText(null);
        emergency.setText(null);
        pass.setText(null);
        confirm_pass.setText(null);


    }

}
