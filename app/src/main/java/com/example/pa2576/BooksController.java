package com.example.pa2576;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class BooksController implements View.OnClickListener{


    private String mString;


    private BooksModel bModel;
    private Context activity;
    public BooksController(){

    }
    public BooksController(Context ctx){
        this.activity = ctx;
        mString = null;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
              bModel.getPressedBtnBook(v.getId());

                Toast.makeText(activity.getApplicationContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void setString(String s){
        this.mString = s;
    }
    public String getString(){
        return this.mString;
    }
}
