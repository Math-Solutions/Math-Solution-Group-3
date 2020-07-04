package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ChangeProfile extends AppCompatActivity {
    private Button change;
    private EditText firstName, lastName, email, username, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        firstName = (EditText) findViewById(R.id.FirstNameET);
        lastName = (EditText) findViewById(R.id.LastNameET);
        email = (EditText) findViewById(R.id.EmailET);
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.PasswordET);
        password2 = (EditText) findViewById(R.id.PasswordET2);

        change = (Button) findViewById(R.id.ChangeBtn);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextEditHandler();

            }
        });
    }

    public void TextEditHandler(){


                if(password.getText().toString().equals(password2.getText().toString()) || password.getText().toString() == null) {

                        if (checkPassword(password.toString()) || password.getText().toString() == null) {
                            UpdateUser(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), username.getText().toString(), password.getText().toString());
                        } else {
                            Toast.makeText(ChangeProfile.this, "The password needs to be at least 8 char long, a capital letter and a digit", Toast.LENGTH_SHORT).show();
                        }

                }
                else{
                    Toast.makeText(ChangeProfile.this, "password does not match password2" , Toast.LENGTH_SHORT).show();

                }




    }


    public boolean checkPassword(String password) {
        boolean check = false;

        if(password.length()>=8){
            boolean checknumber = false;
            boolean checkCapLetter = false;
            for(int i=0; i<password.length();i++){

                if(Character.isDigit(password.charAt(i))){
                    checknumber = true;
                }
                if(Character.isUpperCase(password.charAt(i))){
                    checkCapLetter = true;
                }

            }
            if(checknumber && checkCapLetter){
                check = true;
            }
        }
        return check;
    }
    public void UpdateUser(final String firstName, final String lastName, final String email, final String username, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(ChangeProfile.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Creating the Account");
        progressDialog.show();
        String url = "http://10.0.2.2/updateProfile.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            //Here the method checks if the response from the php code is "Successfully created an account" that it should create
            public void onResponse(String response) {
                change.setText(response);
                String[] responseArray = change.getText().toString().split(" ");
                if (responseArray[0].equals("Successfully")) {
                    progressDialog.dismiss();
                    Toast.makeText(ChangeProfile.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangeProfile.this, Homepage.class));

                }
                else{
                    //displays the respone that was received from the php code
                    progressDialog.dismiss();
                    Toast.makeText(ChangeProfile.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ChangeProfile.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            //here is the variables that goes into the php code
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("firstname",firstName);
                param.put("lastname",lastName);
                param.put("email",email);
                param.put("username",username);
                param.put("password",password);


                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(ChangeProfile.this).addToRequestQueue(request);
    }
}
