package com.example.pa2576;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksController{

    private BooksModel bModel;

    public BooksController(BooksModel bModel){
        this.bModel = bModel;
    }
    public BooksController(int id) {

        this.bModel.setId(id);
    }
    public void setActivity(Context activity){
        bModel.setActivity(activity);
    }
    public void setNextActivity(Object nextActivity){
        bModel.setNextActivity(nextActivity);
    }
    public Context getActivity(){
        return bModel.getActivity();
    }
    public Object getNextActivity(){
        return bModel.getNextActivity();
    }
    public ArrayList<Button> getArray(){
        return  bModel.getArray();
    }
    public void setArray(ArrayList<Button> array){
        bModel.setArray(array);
    }
    public String getString(){
        return bModel.getString();
    }
    public void setString(String str){
        bModel.setString(str);
    }
    public String getUrl(){
        return bModel.getUrl();
    }
    public void setUrl(String str){
        bModel.setUrl(str);
    }
    public int getId(){
        return bModel.getId();
    }
    public void setId(int id){
        bModel.setId(id);
    }
    public void setStrArray(String[] strArray){
        bModel.setStrArray(strArray);
    }
    public String[] getStrArray(){
        return  bModel.getStrArray();
    }

    public void setTextBtn(){
        for(int i=0; i<getStrArray().length;i++) {
            getArray().get(i).setText(getStrArray()[i]);
        }
        for(int i=0; i<getArray().size();i++) {
            if(getArray().get(i).getText().equals("")){
                getArray().get(i).setVisibility(View.GONE);
            }
        }
    }
    public void setTextBtnInt() {
        int temp = Integer.parseInt(getArray().get(0).getText().toString());
        for (int i = 0; i <temp ; i++) {
            getArray().get(i).setText(getString()+"" +(i+1)+"");
        }
        for(int i=0; i<getArray().size();i++) {
            if (getArray().get(i).getText().equals("")) {
                getArray().get(i).setVisibility(View.GONE);
            }
        }
    }
    public void getPressedBtnCtr(int id) {
       for(int i=0; i<getArray().size();i++) {
            if (getArray().get(i).getId() == id) {
               setString(getArray().get(i).getText().toString());
               setId(i+1);
            }
        }
    }
    public void getbtnDataFromDB(final String stringVar, final String string,final String stringVar2, final String string2) {
       /* final ProgressDialog progressDialog = new ProgressDialog(getActivity().getApplicationContext());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Courses");
        progressDialog.show();*/
        StringRequest request = new StringRequest(Request.Method.POST, getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                getArray().get(0).setText(response);
                String[] strArray = getArray().get(0).getText().toString().split(",");
                setStrArray(strArray);
                if(stringVar=="book" && stringVar2 != "chapter") {
                    setTextBtnInt();
                }
                else{
                    setTextBtn();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put(stringVar, string);
                param.put(stringVar2,string2);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(getActivity()).addToRequestQueue(request);
    }
    public void openActivity() {
      // getContext().startActivity(new Intent(getApplicationContext(),Books.class));
    }
}