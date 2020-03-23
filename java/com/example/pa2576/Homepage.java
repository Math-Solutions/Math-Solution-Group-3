package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity implements View.OnClickListener {
    Button math, physics, info, profile;
    TextView welcome, searchinfo;
    SearchView search;
    int number=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        buttonHandler();


}

    @Override
    public void onClick(View v) {

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
                number=0;
                openCourses();
            }});

        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=1;
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
        Intent course = new Intent(this, Courses.class);
        course.putExtra("COURSE",number);
        startActivity(course);
    }
    public void openInfo() {

    }




}
