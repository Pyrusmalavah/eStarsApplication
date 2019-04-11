package com.pyrusoft.pybot.pysecurity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AuthenticationActivity extends AppCompatActivity implements Login_fragment.OnLoginFormActivityListener {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {

                return;

            }
            if (prefConfig.readLoginStatus()) {
                startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Login_fragment()).commit();
            }
        }
    }

    @Override
    public void skipLogin() {
        Intent landIntent = new Intent(AuthenticationActivity.this, MainActivity.class);
        startActivity(landIntent);
        finish();
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String fname,String mname, String sname,String uname, String email,String mobile,String emergencynum,String phycaddress) {
        prefConfig.writeName(fname);
        prefConfig.writeThirdname(mname);
        prefConfig.writeSname(sname);
        prefConfig.writeUsername(uname);
        prefConfig.writeEmail(email);
        prefConfig.writeMobilenum(mobile);
        prefConfig.writeEmergencynum(emergencynum);
        prefConfig.writePhycaddress(phycaddress);
        Intent landIntent = new Intent(AuthenticationActivity.this, MainActivity.class);
        startActivity(landIntent);
        finish();
    }
}
