package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Courses extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Integer> idArray = new ArrayList<>();

    ArrayList<Button> btnArray = new ArrayList<>();

    ArrayList<String> mathCourses = new ArrayList<>();
    ArrayList<Integer> mathNrChapters = new ArrayList<>();
    ArrayList<String> physicsCourses = new ArrayList<>();

    //If the index is 0 the mathcourses will be displayed and if it is 1 the physichscourses will be displayed
    int index =0;

    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        idArray.add(R.id.button1);
        idArray.add(R.id.button2);
        idArray.add(R.id.button3);
        idArray.add(R.id.button4);
        idArray.add(R.id.button5);
        idArray.add(R.id.button6);
        idArray.add(R.id.button7);
        idArray.add(R.id.button8);
        idArray.add(R.id.button9);
        idArray.add(R.id.button10);
        idArray.add(R.id.button11);
        idArray.add(R.id.button12);
        idArray.add(R.id.button13);

        //Adds the buttons in the btnArray
        for (int id: idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);
        }
        //Add Courses to the mathCoursesArray
        mathCourses.add("linear Algebra");
        mathCourses.add("Diskret");
        mathCourses.add("Analys 1");
        mathCourses.add("Analys 2");
        mathCourses.add("Fler dim");
        mathCourses.add("Statestik");
        mathCourses.add("Matte grundkurs");

        //Adds how many chapters each course has in the MathNrChaptersArray
        mathNrChapters.add(12);
        mathNrChapters.add(3);
        mathNrChapters.add(7);
        mathNrChapters.add(13);
        mathNrChapters.add(6);
        mathNrChapters.add(11);
        mathNrChapters.add(10);

        //Add Courses to the physicsArray
        physicsCourses.add("physics1");
        physicsCourses.add("physics2");
        physicsCourses.add("physics3");

        //checks if the user pressed the Math or the physichs button in the homepage
        if(index == 0) {
            setTextBtnMath();
        }
        else if(index == 1){
            setTextBtnPhys();
        }

        //Make the buttons clickable
        for (int i = 0; i < mathCourses.size(); i++) {
            btnArray.get(i).setOnClickListener( this);
        }


    }


    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()){
            default:
                if(index == 0) {
                    checkPressedBtnMath(v.getId());
                }
                else if(index == 1){
                    checkPressedBtnPhys(v.getId());
                }
                break;
        }
    }

    public void checkPressedBtnPhys(int id) {

        for (int i = 0; i <physicsCourses.size() ; i++) {
            if(btnArray.get(i).getId() == id){
                //Add Intens to physics chapters
            }
        }
    }


    public void checkPressedBtnMath(int id) {


        for (int i = 0; i <mathNrChapters.size() ; i++) {
            if(btnArray.get(i).getId() == id){
                number = mathNrChapters.get(i);
                openChapters();
            }
        }

    }

    public void openChapters() {

        Intent intent = new Intent(this, Chapter.class);
        intent.putExtra("NR_OF_CHAPTERS", number);
        startActivity(intent);
    }


    public void setTextBtnMath(){

        for(int i=0; i<mathCourses.size();i++) {
            btnArray.get(i).setText(mathCourses.get(i));
        }
        for(int i=0; i<btnArray.size();i++) {
           if(btnArray.get(i).getText().equals("")){
               btnArray.get(i).setVisibility(View.GONE);
           }

        }


    }
    public void setTextBtnPhys() {

        for(int i=0; i<physicsCourses.size();i++) {
            btnArray.get(i).setText(physicsCourses.get(i));
        }
        for(int i=0; i<btnArray.size();i++) {
            if(btnArray.get(i).getText().equals("")){
                btnArray.get(i).setVisibility(View.GONE);
            }

        }
    }


}