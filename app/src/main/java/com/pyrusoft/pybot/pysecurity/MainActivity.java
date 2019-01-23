package com.pyrusoft.pybot.pysecurity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        FloatingActionButton fpol = (FloatingActionButton) findViewById(R.id.fab_pol_emergency);
        FloatingActionButton fmed = (FloatingActionButton) findViewById(R.id.fab_med_emergency);
        FloatingActionButton f_fire = (FloatingActionButton) findViewById(R.id.fab_fire_rescue);


        fpol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Call the Police?", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v, "Calling the police!", Snackbar.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        });

        fmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Call My Doctor?", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Calling the Doctor", Snackbar.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        });


        f_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Call Rescue Team?", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Calling Rescue team", Snackbar.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        });
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        info = (TextView) findViewById(R.id.info);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String itemName = (String) item.getTitle();

        info.setText(itemName);

        closeDrawer();

        switch (item.getItemId()) {
            case R.id.nav_alerts:
                break;
            case R.id.nav_profile:
                break;
            case R.id.nav_track:
                break;
            case R.id.nav_medical:
                break;
            case R.id.nav_family:
                break;
            case R.id.nav_other_services:
                break;
        }

        return true;
    }

    private void closeDrawer() {

        drawerLayout.closeDrawer(GravityCompat.START);

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
        getMenuInflater().inflate(R.menu.right_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.settings) {
            info.setText("Settings");
            return true;
        } else if (id == R.id.notifications) {
            info.setText("Notifications");
            return true;
        } else if (id == R.id.user) {
            info.setText("User");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
