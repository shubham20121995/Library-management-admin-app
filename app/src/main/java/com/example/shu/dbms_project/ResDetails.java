package com.example.shu.dbms_project;

/**
 * Created by Shu on 29/09/2016.
 */
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

import java.util.ArrayList;

import parsing.All_books;
import parsing.ApiService;
import parsing.RetroClient;
import parsing.*;
import parsing.all_books_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shu on 24/09/2016.
 */
public class ResDetails extends AppCompatActivity {
    ListView list;
    Adapter6 a;
    View v;
    ProgressDialog pDialog;



    ArrayList<res_books> all_books = new ArrayList<res_books>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fab4);


        FloatingActionButton fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load1();
            }
        });

        load1();
    }
    public void load1()
    {
        pDialog = new ProgressDialog(ResDetails.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        ApiService client = RetroClient.getApiService();
        Call<display_reserved_booksResponse> display_reserved_booksresponse = client.getLogin11(new display_reserved_booksRequest("1"));
        display_reserved_booksresponse.enqueue(new Callback<display_reserved_booksResponse>() {

            @Override
            public void onResponse(Call<display_reserved_booksResponse> call, Response<display_reserved_booksResponse> response) {

                if (response.body().getSuccess().equals("true")) {

                    all_books.clear();

                    all_books = response.body().getBooks();

                    list = (ListView)findViewById(R.id.listview4);
                    a = new Adapter6(getApplicationContext(), all_books);
                    list.setAdapter(a);
                    pDialog.dismiss();

                } else {


                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "No books available", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<display_reserved_booksResponse> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Server down", Toast.LENGTH_LONG).show();
                Log.e("Error:", t.toString());
            }
        });
    }
}


