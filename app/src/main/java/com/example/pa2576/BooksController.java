package com.example.pa2576;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class BooksController implements View.OnClickListener{

    private String string;
    private ArrayList<Button> myArray;
    public static int NrofButtons = 13;

    private Context activity;
    private Context nextActivity;
    private BooksModel bModel = new BooksModel();

    public BooksController(Context ctx){
        activity = ctx;
    }
    public void setNextActivity(Context nextActivity) {
        this.nextActivity = nextActivity;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:

              bModel.setId(v.getId());
              //bModel.getPressedBtnBook();
              string =  bModel.getString();

              Toast.makeText(activity.getApplicationContext(), string+"", Toast.LENGTH_SHORT).show();
              //Intent intent = new Intent(activity.getApplicationContext(), Books.class);
              //activity.startActivity(intent);
              break;
        }
    }
}
