package com.example.pa2576;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class uploadSolution extends AppCompatActivity implements View.OnClickListener {
    ImageButton camera;
    ImageView solutionPhoto;
    EditText comment, nameOfPhoto;
    Button uploadButton;
    ImageButton galleryButton;
    private static final int PICK_IMAGE = 100;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;


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


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cameraBtn:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICK_IMAGE);

                break;
            case R.id.uploadBtn:
                uploadSolution();

                break;
            case R.id.galleryBtn:

                selectImage();

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri path = data.getData();
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){


                bitmap =(Bitmap) data.getExtras().get("data");
                solutionPhoto.setImageBitmap(bitmap);
                solutionPhoto.setVisibility(View.VISIBLE);



        }

        else if(requestCode == IMG_REQUEST && data!=null) {


            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                solutionPhoto.setImageBitmap(bitmap);
                solutionPhoto.setVisibility(View.VISIBLE);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String imageToString(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }


    private void uploadSolution() {
        final ProgressDialog progressDialog = new ProgressDialog(uploadSolution.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Uploading image to database");
        progressDialog.show();
        String url = "http://10.0.2.2/uploadImages.php";

        //Toast.makeText(uploadSolution.this, "response", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            //Here the method checks if the response from the php code is "Successfully created an account" that it should create
            public void onResponse(String response) {
                if (response.equals("The Solution was uploaded successfully")) {
                    progressDialog.dismiss();
                    Toast.makeText(uploadSolution.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(uploadSolution.this, MainActivity.class));
                }
                else{
                    //displays the respone that was received from the php code
                    progressDialog.dismiss();
                    Toast.makeText(uploadSolution.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(uploadSolution.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {


            //here is the variables that goes into the php code
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("image",imageToString(bitmap));
                param.put("name",nameOfPhoto.getText().toString().trim());
                param.put("username","MainActivity.usernameLogin.getText().toString()");
                param.put("comment",comment.getText().toString());
                param.put("taskID","Tasks.taskID");
                param.put("bookID","Books.bookName");


                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(uploadSolution.this).addToRequestQueue(request);

    }
}
