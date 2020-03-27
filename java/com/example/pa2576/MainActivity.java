    package com.example.pa2576;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

    import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin;
    EditText password;

    ArrayList<String> searchTagsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameLogin = findViewById(R.id.userNameLogin);
        password = findViewById(R.id.password);

        Button signINBtn =  findViewById(R.id.signInBtn);
        Button createAccount = findViewById(R.id.createAccountBtn);
        Button forgotPass = findViewById(R.id.forgotbtn);

        searchTagsList.add("linear Algebra");
        searchTagsList.add("Diskret");
        searchTagsList.add("Analys");
        searchTagsList.add("Fler dim");
        searchTagsList.add("Statestik");
        searchTagsList.add("Matte grundkurs");


        signINBtn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        searchMethod();

    }

        private void searchMethod() {

        }

        @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInBtn:
                //don´t forget to take away the ! bellow
                if(!checkSignIN()) {
                    openHomePage();
                }
                break;
            case R.id.createAccountBtn:
                openCreateAccount();
                break;
            case R.id.forgotbtn:
                openForgot();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


    public boolean checkSignIN() {

        TextView incorrectLogin = findViewById(R.id.wrongUserOrPass);
        String[][] checkLoginArray = new String[4][2];

        checkLoginArray[0][0] = "Emil123";
        checkLoginArray[1][0] = "Hedvig123";
        checkLoginArray[2][0] = "Victoria123";
        checkLoginArray[3][0] = "Malin123";

        checkLoginArray[0][1] = "emilEinerskog";
        checkLoginArray[1][1] = "hedvigErnst";
        checkLoginArray[2][1] = "victoriaJansson";
        checkLoginArray[3][1] = "malinKronvall";


        for (String[] strings : checkLoginArray) {

            if ((usernameLogin.getText().toString()).equals(strings[0]) && password.getText().toString().equals(strings[1])) {
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
    public void openHomePage() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void openForgot() {
        Intent intent = new Intent(this, Forgot.class);
        startActivity(intent);
    }

    }

