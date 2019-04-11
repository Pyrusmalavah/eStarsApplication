package com.pyrusoft.pybot.pysecurity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("register.php")
    Call<User> performRegistration(@Query("f_name") String first_name, @Query("m_name") String middle_name, @Query("s_name") String sur_name, @Query("u_name") String user_name, @Query("p_address") String physical_address, @Query("e_address") String email_address, @Query("mobile") String mobile_no, @Query("emergency_no") String emergency_no, @Query("password") String password, @Query("c_password") String confirm_password);


    @GET("login.php")
    Call<User> performUserLogin(@Query("u_name") String user_name, @Query("password") String password);


}
