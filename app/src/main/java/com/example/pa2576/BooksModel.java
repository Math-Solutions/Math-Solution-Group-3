package com.example.pa2576;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;

public class BooksModel {
    private ArrayList<Button> myArray;
    private String myString;
    private int id;
    private int nrofButtons;
    private String[] strArray;
    private Context activity;
    private Object nextActivity;
    private String url;
    public BooksModel(Context ctx,Context nextActivity){
        this.activity = ctx;
        this.nextActivity = nextActivity;
    }
    public BooksModel(ArrayList<Button> myArray) {
        this.myArray = myArray;
        this.myString = "Chapter";
        this.id = 0;
        this.nrofButtons=23;
        this.activity = null;
        this.nextActivity = null;
    }
    public void setNrofButtons(int nr) {
        this.nrofButtons = nr;
    }
    public int getNrofButtons() {
        return this.nrofButtons;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setActivity(Context activity){
        this.activity= activity;
    }
    public void setNextActivity(Object nextActivity){
        this.nextActivity = nextActivity;
    }
    public Context getActivity(){
        return this.activity;
    }
    public Object getNextActivity(){
        return this.nextActivity;
    }
    public ArrayList<Button> getArray() {
        return this.myArray;
    }
    public void setArray(ArrayList array){
        this.myArray = array;
    }
    public String getString() {
        return this.myString;
    }
    public void setString(String string){
        this.myString = string;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setStrArray(String[] strArray){
        this.strArray = strArray;
    }
    public String[] getStrArray(){
        return this.strArray;
    }
}