package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.CornerPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Courses extends Menu {
    ArrayList<Button> btnArray = new ArrayList<>();
    //If the index is 0 the mathcourses will be displayed and if it is 1 the physichscourses will be displayed
   public static String courseName;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    private BooksModel bModel;
    private BooksController bController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        /*
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
*/
        //subject = getIntent().getStringExtra("SUBJECT");

        bModel = new BooksModel(btnArray);
        for (int i = 1; i <= BooksController.NrofButtons; i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i - 1).setOnClickListener(new BooksController(getApplicationContext()));
        }

        //bController.setNextActivity(Books.class);
        //Adds the buttons in the btnArray

        getCourses(Homepage.subject);
        //Make the buttons clickable
    }
    //Set what happens when you click a button
    /*public void onClick(View v) {
        switch (v.getId()){
            default:
                    bModel.getPressedBtnBook(v.getId());
                    courseName = bModel.getString();
                    openBooks();
                break;
        }
    }

     */
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }
    // Opens the class books and send som variables through
    public void openBooks() {
        Intent intent = new Intent(this, Books.class);
        //intent.putExtra("CHOSEN_COURSE", courseName);
        startActivity(intent);
    }
    //Input value Subject into database and extract all the courses with that subject
    public void getCourses(final String subject) {
        final ProgressDialog progressDialog = new ProgressDialog(Courses.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Courses");
        progressDialog.show();
        String url = "http://10.0.2.2/courses.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                String[] courses = btnArray.get(0).getText().toString().split(",");
                bModel.setTextBtnCourses(courses);
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
                param.put("subject", subject);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Courses.this).addToRequestQueue(request);
    }
}