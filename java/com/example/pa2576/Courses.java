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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        index = getIntent().getIntExtra("COURSE",-1);
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
        mathCourses.add("Analys");
        mathCourses.add("Fler dim");
        mathCourses.add("Statestik");
        mathCourses.add("Matte grundkurs");

        mathNrBooks.add(2);
        mathNrBooks.add(2);
        mathNrBooks.add(2);
        mathNrBooks.add(1);
        mathNrBooks.add(1);
        mathNrBooks.add(1);


        //Adds how many chapters each course has in the MathNrChaptersArray


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


        for (int i = 0; i <mathCourses.size() ; i++) {
            if(btnArray.get(i).getId() == id){
                bookIndex = i;
                nrOfBooks = mathNrBooks.get(i);
                courseName = mathCourses.get(i).toString();
                openBooks();
            }
        }

    }

    public void openBooks() {

        Intent intent = new Intent(this, Books.class);
        intent.putExtra("NAME_OF_BOOK", bookIndex);
        intent.putExtra("NR_OF_BOOKS", nrOfBooks);
        intent.putExtra("CHOSEN_COURSE", courseName);
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

    private void getCourses(ArrayList<String> courses){
        final ProgressDialog progressDialog = new ProgressDialog(Courses.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Courses");
        progressDialog.show();
        String url = "http://192.168.1.112/courses.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Created Account")) {
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
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("firstname",firstName);
                param.put("lastname",lastName);
                param.put("email",email);
                param.put("education",education);
                param.put("username",username);
                param.put("password",password);


                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Courses.this).addToRequestQueue(request);
    }
    }

}