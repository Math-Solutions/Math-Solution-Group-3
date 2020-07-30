package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Books extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Button> btnArray = new ArrayList<>();
    public static String bookName;
    private BooksModel bModel;
    private BooksController bController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        setTitle(Courses.courseName);
        bModel = new BooksModel(btnArray);
        bController = new BooksController(bModel);
        for (int i = 1; i <= bModel.getNrofButtons(); i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bController.setArray(btnArray);
        bController.setUrl("http://10.0.2.2/books.php");
        bController.getbtnDataFromDB("course",Courses.courseName,"","");
    }
    public void onClick(View v) {
        switch (v.getId()){
            default:
                bController.getPressedBtnCtr(v.getId());
                bookName = bController.getString();
                openChapters();
                break;
        }
    }
    //opens the chapters class and sends som variables into that class
    public void openChapters() {
        Intent intent = new Intent(this, Chapter.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Courses.class);
        startActivity(intent);
    }
}