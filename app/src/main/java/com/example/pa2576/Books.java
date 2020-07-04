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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        setTitle(Courses.courseName);
        for (int i = 1; i <= BooksController.NrofButtons; i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bModel = new BooksModel(btnArray);
       //checkbook();
        getBooks(Courses.courseName);
        //Toast.makeText(Books.this, "error.toString()", Toast.LENGTH_SHORT).show();
        //Make the buttons clickable
        for (int i = 0; i < btnArray.size(); i++) {
            //btnArray.get(i).setOnClickListener(new BooksController(getApplicationContext()));
            btnArray.get(i).setOnClickListener(this);
        }
    }
    public void onClick(View v) {
        switch (v.getId()){
            default:
                //bModel.getPressedBtnBook(v.getId());
                bookName = bModel.getString();
                openChapters();
                break;
        }
    }
    public void setTextBtnBooks(String[] books){
        for(int i=0; i<books.length;i++) {
            btnArray.get(i).setText(books[i]);
        }
        for(int i=0; i<btnArray.size();i++) {
            if(btnArray.get(i).getText().equals("")){
                btnArray.get(i).setVisibility(View.GONE);
            }
        }
    }
    //checks which button that is pressed
    //opens the chapters class and sends som variables into that class
    public void openChapters() {
        Intent intent = new Intent(this, Chapter.class);
        startActivity(intent);
    }
    //Input value course into database and extract all the books on that course
    public void getBooks(final String course) {
        final ProgressDialog progressDialog = new ProgressDialog(Books.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Books");
        progressDialog.show();
        String url = "http://10.0.2.2/books.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                String[] books = btnArray.get(0).getText().toString().split(",");
                setTextBtnBooks(books);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Books.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("course", course);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Books.this).addToRequestQueue(request);
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Courses.class);
        startActivity(intent);
    }
}