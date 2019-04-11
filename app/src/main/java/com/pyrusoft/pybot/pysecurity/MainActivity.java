package com.pyrusoft.pybot.pysecurity;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DashboardFragment.OnDashboardPageActivityListener {

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String TAG = "TrackActivity";
    private static final int REQUEST_CALL = 1;
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;


    DrawerLayout drawerLayout;
    TextView info, usrnme_info;
    private Button BtnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefConfig = new PrefConfig(this);


        final FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {


                switch (menuItem.getItemId()) {
                    case R.id.police_call:
                        Toast.makeText(MainActivity.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        callPolice();
                        break;
                    case R.id.med_call:
                        Toast.makeText(MainActivity.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        callMedical();
                        break;
                    case R.id.rescue_call:
                        Toast.makeText(MainActivity.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        callRescue();
                        break;

                }


                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        info = (TextView) findViewById(R.id.info);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeaderView = navigationView.getHeaderView(0);
        ImageView profilePhoto = (ImageView) navigationHeaderView.findViewById(R.id.displayPhoto);
        TextView firstName = (TextView) navigationHeaderView.findViewById(R.id.username_info1);
        TextView secondName = (TextView) navigationHeaderView.findViewById(R.id.username_info2);
        TextView userEmail = (TextView) navigationHeaderView.findViewById(R.id.user_mail);
        firstName.setText(AuthenticationActivity.prefConfig.readName());
        secondName.setText(AuthenticationActivity.prefConfig.readSname());
        userEmail.setText(AuthenticationActivity.prefConfig.readEmail());


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new DashboardFragment()).addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            //all is cool, the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occurred but we can fix it
            Log.d(TAG, "isServicesOK: an error occurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        /*
        String itemName = (String) item.getTitle();

        info.setText(itemName);

*/
        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new DashboardFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_faq:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new FaqFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new HelpFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new AboutUsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_tac:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new TacFragment()).addToBackStack(null).commit();
                break;
        }
        closeDrawer();
        return true;
    }

    private void closeDrawer() {

        drawerLayout.closeDrawer(GravityCompat.START);

    }


    public void callPolice() {
        String police = "0799035009";
        if (police.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dialPolice = police;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dialPolice));
                startActivity(intent);
            }
        } else {
            Toast.makeText(MainActivity.this, "Police Number not found", Toast.LENGTH_SHORT).show();
        }

    }


    public void callMedical() {
        String medical = "0706706705";
        if (medical.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dialMedical = medical;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dialMedical));
                startActivity(intent);
            }
        } else {
            Toast.makeText(MainActivity.this, "Medical Number not found", Toast.LENGTH_SHORT).show();
        }


    }


    public void callRescue() {

        String rescue = "0725849938";
        if (rescue.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dialRescue = rescue;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dialRescue));
                startActivity(intent);
            }
        } else {
            Toast.makeText(MainActivity.this, "Fire & Rescue Number not found", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPolice();
                callMedical();
                callRescue();
            }
        } else {
            Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
        }
    }


    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        }
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.right_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout:
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                prefConfig.writeLoginStatus(false);
                prefConfig.writeName("User");
                prefConfig.writeSname("Name");
                prefConfig.writeEmail("Mail");
                prefConfig.writeThirdname("MidName");
                prefConfig.writeUsername("UserName");
                prefConfig.writeMobilenum("Phone");
                prefConfig.writeEmergencynum("EmergencyContact");
                prefConfig.writePhycaddress("MyAddress");
                Intent landIntent = new Intent(MainActivity.this, AuthenticationActivity.class);
                startActivity(landIntent);
                finish();
                return true;

            case R.id.feedback:
                Toast.makeText(this, "No feedback", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.notifications:
                Toast.makeText(this, "No notifications", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    @Override
    public void viewAlerts() {
        Intent landIntent = new Intent(MainActivity.this, AlertActivity.class);
        startActivity(landIntent);
        finish();
    }

    @Override
    public void viewProfile() {
        Intent landIntent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(landIntent);
        finish();
    }

    @Override
    public void viewMedprofile() {
        Intent landIntent = new Intent(MainActivity.this, MedActivity.class);
        startActivity(landIntent);
        finish();
    }

    @Override
    public void viewFamily() {
        Intent landIntent = new Intent(MainActivity.this, FamActivity.class);
        startActivity(landIntent);
        finish();
    }

    @Override
    public void viewTrack() {
        if (isServicesOK()) {
            Intent landIntent = new Intent(MainActivity.this, TrackActivity.class);
            startActivity(landIntent);
            finish();
        }
    }

    @Override
    public void viewCctv() {
        Intent landIntent = new Intent(MainActivity.this, CctvActivity.class);
        startActivity(landIntent);
        finish();

    }


}
