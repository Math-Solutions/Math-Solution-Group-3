package com.example.pa2576;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateAccount extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText eMail;
    EditText createUsername;
    EditText createPassword;
    EditText createPassword2;
    Button createAccount;
    CheckBox checkBoxGDPR;
    CheckBox checkBoxRules;
    TextView welcomeText;
    TextView notFilled;
    TextView passwordText;
    TextView passwordText2;
    TextView chooseEducation;
    CheckBox civAI;
    CheckBox civSak;
    CheckBox hogSak;
    CheckBox civIndek;
    CheckBox civMarin;
    CheckBox civMaskin;
    CheckBox hogMaskin;
    CheckBox civSpel;
    CheckBox hogSpel;
    CheckBox educationElse;

    ArrayList<CheckBox> educationArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        welcomeText = findViewById(R.id.welcome);
        checkBoxGDPR = findViewById(R.id.checkBoxGDPR);
        checkBoxRules = findViewById(R.id.checkBoxRules);
        firstName = findViewById(R.id.createFirstName);
        lastName = findViewById(R.id.createLastName);
        eMail = findViewById(R.id.createEmail);
        createUsername = findViewById(R.id.createUsername);
        createPassword = findViewById(R.id.createPassword);
        createPassword2 = findViewById(R.id.createPassword2);
        notFilled = findViewById(R.id.notFilled);
        passwordText = findViewById(R.id.passwordText);
        passwordText2 = findViewById(R.id.passwordText2);
        chooseEducation = findViewById(R.id.chooseEducation);


        //All buttons for educations
        civAI = findViewById(R.id.civAI);
        civSak = findViewById(R.id.civSak);
        hogSak = findViewById(R.id.hogSak);
        civIndek = findViewById(R.id.civIndek);
        civMarin = findViewById(R.id.civMarin);
        civMaskin = findViewById(R.id.civMaskin);
        hogMaskin = findViewById(R.id.hogMaskin);
        civSpel = findViewById(R.id.civSpel);
        hogSpel = findViewById(R.id.hogSpel);
        educationElse = findViewById(R.id.educationElse);

        //Array for the educations checkboxes



        educationArray.add(civAI);
        educationArray.add(civSak);
        educationArray.add(hogSak);
        educationArray.add(civIndek);
        educationArray.add(civMarin);
        educationArray.add(civMaskin);
        educationArray.add(hogMaskin);
        educationArray.add(civSpel);
        educationArray.add(hogSpel);
        educationArray.add(educationElse);

        createAccount = findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( checkIfFilled()){
                   openSignInPage();
               }


            }


        });



    }

    public void openSignInPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public boolean checkIfFilled() {

        String red = "#ba160c";

        if (!checkBoxGDPR.isChecked() || !checkBoxRules.isChecked() || createUsername.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()){
            setColorText(red);
            notFilled.setText("Please fill in all the things that are red");
        return false;
        }
        else if(!checkEducation()){
            chooseEducation.setText("choose ONE Education");
            chooseEducation.setTextColor(Color.parseColor(red));
            return false;
        }
        else if(checkUsername()){
            createUsername.setTextColor(Color.parseColor(red));
            notFilled.setText("Username already exists");
            return false;
        }
        else if(!checkPassword()){
            setcolorPassword(red);
            notFilled.setText("The password does not match the criteria or the two passwords do not match");

            return false;
        }
        else if(!checkEmail()){
          //  setcolorPassword(red);
           // notFilled.setText("The password does not match the criteria or the two passwords do not match");

            return false;
        }

        else {
            return true;
        }

    }

    private boolean checkEducation() {
        int index = 0;

        for (CheckBox i : educationArray){
            if(i.isChecked()){
                index++;
            }
        }
        if(index==1){
            return true;
        }
        else
        return false;
    }

    private boolean checkEmail() {



        return true;
    }

    private void setColorText(String color) {

        firstName.setHintTextColor(Color.parseColor(color));
        lastName.setHintTextColor(Color.parseColor(color));
        createUsername.setHintTextColor(Color.parseColor(color));
        eMail.setHintTextColor(Color.parseColor(color));
        checkBoxGDPR.setTextColor(Color.parseColor(color));
        checkBoxRules.setTextColor(Color.parseColor(color));

    }

    private void setcolorPassword(String color){

        passwordText.setTextColor(Color.parseColor(color));
        passwordText2.setTextColor(Color.parseColor(color));
    }



    public boolean checkPassword() {


        if(createPassword.getText().toString().length()>=8){
            boolean checknumber = false;
            boolean checkCapLetter = false;
            for(int i=0; i<createPassword.getText().toString().length();i++){


                if(Character.isDigit(createPassword.getText().toString().charAt(i))){
                    checknumber = true;
                }
                if(Character.isUpperCase(createPassword.getText().toString().charAt(i))){
                    checkCapLetter = true;
                }
                if(checknumber && checkCapLetter){
                    return true;
                }

            }
        }
        return false;
    }

    private boolean checkUsername() {

        String[] checkLoginArray = new String[4];

        checkLoginArray[0] = "Emil123";
        checkLoginArray[1] = "Hedvig123";
        checkLoginArray[2] = "Victoria123";
        checkLoginArray[3] = "Malin123";

        for (String s : checkLoginArray) {

            if ((createUsername.getText().toString()).equals(s)) {
                return true;

            }
        }
        return false;
    }



}
