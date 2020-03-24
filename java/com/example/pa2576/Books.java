package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Books extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Integer> idArray = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();
    ArrayList<Integer> chapterArrayOne = new ArrayList<>();
    ArrayList<Integer> chapterArrayTwo = new ArrayList<>();

    int choosenBook;
    int nrOfBooks;

    int number=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        choosenBook = getIntent().getIntExtra("NAME_OF_BOOK",-1);
        nrOfBooks = getIntent().getIntExtra("NAME_OF_BOOK",0);
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

        //Adds the buttons in the btnArray
        for (int id: idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);
        }



        chapterArrayOne.add(12);
        chapterArrayOne.add(10);
        chapterArrayOne.add(5);
        chapterArrayOne.add(13);
        chapterArrayOne.add(9);
        chapterArrayOne.add(2);


        chapterArrayTwo.add(11);
        chapterArrayTwo.add(9);
        chapterArrayTwo.add(8);






       checkbook();
       setTextBtnBooks();

        //Make the buttons clickable
        for (int i = 0; i < choosenBook+1; i++) {
            btnArray.get(i).setOnClickListener( this);
        }




    }
    //Set what happens when you click a button
    public void onClick(View v) {
        switch (v.getId()){
            default:
               checkPressedBtnBook(v.getId());
                break;
        }
    }


    private void checkbook() {

        for (int i = 0; i <choosenBook+1 ; i++) {
            switch (choosenBook){
                case 0:
                    btnArray.get(0).setText("Linjer bok 1");
                    btnArray.get(1).setText("Linjer bok 2");
                    break;
                case 1:
                    btnArray.get(0).setText("Diskret bok 1");
                    btnArray.get(1).setText("Diskret bok 2");
                    break;

                    case 2:
                    btnArray.get(0).setText("Analys bok 1");
                    btnArray.get(1).setText("Analys bok 2");
                    break;
                case 3:
                    btnArray.get(0).setText("Fler dim bok 1");
                    break;
                case 4:
                    btnArray.get(0).setText("Statistik bok 1");
                    break;
                case 5:
                    btnArray.get(0).setText("Matte grundcourse bok 1");
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + choosenBook);
            }

        }
    }


    public void setTextBtnBooks(){


        for(int i=0; i<btnArray.size();i++) {
            if(btnArray.get(i).getText().equals("")){
                btnArray.get(i).setVisibility(View.GONE);
            }

        }


    }
    public void checkPressedBtnBook(int id) {



            if(btnArray.get(0).getId() == id) {
                    for (int j = 0; j < choosenBook + 1; j++) {
                        if (j == choosenBook) {
                            number = chapterArrayOne.get(j);
                        }
                    }
                }

            if(btnArray.get(1).getId() == id) {
                    for (int j = 0; j < choosenBook + 2; j++) {
                        if (j == choosenBook) {
                                number = chapterArrayTwo.get(j);
                        }
                    }

                }
                openChapters();
    }

    public void openChapters() {

        Intent intent = new Intent(this, Chapter.class);
        intent.putExtra("NR_OF_CHAPTERS", number);
        startActivity(intent);
    }

}