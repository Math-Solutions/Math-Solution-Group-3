package com.example.pa2576;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout = findViewById(R.id.drawer);
        toolbar= findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.homepage:
                Toast.makeText(Menu.this, "Homepage selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(Menu.this, "Profile selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info:
                Toast.makeText(Menu.this, "Info selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic:
                Toast.makeText(Menu.this, "My Solutions was selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rates:
                Toast.makeText(Menu.this, "My ratings was selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(Menu.this, "Good Bye!", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}

