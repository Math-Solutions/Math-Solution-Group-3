package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.CornerPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Courses extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Integer> idArray = new ArrayList<>();

    ArrayList<Button> btnArray = new ArrayList<>();

    ArrayList<String> mathCourses = new ArrayList<>();
    ArrayList<Integer> mathNrBooks= new ArrayList<>();
    ArrayList<String> physicsCourses = new ArrayList<>();

    //If the index is 0 the mathcourses will be displayed and if it is 1 the physichscourses will be displayed
    int index;
    String courseName;
    int bookIndex;
    int nrOfBooks;
    String subject;
    String coursesPHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        subject = getIntent().getStringExtra("SUBJECT");
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


        mathNrBooks.add(2);
        mathNrBooks.add(2);
        mathNrBooks.add(2);
        mathNrBooks.add(1);
        mathNrBooks.add(1);
        mathNrBooks.add(1);
        mathCourses.add("Test");

        //Adds how many chapters each course has in the MathNrChaptersArray


        //Add Courses to the physicsArray
        physicsCourses.add("physics1");
        physicsCourses.add("physics2");
        physicsCourses.add("physics3");


        //checks if the user pressed the Math or the physichs button in the homepage
        getCourses(subject);
        fillCourseArray(coursesPHP);
            setTextBtnMath();



        //Make the buttons clickable
        for (int i = 0; i < mathCourses.size(); i++) {
            btnArray.get(i).setOnClickListener( this);
        }


    }

    private void fillCourseArray(String str) {

            String[]  arrOfCourses = str.split(",");

            for(String i: arrOfCourses){
                mathCourses.add(i);
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

    //checks which button that is pressed when the subjects it maths
    public void checkPressedBtnMath(int id) {


        for (int i = 0; i <mathCourses.size() ; i++) {
            if(btnArray.get(i).getId() == id){
                bookIndex = i;
                nrOfBooks = mathNrBooks.get(i);
                courseName = mathCourses.get(i).toString();
                openBooks();
            }
        }

    }
    // Opens the class books and send som variables through
    public void openBooks() {

        Intent intent = new Intent(this, Books.class);
        intent.putExtra("NAME_OF_BOOK", bookIndex);
        intent.putExtra("NR_OF_BOOKS", nrOfBooks);
        intent.putExtra("CHOSEN_COURSE", courseName);
        startActivity(intent);
    }

    //sets the texts on each button and make the buttons without any text invisible
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
    //sets the texts on each button and make the buttons without any text invisible
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


    public void getCourses(final String subject){
        final ProgressDialog progressDialog = new ProgressDialog(Courses.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Books");
        progressDialog.show();
        String url = "http://192.168.1.112/courses.php";





        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("0 results")) {
                    progressDialog.dismiss();
                    Toast.makeText(Courses.this, response, Toast.LENGTH_SHORT).show();



                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(Courses.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Courses.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("subject",subject);
                coursesPHP = param.get("course");
                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Courses.this).addToRequestQueue(request);
    }


}