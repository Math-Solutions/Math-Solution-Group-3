package com.example.pa2576;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tasks extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Button> btnArray = new ArrayList<>();
    public static String taskID;
    String bookName;
    private BooksModel bModel;
    private BooksController bController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        bModel = new BooksModel(btnArray);
        bController = new BooksController(bModel);

        for (int i = 1; i <= bModel.getNrofButtons(); i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bController.setArray(btnArray);
        bController.setUrl("http://10.0.2.2/tasks.php");
        bController.getbtnDataFromDB("book",Books.bookName,"chapter",Chapter.chapter);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                bController.getPressedBtnCtr(v.getId());
                taskID = bController.getString();
                openSolutions();
                break;
        }
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Chapter.class);
        startActivity(intent);
    }

    public void openSolutions(){
        Intent intent = new Intent(this, SeeSolutions.class);
        startActivity(intent);
    }
}