package com.example.mathsolutionprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonAction();
        TextfieldHandler();
    }

    public void ButtonAction(){
        Button changeProfile =(Button) findViewById(R.id.changeProfileBtn);
        Button changePic = (Button) findViewById(R.id.changePicBtn);
        Button rates = (Button) findViewById(R.id.rateBtn);
        Button uploads = (Button) findViewById(R.id.myUploadsBtn);

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangeProfile();

            }
        });


        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangePic();
            }
        });
        rates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRates();
            }
        });
        uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUploads();
            }
        });

    }

    public void openChangeProfile(){
        Intent intent = new Intent(this, ChangeProfile.class);
        startActivity(intent);
    }

    public void openChangePic(){
        Intent intet = new Intent(this, ChangeProfilePic.class);
        startActivity(intet);
    }

    public void openRates(){
        Intent intent = new Intent(this, MyRates.class);
        startActivity(intent);
    }

    public void openUploads() {
        Intent intent = new Intent(this, MyUploads.class);
        startActivity(intent);
    }

    public void TextfieldHandler() {
        TextView rateAmount = (TextView) findViewById(R.id.AmountVoteTV);
        TextView myRates = (TextView) findViewById(R.id.RateingTV);

        myRates.setText("Your rates");
        rateAmount.setText("Here will be rates from database");
    }
}
