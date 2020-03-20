package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Chapter extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> idArray = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();


    ArrayList<Integer> chapterArray = new ArrayList<>();
    ArrayList<Integer> nrofTaskinChap = new ArrayList<>();


    int nrofChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        nrofChapters = getIntent().getIntExtra("NR_OF_CHAPTERS",0);
        idArray.add(R.id.button1);
        idArray.add(R.id.button2);
        idArray.add(R.id.button3);
        idArray.add(R.id.button4);
        idArray.add(R.id.button5);
        idArray.add(R.id.button6);
        idArray.add(R.id.button7);
        idArray.add(R.id.button8);
        idArray.add(R.id.button9);
        idArray.add(R.id.button10);
        idArray.add(R.id.button11);
        idArray.add(R.id.button12);
        idArray.add(R.id.button13);


        for (int id : idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);


        }
        for (int i = 0; i <nrofChapters ; i++) {
            chapterArray.add(i);
        }
        setTextBtn();
        for (int i = 0; i < chapterArray.size(); i++) {
            btnArray.get(i).setOnClickListener(this);

        }

        nrofTaskinChap.add(12);
        nrofTaskinChap.add(3);
        nrofTaskinChap.add(7);
        nrofTaskinChap.add(13);
        nrofTaskinChap.add(6);
        nrofTaskinChap.add(11);
        nrofTaskinChap.add(10);

    }

    public void setTextBtn() {

        for (int i = 0; i <chapterArray.size() ; i++) {
            btnArray.get(i).setText("Chapter " +(i+1)+"");

        }
        for(int i=0; i<btnArray.size();i++) {
            if (btnArray.get(i).getText().equals("")) {
                btnArray.get(i).setVisibility(View.GONE);
            }
        }


    }

    public void onClick(View v) {
        switch (v.getId()) {
            default:
                checkPressedBtn(v.getId());
                break;
        }
    }




    private void checkPressedBtn(int id) {


        for (int i = 0; i < chapterArray.size(); i++) {
            if (btnArray.get(i).getId() == id) {
                Intent intent = new Intent(this, Tasks.class);
                intent.putExtra("CHAPTER_NR", (i+1));
                intent.putExtra("NR_OF_TASKS_IN_CHAPTER",nrofTaskinChap.get(i));
                startActivity(intent);
            }
        }
    }


}



