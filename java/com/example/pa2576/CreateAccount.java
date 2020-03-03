package com.example.pa2576;

import android.content.Intent;
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


    public boolean checkIfFilled(){

        if(!checkBoxRules.isChecked() || !checkBoxGDPR.isChecked() || checkUsername() || checkPassword() || firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || eMail.getText().toString().isEmpty())
            return false;
        else
            welcomeText.setText("Allt är ifyllt");
            return true;

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
