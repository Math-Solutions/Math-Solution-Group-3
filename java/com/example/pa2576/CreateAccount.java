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
        else {

            return true;
        }

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
