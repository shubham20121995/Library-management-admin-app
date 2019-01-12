package com.example.shu.dbms_project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import parsing.*;
import parsing.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Shu on 24/09/2016.
 */
public class searchbook extends AppCompatActivity {
    ProgressDialog pDialog;
    ArrayList<Book_details> details = new ArrayList<Book_details>();
    com.example.shu.dbms_project.NestedListView list;
    Adapter3 ad;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText b_id = (EditText) findViewById(R.id.b_id);
        if (scanResult.getContents() != null) {
            b_id.setText(String.valueOf(scanResult.getContents()));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbook);
        list=(com.example.shu.dbms_project.NestedListView)findViewById(R.id.listview3);

        Button search_button = (Button)findViewById(R.id.search_button);
        TextView search_b = (TextView) findViewById(R.id.search_b);
        search_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(searchbook.this,0);
                integrator.initiateScan();
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                EditText b_id=(EditText)findViewById(R.id.b_id);
                final TextView b_name=(TextView)findViewById(R.id.b_name);
                final TextView b_author=(TextView)findViewById(R.id.b_author);
                final TextView b_publication=(TextView)findViewById(R.id.b_publication);
                final TextView b_quantity=(TextView)findViewById(R.id.b_quantity);
                final TextView b_edition=(TextView)findViewById(R.id.b_edition);
                final TextView b_price=(TextView)findViewById(R.id.b_price);
                final TextView b_date_pur=(TextView)findViewById(R.id.b_date_pur);
                String a, b, c, d,e,f,g,h;

                a = b_id.getText().toString();

                pDialog = new ProgressDialog(searchbook.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client= RetroClient.getApiService();
                Call<searchbookResponse> Response=client.getLogin5(new searchbookRequest(a));
                Response.enqueue(new Callback<searchbookResponse>() {

                    @Override
                    public void onResponse(Call<searchbookResponse> call, retrofit2.Response<searchbookResponse> response) {

                        if(response.body().getSuccess().equals("1"))
                        {
                            b_name.setText(response.body().getBook_name());
                            b_author.setText(response.body().getAuthor());
                            b_publication.setText(response.body().getPublication());
                            b_quantity.setText(response.body().getNo_of_copies());
                            b_edition.setText(response.body().getEdition());
                            b_price.setText(response.body().getPrice());
                            b_date_pur.setText(response.body().getDate_pur());
                            details.clear();
                            details=response.body().getDetails();
                            Log.e("Hello",response.body().getDetails().toString());

                            ad=new Adapter3(getApplicationContext(),details);
                            list.setAdapter(ad);


                            pDialog.dismiss();

                        }
                        else if(response.body().getSuccess().equals("0")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not found. Check book id.");

                        }
                        else if(response.body().getSuccess().equals("3")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Enter all details");

                        }
                        else {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Unknown error");

                        }



                    }

                    @Override
                    public void onFailure(Call<searchbookResponse> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server down",Toast.LENGTH_LONG).show();

                        Log.e("Error:",t.toString());
                    }
                });



            }
        });

    }
    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(searchbook.this);
        builder.setIcon(R.drawable.success);
        builder.setTitle(title);
        builder.setMessage(body)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }

                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();

        // show it
        alertDialog.show();
    }

    public void fail_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(searchbook.this);
        builder.setIcon(R.drawable.fail);
        builder.setTitle(title);
        builder.setMessage(body)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }

                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();

        // show it
        alertDialog.show();
    }
}
