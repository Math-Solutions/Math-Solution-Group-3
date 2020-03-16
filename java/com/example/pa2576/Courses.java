package com.example.pa2576;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {


    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;


    ArrayList<Button> btnArray = new ArrayList<>();

    ArrayList<String> mathCourses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);


        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn10 = findViewById(R.id.button10);
        btn11 = findViewById(R.id.button11);
        btn12 = findViewById(R.id.button12);
        btn13 = findViewById(R.id.button13);

        btnArray.add(btn1);
        btnArray.add(btn2);
        btnArray.add(btn3);
        btnArray.add(btn4);
        btnArray.add(btn5);
        btnArray.add(btn6);
        btnArray.add(btn7);
        btnArray.add(btn8);
        btnArray.add(btn9);
        btnArray.add(btn10);
        btnArray.add(btn11);
        btnArray.add(btn12);
        btnArray.add(btn13);

        mathCourses.add("linear Algebra");
        mathCourses.add("Diskret");
        mathCourses.add("Analys 1");
        mathCourses.add("Analys 2");
        mathCourses.add("Fler dim");
        mathCourses.add("Statestik");
        mathCourses.add("Matte grundkurs");



        setTextBtn();
    }

    public void setTextBtn() {






        for(int i=0; i<mathCourses.size();i++) {
            btnArray.get(i).setText(mathCourses.get(i));
        }
        for(int i=0; i<btnArray.size();i++) {
           if(btnArray.get(i).getText().equals("")){
               btnArray.get(i).setVisibility(View.GONE);
           }

        }


    }
}

