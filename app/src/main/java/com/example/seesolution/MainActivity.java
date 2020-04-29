package com.example.seesolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button teacherOne = findViewById(R.id.teacherSolOne);
        Button teacherTwo = findViewById(R.id.teacherSolTwo);
        Button teacherThree = findViewById(R.id.teacherSolThree);
        Button studentOne = findViewById(R.id.studentSolOne);
        Button studentTwo = findViewById(R.id.studentSolTwo);
        Button studentThree = findViewById(R.id.studentSolThree);
        Button studentFour = findViewById(R.id.studentSolFour);
        Button studentFive = findViewById(R.id.studentSolFive);

        Button add = findViewById(R.id.addSolution);

        teacherOne.setOnClickListener(this);
        teacherTwo.setOnClickListener(this);
        teacherThree.setOnClickListener(this);

        studentOne.setOnClickListener(this);
        studentTwo.setOnClickListener(this);
        studentThree.setOnClickListener(this);
        studentFour.setOnClickListener(this);
        studentFive.setOnClickListener(this);

        add.setOnClickListener(this);



      /*  if ( add.setOnClickListener(); )
        {
            teacherSolOne.setVisibility(View.VISIBLE); //SHOW the button
        }*/
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.teacherSolOne:
                Toast.makeText(this, "Teacher Solution one is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teacherSolTwo:
                Toast.makeText(this, "Teacher Solution two is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teacherSolThree:
                Toast.makeText(this, "Teacher Solution three is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.studentSolOne:
                Toast.makeText(this, "Student Solution one is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.studentSolTwo:
                Toast.makeText(this, "Student Solution two is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.studentSolThree:
                Toast.makeText(this, "Student Solution three is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.studentSolFour:
                Toast.makeText(this, "Student Solution four is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.studentSolFive:
                Toast.makeText(this, "Student Solution five is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addSolution:
                Toast.makeText(this, "Add a solution", Toast.LENGTH_SHORT).show();
                break;
        }

    }





}
