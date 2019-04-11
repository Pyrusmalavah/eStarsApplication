package com.pyrusoft.pybot.pysecurity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class DashboardFragment extends Fragment {
    private Button profile_btn, medprofile_btn, alert_btn, family_btn, track_btn, cctv_btn;
    ViewFlipper viewFlipper;


    OnDashboardPageActivityListener dashboardPageActivityListener;

    public interface OnDashboardPageActivityListener {
        //define abstract methods
        public void viewAlerts();

        public void viewProfile();

        public void viewMedprofile();

        public void viewFamily();

        public void viewTrack();

        public void viewCctv();


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        profile_btn = v.findViewById(R.id.btn_profile);
        medprofile_btn = v.findViewById(R.id.btn_Med);
        family_btn = v.findViewById(R.id.btn_Family);
        alert_btn = v.findViewById(R.id.btn_Alerts);
        track_btn = v.findViewById(R.id.btn_Track);
        cctv_btn = v.findViewById(R.id.btn_MyEyes);


        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPageActivityListener.viewProfile();
            }
        });
        medprofile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPageActivityListener.viewMedprofile();
            }
        });
        alert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPageActivityListener.viewAlerts();
            }
        });
        cctv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPageActivityListener.viewCctv();
            }
        });
        track_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPageActivityListener.viewTrack();
            }
        });
        family_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardPageActivityListener.viewFamily();
            }
        });


        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        dashboardPageActivityListener = (OnDashboardPageActivityListener) activity;
    }

}
