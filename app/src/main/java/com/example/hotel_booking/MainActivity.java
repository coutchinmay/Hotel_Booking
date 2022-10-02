package com.example.hotel_booking;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        TextView textView = (TextView)toolbar.findViewById(R.id.toolbarTextView);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragmnet()).commit();
            navigationView.setCheckedItem(R.id.home_menu);
        }

        mAuth = FirebaseAuth.getInstance();

        String email = mAuth.getCurrentUser().getEmail();
        View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
        ((TextView) nav_header.findViewById(R.id.heading)).setText(email);
        navigationView.addHeaderView(nav_header);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragmnet()).commit();
                break;
            case R.id.booking_menu:_menu:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BookingFragmnet()).commit();
                break;
            case R.id.profile_menu:_menu:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragmnet()).commit();
                break;
            case R.id.settings_menu:_menu:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SettingsFragmnet()).commit();
                break;
            case R.id.logout_menu:_menu:
            FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
//        if (user == null){
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}