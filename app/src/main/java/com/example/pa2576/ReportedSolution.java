package com.example.pa2576;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReportedSolution extends AppCompatActivity {

    ImageView solutionPhoto;
    EditText enterReportComment;
    Button reportBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_solution);
        solutionPhoto = findViewById(R.id.reportImage);
        enterReportComment = findViewById(R.id.enterRepComment);
        reportBtn = findViewById(R.id.reportbtn);
        ViewSolution.viewImage(solutionPhoto);

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataComment(enterReportComment.getText().toString(),MainActivity.usernameLogin.getText().toString(),ViewSolution.imagePath);

                openViewSolution();
            }
        });
    }
    private void openViewSolution() {
        Intent intent = new Intent(this, ViewSolution.class);
        startActivity(intent);
    }
    private void setDataComment(final String reportComment,final String username, final String solutionName) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Reporting Image");
        progressDialog.show();
        String url = "http://10.0.2.2/reportSolution.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            //Here the method checks if the response from the php code is "Successfully created an account" that it should create
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(response.equals("Successfully reported Solution")) {
                    Toast.makeText(ReportedSolution.this, response, Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ReportedSolution.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ReportedSolution.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            //here is the variables that goes into the php code
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("reportComment",reportComment);
                param.put("username",username);
                param.put("solutionName",solutionName);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(ReportedSolution.this).addToRequestQueue(request);
    }
}