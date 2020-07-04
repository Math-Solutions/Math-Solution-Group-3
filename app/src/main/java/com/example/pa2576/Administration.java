package com.example.pa2576;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Administration extends  Menu implements View.OnClickListener {


    Button removeUser;
    Button giveAccess;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        removeUser = findViewById(R.id.removeUser);
        giveAccess = findViewById(R.id.giveAccessBtn);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        removeUser.setOnClickListener(this);
        giveAccess.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.removeUser:
                openRemoveUser();
                break;
            case R.id.giveAccessBtn:
                openGiveAccess();
                break;
        }
    }

    private void openRemoveUser() {
        Intent profile = new Intent(this, RemoveUser.class);
        startActivity(profile);
    }
    private void openGiveAccess() {
        Intent profile = new Intent(this, GiveAccess.class);
        startActivity(profile);
    }
}
