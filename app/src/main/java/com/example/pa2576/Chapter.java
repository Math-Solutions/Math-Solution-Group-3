package com.example.pa2576;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chapter extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Button> btnArray = new ArrayList<>();


    //String bookName;
    public static String chapter;
    private BooksModel bModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        //bookName = getIntent().getStringExtra("BOOK_NAME");
        setTitle(Books.bookName);
        for (int i = 1; i <= BooksController.NrofButtons; i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bModel = new BooksModel(btnArray);
        //makes all the buttons clickable
        for (int i = 0; i < btnArray.size(); i++) {
            btnArray.get(i).setOnClickListener(this);
        }
        getChapters(Books.bookName);
    }
    //sets the texts of all the buttons
    public void setTextBtn(String nrOfChapters) {
        int temp = Integer.parseInt(nrOfChapters);
        for (int i = 0; i <temp ; i++) {
            btnArray.get(i).setText("Chapter " +(i+1)+"");
        }
        for(int i=0; i<btnArray.size();i++) {
            if (btnArray.get(i).getText().equals("")) {
                btnArray.get(i).setVisibility(View.GONE);
            }
        }
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                checkPressedBtn(v.getId());
                break;
        }
    }
    //checks which button that is pressed and opens task.class and send som variables to the task class
    private void checkPressedBtn(int id) {
        for (int i = 0; i < btnArray.size(); i++) {
            if (btnArray.get(i).getId() == id) {
                Intent intent = new Intent(this, Tasks.class);
                chapter = i+1+"";
                startActivity(intent);
            }
        }
    }
    //Input value book into database and extract how many chapters that book has
    public void getChapters(final String book) {
        final ProgressDialog progressDialog = new ProgressDialog(Chapter.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Books");
        progressDialog.show();
        String url = "http://10.0.2.2/chapters.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                setTextBtn(btnArray.get(0).getText().toString());
                //Toast.makeText(Chapter.this,Books.bookName, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Chapter.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("book", book);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Chapter.this).addToRequestQueue(request);
    }
}



