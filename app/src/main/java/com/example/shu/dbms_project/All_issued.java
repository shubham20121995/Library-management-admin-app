package com.example.shu.dbms_project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import parsing.ApiService;
import parsing.*;
import parsing.Books2;
import parsing.RetroClient;
import parsing.books_issued_request;
import parsing.books_issued_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shubham on 12/27/2016.
 */

public class All_issued extends AppCompatActivity{

    ProgressDialog pDialog;
    ArrayList<Books2> issued_books = new ArrayList<Books2>();
    ListView list;
    Adapter5 ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_issued);

        list= (ListView) findViewById(R.id.listview6);
        FloatingActionButton fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load1();
            }
        });
        TextView empty3=(TextView)findViewById(R.id.empty3);
        empty3.setVisibility(View.GONE);
        load1();

    }

    public void load1()
    {
        pDialog = new ProgressDialog(All_issued.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();


        ApiService client= RetroClient.getApiService();
        Call<all_issueResponse> Response=client.getLogin10(new all_issueRequest("1"));
        Response.enqueue(new Callback<all_issueResponse>() {

            @Override
            public void onResponse(Call<all_issueResponse> call, Response<all_issueResponse> response) {

                if(response.body().getSuccess().equals("true"))
                {
                    TextView empty3=(TextView) findViewById(R.id.empty3);
                    empty3.setVisibility(View.GONE);
                    issued_books.clear();
                    issued_books=response.body().getBooks();
                    ad=new Adapter5(All_issued.this,issued_books);
                    list.setAdapter(ad);

                    pDialog.dismiss();

                }
                else if(response.body().getSuccess().equals("3"))
                {
                    issued_books.clear();

                    pDialog.dismiss();
                    Toast.makeText(All_issued.this,"Unknown error",Toast.LENGTH_LONG).show();
                }
                else
                {   issued_books.clear();
                    TextView empty3=(TextView) findViewById(R.id.empty3);
                    empty3.setVisibility(View.VISIBLE);
                    pDialog.dismiss();
                    Toast.makeText(All_issued.this,"No books issued",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<all_issueResponse> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(All_issued.this,"Server down",Toast.LENGTH_LONG).show();
                Log.e("Error:",t.toString());
            }
        });
    }


}


