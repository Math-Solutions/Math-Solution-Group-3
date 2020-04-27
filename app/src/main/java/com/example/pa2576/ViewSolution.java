package com.example.pa2576;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

    public class ViewSolution  extends AppCompatActivity implements View.OnClickListener {
        ImageButton camera;
        ImageView solutionPhoto;
        EditText comment, nameOfPhoto;
        Button uploadButton;
        ImageButton galleryButton;
        String name = "Name";

        String imagePath;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upload_solution);

            nameOfPhoto = findViewById(R.id.nameOfPhoto);
            camera = findViewById(R.id.cameraBtn);
            solutionPhoto = findViewById(R.id.picIV);
            comment = findViewById(R.id.commentToSolutionText);
            uploadButton = findViewById(R.id.uploadBtn);
            galleryButton = findViewById(R.id.galleryBtn);
            camera.setOnClickListener(this);
            uploadButton.setOnClickListener(this);
            galleryButton.setOnClickListener(this);

            getSolution(name);



        }


        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cameraBtn:


                    break;
                case R.id.uploadBtn:
                    viewImage();

                    break;
                case R.id.galleryBtn:



                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }

        private void viewImage() {
            String url = "http://10.0.2.2/" + imagePath;
            nameOfPhoto.setText(url);
            Picasso.get().load(url).into(solutionPhoto);

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

                        progressDialog.dismiss();
                        Toast.makeText(ViewSolution.this, response, Toast.LENGTH_SHORT).show();

                        uploadButton.setText(response);
                        String[] getData = uploadButton.getText().toString().split(",");
                        imagePath =  getData[0];

                        viewImage();
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


