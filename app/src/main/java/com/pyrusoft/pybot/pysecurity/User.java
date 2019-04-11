package com.pyrusoft.pybot.pysecurity;

import com.google.gson.annotations.SerializedName;

public class User {

      @SerializedName("response")
    private String Response;

    @SerializedName("f_name")
    private String Fname;


    @SerializedName("s_name")
    private String Sname;

    @SerializedName("e_address")
    private String Email;



    @SerializedName("m_name")
    private String Mname;

    @SerializedName("u_name")
    private String Uname;


    @SerializedName("mobile")
    private String Mobile;

    @SerializedName("emergency_no")
    private String Emergency;

    @SerializedName("p_address")
    private String Phycaddress;



    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Fname;
    }


    public String getSname() {
        return Sname;
    }

    public String getEmail() {
        return Email;
    }



    public String getMname() {
        return Mname;
    }

    public String getUname() {
        return Uname;
    }


    public String getMobile() {
        return Mobile;
    }

    public String getEmergency() {
        return Emergency;
    }

    public String getPhycaddress(){return Phycaddress;
    }

}
