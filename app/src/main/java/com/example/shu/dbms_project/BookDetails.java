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
import parsing.all_books_request;
import parsing.all_books_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shu on 24/09/2016.
 */
public class BookDetails extends AppCompatActivity {
    ListView list;
    Adapter2 a;
    View v;
    ProgressDialog pDialog;



    ArrayList<All_books> all_books = new ArrayList<All_books>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fab2);


        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load1();
            }
        });
           TextView empty2=(TextView)findViewById(R.id.empty2);
            empty2.setVisibility(View.GONE);
            load1();
        }
    public void load1()
    {
        pDialog = new ProgressDialog(BookDetails.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        ApiService client = RetroClient.getApiService();
        Call<all_books_response> allBooksResponse = client.getLogin7(new all_books_request("1"));
        allBooksResponse.enqueue(new Callback<all_books_response>() {

            @Override
            public void onResponse(Call<all_books_response> call, Response<all_books_response> response) {

                if (response.body().getSuccess().equals("true")) {
                    TextView empty2=(TextView) findViewById(R.id.empty2);
                    all_books.clear();
                    empty2.setVisibility(View.GONE);
                    all_books = response.body().getBooks();

                    list = (ListView)findViewById(R.id.listview2);
                    a = new Adapter2(getApplicationContext(), all_books);
                    list.setAdapter(a);
                    pDialog.dismiss();

                } else {
                    TextView empty2=(TextView) findViewById(R.id.empty2);
                    empty2.setVisibility(View.VISIBLE);
                    all_books.clear();

                    list = (ListView)findViewById(R.id.listview2);
                    a = new Adapter2(getApplicationContext(), all_books);
                    list.setAdapter(a);
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "No books available", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<all_books_response> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Server down", Toast.LENGTH_LONG).show();
                Log.e("Error:", t.toString());
            }
        });
    }
}


