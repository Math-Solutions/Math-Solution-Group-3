package com.example.pa2576;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Administration extends AppCompatActivity implements View.OnClickListener {


    Button removeUser;
    Button giveAccess;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        removeUser = findViewById(R.id.removeUser);
        giveAccess = findViewById(R.id.giveAccessBtn);

        removeUser.setOnClickListener(this);
        giveAccess.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.removeUser:
                openRemoveUser();
                break;
            case R.id.giveAccessBtn:
                openGiveAccess();
                break;
        }
    }

    private void openRemoveUser() {
        Intent profile = new Intent(this, RemoveUser.class);
        startActivity(profile);
    }
    private void openGiveAccess() {
        Intent profile = new Intent(this, GiveAccess.class);
        startActivity(profile);
    }
}
