package com.example.pa2576;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Books extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Integer> idArray = new ArrayList<>();

    ArrayList<Button> btnArray = new ArrayList<>();

    ArrayList<Integer> nrOfBooksArray = new ArrayList<>();
    ArrayList<String> checkCourseArray = new ArrayList<>();

    ArrayList<String> booknNameArray = new ArrayList<>();




    String choosenBook;

    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        choosenBook = getIntent().getStringExtra("NAME_OF_BOOK");
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
        //Add Courses to the checkCourseArray
        checkCourseArray.add("linear Algebra");
        checkCourseArray.add("Diskret");
        checkCourseArray.add("Analys 1");
        checkCourseArray.add("Analys 2");
        checkCourseArray.add("Fler dim");
        checkCourseArray.add("Statestik");
        checkCourseArray.add("Matte grundkurs");
        checkCourseArray.add("physics1");
        checkCourseArray.add("physics2");
        checkCourseArray.add("physics3");




        checkbook();


        //Make the buttons clickable
        for (int i = 0; i < checkCourseArray.size(); i++) {
            btnArray.get(i).setOnClickListener( this);
        }


    }

    private void checkbook() {

        for (int i = 0; i <checkCourseArray.size() ; i++) {
            switch (choosenBook){
                case "linear Algebra":
                    btnArray.get(0).setText("Linjer bok 1");
                    btnArray.get(0).setText("Linjer bok 2");


                    break;
                case "Diskret":
                    btnArray.get(0).setText("Diskret bok 1");
                    btnArray.get(0).setText("Diskret bok 2");


                    break; case "Analys 1":
                    btnArray.get(0).setText("Analys bok 1");
                    btnArray.get(0).setText("Analys bok 2");


                    break;
                case "Analys 2":
                    btnArray.get(0).setText("Analys bok 1");
                    btnArray.get(0).setText("Analys bok 2");


                    break;
                case "Fler dim":
                    btnArray.get(0).setText("Fler dim bok 1");
                    btnArray.get(0).setText("Fler dim bok 2");



                default:
                    throw new IllegalStateException("Unexpected value: " + choosenBook);
            }

        }
    }


    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()){
            default:

                break;
        }
    }
/*
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


 */

}