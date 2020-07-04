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

public class Tasks extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Button> btnArray = new ArrayList<>();
    public static String taskID;
    String bookName;
    private BooksModel bModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        setTitle(Books.bookName + " -> " + Chapter.chapter);
        for (int i = 1; i <= BooksController.NrofButtons; i++) {
            Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            btnArray.add(button);
            btnArray.get(i-1).setOnClickListener(this);
        }
        bModel = new BooksModel(btnArray);
        getTasks(Books.bookName,Chapter.chapter);
    }
    public void setTextBtn(String[] array) {
        for (int i = 0; i <array.length ; i++) {
            btnArray.get(i).setText(array[i]);
        }
        for(int i=0; i<btnArray.size();i++) {
            if (btnArray.get(i).getText().equals("")) {
                btnArray.get(i).setVisibility(View.GONE);
            }
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                checkPressedBtn(v.getId());
                break;
        }
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Chapter.class);
        startActivity(intent);
    }
    private void checkPressedBtn(int id) {
        for (int i = 0; i < btnArray.size(); i++) {
            if (btnArray.get(i).getId() == id) {
                Intent intent = new Intent(this, SeeSolutions.class);
                taskID = btnArray.get(i).getText().toString();
                intent.putExtra("CHAPTER_NR", (i+1));
                intent.putExtra("BOOK_NAME",bookName);
                intent.putExtra("CHOSEN_BOOK",getIntent().getStringExtra("CHOSEN_BOOK") + " -> Cap " +(i+1) + "");
                startActivity(intent);
            }
        }
    }
    //Input value book into database and extract how many chapters that book has
    public void getTasks(final String book, final String chapter) {
        final ProgressDialog progressDialog = new ProgressDialog(Tasks.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Task");
        progressDialog.show();
        String url = "http://10.0.2.2/tasks.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                String[] taskArray = btnArray.get(0).getText().toString().split(",");
                setTextBtn(taskArray);
                //Toast.makeText(Tasks.this,response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Tasks.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("book", book);
                param.put("chapter",chapter);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Tasks.this).addToRequestQueue(request);
    }
}