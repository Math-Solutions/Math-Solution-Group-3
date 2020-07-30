package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SeeReportedSolutions extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Button> btnArray = new ArrayList<>();
    public static String imgPathRep;
    //DrawerLayout drawerLayout;
    //Toolbar toolbar;
    //NavigationView navigationView;
    //ActionBarDrawerToggle toggle;
    private BooksModel bModel;
    public BooksController bController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        bModel = new BooksModel(btnArray);
        bController = new BooksController(bModel);
        //bModel.setNrofButtons(13);
        //Toast.makeText(Courses.this, bController.getId()+"", Toast.LENGTH_SHORT).show();
        for (int i = 1; i <= bModel.getNrofButtons(); i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bController.setArray(btnArray);
        bController.setUrl("http://10.0.2.2/getReportedSolutions.php");
        bController.setString("Reported Solution ");
        bController.getbtnDataFromDB("","","","");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                bController.getPressedBtnCtr(v.getId());
                imgPathRep = bController.getString();
                openSolution();
                break;
        }
    }

    private void openSolution() {
        Intent intent = new Intent(this, ViewSolution.class);
        startActivity(intent);
    }
}
