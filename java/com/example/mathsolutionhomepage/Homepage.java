package com.example.mathsolutionhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    Button math, physics, info, profile;
    TextView welcome, searchinfo;
    SearchView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        buttonHandler();


}

    public void buttonHandler() {
        math = (Button) findViewById(R.id.mathBtn);
        physics = (Button) findViewById(R.id.physicsBtn);
        info = (Button) findViewById(R.id.infoBtn);
        profile = (Button) findViewById(R.id.profileBtn);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourses();
            }});

        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCourses();
            }});

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });


    };

    public void openProfile() {
        Intent profile = new Intent(this, ProfilePage.class);
        startActivity(profile);
    }

    public void openCourses(){

    }
    public void openInfo() {

    }


}
