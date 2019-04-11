package com.pyrusoft.pybot.pysecurity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.apply();
    }

    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String f_name) {
        SharedPreferences.Editor editorfnme = sharedPreferences.edit();
        editorfnme.putString(context.getString(R.string.pref_first_name), f_name);
        editorfnme.apply();
    }


    public String readName() {
        return sharedPreferences.getString(context.getString(R.string.pref_first_name), "FirstName");
    }

    public void writeSname(String s_name) {
        SharedPreferences.Editor editorsnme = sharedPreferences.edit();
        editorsnme.putString(context.getString(R.string.pref_second_name), s_name);
        editorsnme.apply();
    }

    public String readSname() {
        return sharedPreferences.getString(context.getString(R.string.pref_second_name), "SecondName");
    }

    public void writeEmail(String e_address) {
        SharedPreferences.Editor editormail = sharedPreferences.edit();
        editormail.putString(context.getString(R.string.pref_email_add), e_address);
        editormail.apply();
    }

    public String readEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_email_add), "EmailAddress");
    }


    public void writeUsername(String u_name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name), u_name);
        editor.apply();
    }

    public String readUsername() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_name), "Username");
    }

    public void writeThirdname(String m_name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_mid_name), m_name);
        editor.apply();
    }

    public String readThirdname() {
        return sharedPreferences.getString(context.getString(R.string.pref_mid_name), "MidName");
    }

    public void writeMobilenum(String mobile_num) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_mob_num), mobile_num);
        editor.apply();
    }

    public String readMobilenum() {
        return sharedPreferences.getString(context.getString(R.string.pref_mob_num), "MobileNumber");
    }


    public void writeEmergencynum(String emergency_no) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_emg_num), emergency_no);
        editor.apply();
    }

    public String readEmergencynum() {
        return sharedPreferences.getString(context.getString(R.string.pref_emg_num), "EmergencyContact");
    }

    public void writePhycaddress(String p_address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_phyc_address), p_address);
        editor.apply();
    }

    public String readPhycaddress() {
        return sharedPreferences.getString(context.getString(R.string.pref_phyc_address), "PermanentAddress");
    }

    public void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
