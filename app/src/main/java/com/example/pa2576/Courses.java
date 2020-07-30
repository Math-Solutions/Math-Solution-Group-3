package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Courses extends Menu implements View.OnClickListener{
    ArrayList<Button> btnArray = new ArrayList<>();
    public static String courseName;
     DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private BooksModel bModel;
    public BooksController bController;
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
        bController = new BooksController(bModel);
        //bModel.setNrofButtons(13);
        //Toast.makeText(Courses.this, bController.getId()+"", Toast.LENGTH_SHORT).show();
        for (int i = 1; i <= bModel.getNrofButtons(); i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bController.setArray(btnArray);
        bController.setUrl("http://10.0.2.2/courses.php");
        bController.getbtnDataFromDB("subject",Homepage.subject,"","");
    }
    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()){
            default:
                    bController.getPressedBtnCtr(v.getId());
                    courseName = bController.getString();
                    openBooks();
                break;
        }
    }
    public void openBooks(){
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }
}