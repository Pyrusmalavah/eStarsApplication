package com.pyrusoft.pybot.pysecurity;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login_fragment extends Fragment {

private TextInputLayout textInputEmail;
private TextInputLayout textInputPassword;
    public Login_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);

        textInputEmail = view.findViewById(R.id.txt_email);
        textInputPassword = view.findViewById(R.id.txt_password);

        return view;
    }

    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            textInputEmail.setError("Field can't be empty");
            return false;
        }else {
            textInputEmail.setError(null);
            textInputEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String emailInput = textInputPassword.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            textInputPassword.setError("Field can't be empty");
            return false;
    }else {
        textInputPassword.setError(null);
        return true;
        }
    }

    public void confirmInput(View view){
        if(!validateEmail()|!validatePassword()){
            return;
        }
    }

}
