package com.example.pa2576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameLogin = (EditText) findViewById(R.id.userNameLogin);
        password = (EditText) findViewById(R.id.password);

        Button signINBtn =  findViewById(R.id.signInBtn);
        Button createAccount = findViewById(R.id.createAccountBtn);
        Button forgotPass = findViewById(R.id.forgotbtn);


        signINBtn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        forgotPass.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInBtn:
                if(checkSignIN()) {
                    //openHomePage();
                }
                break;
            case R.id.createAccountBtn:
                openCreateAccount();
                break;
        }
    }

    public boolean checkSignIN() {

        TextView incorrectLogin = (TextView) findViewById(R.id.wrongUserOrPass);
        String[][] checkLoginArray = new String[4][2];

        checkLoginArray[0][0] = "Emil123";
        checkLoginArray[1][0] = "Hedvig123";
        checkLoginArray[2][0] = "Victoria123";
        checkLoginArray[3][0] = "Malin123";

        checkLoginArray[0][1] = "emilEinerskog";
        checkLoginArray[1][1] = "hedvigErnst";
        checkLoginArray[2][1] = "victoriaJansson";
        checkLoginArray[3][1] = "malinKronvall";


        for (int i = 0; i < checkLoginArray.length; i++) {

            if ((usernameLogin.getText().toString()).equals(checkLoginArray[i][0]) && password.getText().toString().equals(checkLoginArray[i][1])) {
                incorrectLogin.setText("Det funka");
                return true;

            }

        }
            incorrectLogin.setText("Incorrect username or password, try again!");
            return false;

    }
    public void openCreateAccount() {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
        }
       /* public void openHomePage() {
            Intent intent = new Intent(this, HomePageTest.class);
            startActivity(intent);
        }

        public void openForgot() {
            Intent intent = new Intent(this, Forgot.class);
            startActivity(intent);
        }*/

}
