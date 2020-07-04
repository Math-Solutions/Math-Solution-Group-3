package com.example.pa2576;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GiveAccess extends AppCompatActivity {


    EditText enterUser;
    Button giveAccess;
    RadioGroup radioGroupAccess;
    RadioButton radioBtnTeacher;
    RadioButton radioBtnAdmin;
    String access;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_access);


        enterUser = findViewById(R.id.giveAccessUser);
        giveAccess = findViewById(R.id.giveAccessToUserBtn);
        radioGroupAccess = findViewById(R.id.radioGroupAccess);
        radioBtnAdmin = findViewById(R.id.radioButtonAdmin);
        radioBtnTeacher = findViewById(R.id.radioButtonTeacher);

        giveAccess.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              if (radioGroupAccess.getCheckedRadioButtonId() == -1) {
                                                  Toast.makeText(GiveAccess.this, "You need to check in the access you want to give the user", Toast.LENGTH_SHORT).show();
                                              }
                                              else{
                                                  if(radioBtnAdmin.isChecked()) {
                                                    access = radioBtnAdmin.getText().toString();
                                                  }
                                                  else {
                                                      access = radioBtnTeacher.getText().toString();
                                                  }
                                                  giveAccessMethod(enterUser.getText().toString(),access);

                                              }

                                          }
                                      }
        );


    }

    private void giveAccessMethod(final String username, final String userType) {
        final ProgressDialog progressDialog = new ProgressDialog(GiveAccess.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Retrieving image to database");
        progressDialog.show();
        String url = "http://10.0.2.2/giveAccess.php";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            //Here the method checks if the response from the php code is "The user has been deleted" the user has been removed
            public void onResponse(String response) {

                if(response.equals("Users access have been changed")){
                    progressDialog.dismiss();
                    opeAdministration();


                }
                else{
                    progressDialog.dismiss();

                    Toast.makeText(GiveAccess.this, response, Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(GiveAccess.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {


            //here is the variables that goes into the php code
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("username",username);
                param.put("userType",userType);



                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(GiveAccess.this).addToRequestQueue(request);

    }



    private void opeAdministration() {
        Intent profile = new Intent(this, Administration.class);
        startActivity(profile);
    }


}
