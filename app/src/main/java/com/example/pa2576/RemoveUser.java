package com.example.pa2576;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RemoveUser extends AppCompatActivity {

    EditText enterUser;
    Button removeUser;
    CheckBox checkBoxRemoveUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);

        enterUser = findViewById(R.id.removeEnterUser);
        removeUser = findViewById(R.id.executeRemoveUserBtn);
        checkBoxRemoveUser = findViewById(R.id.checkBoxRemoveUser);
        removeUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBoxRemoveUser.isChecked()) {
                    removeUser(enterUser.getText().toString());

                }
                else{
                    Toast.makeText(RemoveUser.this, "You need to check in the box in order to remove this user", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );


    }
    private void opeAdministraion() {
        Intent profile = new Intent(this, Administration.class);
        startActivity(profile);
    }
    private void removeUser(final String username) {
            final ProgressDialog progressDialog = new ProgressDialog(RemoveUser.this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.setTitle("Retrieving image to database");
            progressDialog.show();
            String url = "http://10.0.2.2/removeUser.php";


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                //Here the method checks if the response from the php code is "The user has been deleted" the user has been removed
                public void onResponse(String response) {

                    if(response.equals("The user has been deleted")){
                        progressDialog.dismiss();
                        opeAdministraion();


                    }
                    else{
                        progressDialog.dismiss();

                        Toast.makeText(RemoveUser.this, response, Toast.LENGTH_SHORT).show();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(RemoveUser.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {


                //here is the variables that goes into the php code
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("username",username);



                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getmInstance(RemoveUser.this).addToRequestQueue(request);

        }
    }

