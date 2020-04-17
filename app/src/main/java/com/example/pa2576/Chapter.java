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

    ArrayList<Integer> idArray = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();


    ArrayList<Integer> chapterArray = new ArrayList<>();
    ArrayList<Integer> nrofTaskinChap = new ArrayList<>();


    String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        courseName = getIntent().getStringExtra("CHOSEN_BOOK");

        setTitle(getIntent().getStringExtra("CHOSEN_BOOK"));
        idArray.add(R.id.button1);
        idArray.add(R.id.button2);
        idArray.add(R.id.button3);
        idArray.add(R.id.button4);
        idArray.add(R.id.button5);
        idArray.add(R.id.button6);
        idArray.add(R.id.button7);
        idArray.add(R.id.button8);
        idArray.add(R.id.button9);
        idArray.add(R.id.button10);
        idArray.add(R.id.button11);
        idArray.add(R.id.button12);
        idArray.add(R.id.button13);

        //fill the array with buttons
        for (int id : idArray) {
            Button btn = findViewById(id);
            btnArray.add(btn);


        }
        //fills the array with the nr om the chapters it has
       // for (int i = 0; i <nrofChapters ; i++) {
         //   chapterArray.add(i);
        //}

        //makes all the buttons clickable
        for (int i = 0; i < btnArray.size(); i++) {
            btnArray.get(i).setOnClickListener(this);

        }

        nrofTaskinChap.add(12);
        nrofTaskinChap.add(3);
        nrofTaskinChap.add(7);
        nrofTaskinChap.add(13);
        nrofTaskinChap.add(6);
        nrofTaskinChap.add(11);
        nrofTaskinChap.add(10);

    }
    //sets the texts of all the buttons
    public void setTextBtn(int nrofChapters) {

        for (int i = 0; i <nrofChapters ; i++) {

            btnArray.get(i).setText("Chapter " +(i+1)+"");

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



    //checks which button that is pressed and opens task.class and send som variables to the task class
    private void checkPressedBtn(int id) {


        for (int i = 0; i < chapterArray.size(); i++) {
            if (btnArray.get(i).getId() == id) {
                Intent intent = new Intent(this, Tasks.class);
                intent.putExtra("CHAPTER_NR", (i+1));
                intent.putExtra("NR_OF_TASKS_IN_CHAPTER",nrofTaskinChap.get(i));
                intent.putExtra("CHOSEN_BOOK",getIntent().getStringExtra("CHOSEN_BOOK") + " -> Cap " +(i+1) + "");
                startActivity(intent);
            }
        }
    }

    public void getBooks(final String chapter) {
        final ProgressDialog progressDialog = new ProgressDialog(Chapter.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Books");
        progressDialog.show();
        String url = "http://192.168.1.112/chapters.php";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                btnArray.get(0).setText(response);
                setTextBtn(Integer.parseInt(btnArray.get(0).getText().toString()));
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
                param.put("chapter", chapter);
                return param;

            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Chapter.this).addToRequestQueue(request);


    }
}



