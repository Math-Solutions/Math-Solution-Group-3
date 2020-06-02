package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class BooksModel extends Observable {

    private ArrayList<Button> myArray;
    private String myString;


    public BooksModel(ArrayList<Button> myArray){
        this.myArray = myArray;
        this.myString=null;

    }
    public void getPressedBtnBook(int id) {
        for(int i=0; i<myArray.size();i++) {
            if (myArray.get(i).getId() == id) {

                myString = (myArray.get(i).getText().toString());

            }
        }

    }

    public String getString(){
        return this.myString;
    }
}
