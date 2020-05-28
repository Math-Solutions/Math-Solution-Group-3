package com.example.pa2576;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Homepage extends Menu implements View.OnClickListener {
    Button math, physics, info, profile;
    TextView welcome, searchinfo;
    SearchView search;
    String subject=null;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button adminBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


       // drawerLayout = findViewById(R.id.drawer);
       // toolbar = findViewById(R.id.toolbar);
       // navigationView = findViewById(R.id.navigationView);
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       // toggle = ne1w ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
       // drawerLayout.addDrawerListener(toggle);
       // toggle.syncState();
       // navigationView.setNavigationItemSelectedListener(this);

        math = (Button) findViewById(R.id.mathBtn);
        physics = (Button) findViewById(R.id.physicsBtn);
        info = (Button) findViewById(R.id.infoBtn);
        profile = (Button) findViewById(R.id.profileBtn);
        adminBtn = findViewById(R.id.AdminBtn);

        math.setOnClickListener( this);
        physics.setOnClickListener( this);
        info.setOnClickListener( this);
        profile.setOnClickListener( this);
        adminBtn.setOnClickListener(this);
        if(!MainActivity.access.equals("Admin")){
            adminBtn.setVisibility(View.GONE);
        }

}


    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mathBtn:
                subject = "Math";
                openCourses();
                break;

            case R.id.physicsBtn:
                subject = "Physics";
                openCourses();
                break;
            case R.id.infoBtn:
                openInfo();
                break;
            case R.id.profileBtn:
                openProfile();
                break;
            case R.id.AdminBtn:
                openAdministraion();
                break;
        }
    }
    public void openAdministraion() {
        Intent profile = new Intent(this, Administration.class);
        startActivity(profile);
    }

    public void openProfile() {
        Intent profile = new Intent(this, ProfilePage.class);
        startActivity(profile);
    }

    public void openCourses(){
        Intent course = new Intent(this, Courses.class);
        course.putExtra("SUBJECT",subject);
        startActivity(course);
    }
    public void openInfo() {

    }




}
