package com.example.pa2576;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Observable;

public class BooksModel extends Observable {
    private ArrayList<Button> myArray;
    private String myString;
    private int id;

    public void setId(int id){
          this.id = id;
    }
    public int getId(){
        return id;
    }
    public BooksModel(){
        super();
    }
    public BooksModel(ArrayList<Button> myArray){
        this.myArray = myArray;
        this.myString=null;
    }
    public ArrayList getArray(){
        return  myArray;
    }

    public void getPressedBtnBook() {
        for(int i=0; i<myArray.size();i++) {
            if (myArray.get(i).getId() == id) {
                myString = myArray.get(i).getText().toString();
            }
        }
    }
    public void setTextBtnCourses(String[] courses){
        for(int i=0; i<courses.length;i++) {
            myArray.get(i).setText(courses[i]);
        }
        for(int i=0; i<myArray.size();i++) {
            if(myArray.get(i).getText().equals("")){
                myArray.get(i).setVisibility(View.GONE);
            }
        }
    }
    public String getString(){
        return this.myString;
    }
}