package com.example.pa2576;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.Map;

    public class ViewSolution  extends AppCompatActivity implements View.OnClickListener {

        ImageView solutionPhoto;
        TextView comment;
        Button removeSolBtn,upVotebtn,downVotebtn,reportbtn;
        String name = SeeSolutions.nameOfPhoto;
        int votes = SeeSolutions.totalVotes;
        String imagePath;
        int voteCheck=0;
        String username;
        Button fullScreen;

        ImageView fullScreenImage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_solution);

            fullScreen = findViewById(R.id.fullScreenBtn);
            fullScreenImage = findViewById(R.id.solutionImageFullScreen);
            solutionPhoto = findViewById(R.id.solutionImage);
            comment = findViewById(R.id.commentToSolution);
            removeSolBtn = findViewById(R.id.removeSolBtn);
            upVotebtn = findViewById(R.id.upvoteBtn);
            downVotebtn = findViewById(R.id.downvoteBtn);
            reportbtn = findViewById(R.id.report);

            removeSolBtn.setOnClickListener(this);
            upVotebtn.setOnClickListener(this);
            downVotebtn.setOnClickListener(this);
            reportbtn.setOnClickListener(this);
            fullScreen.setOnClickListener(this);

            getSolution(name);



        }
        public void onBackPressed(){
            super.onBackPressed();
            Intent intent = new Intent(this, SeeSolutions.class);
            startActivity(intent);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.removeSolBtn:
                    removeSolution(name);

                    break;
                case R.id.upvoteBtn:

                    if(voteCheck==0){
                    votes++;
                    updateVotes(name,votes+"");
                    voteCheck++;
                }


                    break;
                case R.id.downvoteBtn:
                    if(voteCheck==0){
                        votes--;
                        updateVotes(name,votes+"");
                        voteCheck++;
                    }

                case R.id.report:



                    break;
                case R.id.fullScreenBtn:
                    setContentView(R.layout.activity_fullscreen);
                    //viewImage(fullScreenImage);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }

        private void viewImage(ImageView image) {
            String url = "http://10.0.2.2/" + imagePath;
            Picasso.get().load(url).into(image);


        }

        private void updateVotes(final String name,final String votes) {
            final ProgressDialog progressDialog = new ProgressDialog(ViewSolution.this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.setTitle("Retrieving image to database");
            progressDialog.show();
            String url = "http://10.0.2.2/updateVotes.php";

            //Toast.makeText(uploadSolution.this, "response", Toast.LENGTH_SHORT).show();
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                //Here the method checks if the response from the php code is "Successfully created an account" that it should create
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Toast.makeText(ViewSolution.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(ViewSolution.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {


                //here is the variables that goes into the php code
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("name",name);
                    param.put("votes",votes);




                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getmInstance(ViewSolution.this).addToRequestQueue(request);

        }

        private void removeSolution(final String name) {
            final ProgressDialog progressDialog = new ProgressDialog(ViewSolution.this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.setTitle("Retrieving image to database");
            progressDialog.show();
            String url = "http://10.0.2.2/removeImage.php";

            //Toast.makeText(uploadSolution.this, "response", Toast.LENGTH_SHORT).show();
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                //Here the method checks if the response from the php code is "Successfully created an account" that it should create
                public void onResponse(String response) {

                    if(response.equals("The picture has been deleted")){
                        progressDialog.dismiss();
                        Toast.makeText(ViewSolution.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ViewSolution.this, SeeSolutions.class));

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(ViewSolution.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(ViewSolution.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {


                //here is the variables that goes into the php code
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("name",name);

                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getmInstance(ViewSolution.this).addToRequestQueue(request);

        }

        private void getSolution(final String name) {
            final ProgressDialog progressDialog = new ProgressDialog(ViewSolution.this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.setTitle("Retrieving image to database");
            progressDialog.show();
            String url = "http://10.0.2.2/getImage.php";

            //Toast.makeText(uploadSolution.this, "response", Toast.LENGTH_SHORT).show();
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                //Here the method checks if the response from the php code is "Successfully created an account" that it should create
                public void onResponse(String response) {

                    if(response.equals("No image found")){
                        progressDialog.dismiss();
                        Toast.makeText(ViewSolution.this, response, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        progressDialog.dismiss();


                        reportbtn.setText(response);
                        String[] getData = reportbtn.getText().toString().split(",");
                        imagePath =  getData[0];
                        username = getData[2];
                        reportbtn.setText("Report");
                        comment.setText(getData[1]);

                        if(!MainActivity.access.equals("Teacher") && !MainActivity.access.equals("Admin") && !username.equals(MainActivity.usernameLogin.getText().toString())){
                            removeSolBtn.setVisibility(View.GONE);
                        }
                        viewImage(solutionPhoto);

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(ViewSolution.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {


                //here is the variables that goes into the php code
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("name",name);



                    return param;

                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getmInstance(ViewSolution.this).addToRequestQueue(request);

        }
    }


