package com.example.pa2576;




import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeeSolutions extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> idArraybtn = new ArrayList<>();
    ArrayList<Integer> idArrayVotes = new ArrayList<>();
    ArrayList<Button> btnArray = new ArrayList<>();
    ArrayList<TextView> votTextArray = new ArrayList<>();

    String[] votesArray = new String[8];
    String[] nameOfPhotoArray = new String[8];
    Button add;
    public static String nameOfPhoto;
    public static int totalVotes;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_seesolutions);


            idArraybtn.add(R.id.teacherSolOne);
            idArraybtn.add(R.id.teacherSolTwo);
            idArraybtn.add(R.id.teacherSolThree);
            idArraybtn.add(R.id.studentSolOne);
            idArraybtn.add(R.id.studentSolTwo);
            idArraybtn.add(R.id.studentSolThree);
            idArraybtn.add(R.id.studentSolFour);
            idArraybtn.add(R.id.studentSolFive);

            idArrayVotes.add(R.id.voteTeachOne);
            idArrayVotes.add(R.id.voteTeachTwo);
            idArrayVotes.add(R.id.voteTeachThree);
            idArrayVotes.add(R.id.voteStuOne);
            idArrayVotes.add(R.id.voteStuTwo);
            idArrayVotes.add(R.id.voteStuThree);
            idArrayVotes.add(R.id.voteStuFour);
            idArrayVotes.add(R.id.voteStuFive);


            add = findViewById(R.id.addSolution);



            add.setOnClickListener(this);
            for (int id : idArraybtn) {
                Button btn = findViewById(id);
                btnArray.add(btn);
            }
            for (int id : idArrayVotes) {
                TextView view = findViewById(id);
                votTextArray.add(view);
            }

            for (int i = 0; i < btnArray.size(); i++) {
                btnArray.get(i).setOnClickListener(this);
                votesArray[i] = "";
                nameOfPhotoArray[i] = "";

            }

            getTasks(Books.bookName,Tasks.taskID,"Student");
            getTasks(Books.bookName,Tasks.taskID,"Teacher");
            Toast.makeText(SeeSolutions.this, btnArray.get(6).getText().toString(), Toast.LENGTH_SHORT).show();


        }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, Tasks.class);
        startActivity(intent);
    }
    public void setTextBtn(String[] array) {
        int index=0;
        for (int i = 0; i <btnArray.size() ; i++) {

            if (!array[i].equals("")) {

                btnArray.get(i).setText("Solution " + (i + 1));
                votTextArray.get(i).setText(votesArray[i]);
                if (i > 2) {

                    btnArray.get(i).setText("Solution " + (index + 1));
                    votTextArray.get(i).setText(votesArray[i]);
                    index++;


                }

            }
        }
        if((MainActivity.access.equals("Student") || MainActivity.access.equals("Admin")) && btnArray.get(7).getText().toString().equals("Solution 5")){
            add.setVisibility(View.GONE);
        }
        else if(MainActivity.access.equals("Teacher") && btnArray.get(2).getText().toString().equals("Solution 3")){
            add.setVisibility(View.GONE);
        }
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addSolution:
                Intent intent = new Intent(this, uploadSolution.class);
                startActivity(intent);
            break;
            default:
                checkPressedBtn(v.getId());
                break;
        }
    }



    private void checkPressedBtn(int id) {


        for (int i = 0; i < btnArray.size(); i++) {
            if (btnArray.get(i).getId() == id) {
                Intent intent = new Intent(this, ViewSolution.class);
                nameOfPhoto = nameOfPhotoArray[i];
                totalVotes =  Integer.parseInt(votesArray[i]);
                Toast.makeText(SeeSolutions.this,nameOfPhoto, Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        }
    }

    public void sortDataFromDataseIntoArrays(String[] imgArray, String[] votArray,String userType){

            if(userType.equals("Teacher")){
                for (int i = 0; i < imgArray.length; i++) {
                    nameOfPhotoArray[i] = imgArray[i];
                    votesArray[i] = votArray[i];
                }
            }
            else{
                for (int i = 0; i < imgArray.length; i++) {
                    nameOfPhotoArray[i+3] = imgArray[i];
                    votesArray[i+3] = votArray[i];

                }
            }


    }

    public void getTasks(final String book, final String task, final String userType) {
        final ProgressDialog progressDialog = new ProgressDialog(SeeSolutions.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Task");
        progressDialog.show();
        String url = "http://10.0.2.2/getAllSolutions.php";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("no images found")){
                    progressDialog.dismiss();


                    setTextBtn(nameOfPhotoArray);
                }
                else{
                    progressDialog.dismiss();
                    btnArray.get(0).setText(response);
                    String[] taskArray = btnArray.get(0).getText().toString().split("_");
                    String[] imgArray = taskArray[0].split(",");
                    String[] votArray  = taskArray[1].split(",");
                    btnArray.get(0).setText("");
                    sortDataFromDataseIntoArrays(imgArray,votArray,userType);

                    setTextBtn(nameOfPhotoArray);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SeeSolutions.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("book", book);
                param.put("task",task);
                param.put("userType",userType);
                return param;

            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(SeeSolutions.this).addToRequestQueue(request);


    }

/*
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.teacherSolOne:
                    Toast.makeText(this, "Teacher Solution one is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.teacherSolTwo:
                    Toast.makeText(this, "Teacher Solution two is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.teacherSolThree:
                    Toast.makeText(this, "Teacher Solution three is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.studentSolOne:
                    Toast.makeText(this, "Student Solution one is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.studentSolTwo:
                    Toast.makeText(this, "Student Solution two is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.studentSolThree:
                    Toast.makeText(this, "Student Solution three is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.studentSolFour:
                    Toast.makeText(this, "Student Solution four is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.studentSolFive:
                    Toast.makeText(this, "Student Solution five is clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.addSolution:
                    Toast.makeText(this, "Add a solution", Toast.LENGTH_SHORT).show();
                    break;
            }

        }

 */

    }

