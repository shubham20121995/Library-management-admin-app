package com.example.shu.dbms_project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import parsing.ApiService;
import parsing.RetroClient;
import parsing.addbookRequest;
import parsing.addbookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shu on 24/09/2016.
 */
public class addbook extends AppCompatActivity {

Context l;
    ProgressDialog pDialog;
    final Context context = this;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText e1 = (EditText) findViewById(R.id.e1);
        if (scanResult.getContents() != null) {
            e1.setText(String.valueOf(scanResult.getContents()));
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        final DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

        Button book_button = (Button) findViewById(R.id.book_button);
        EditText date_pur = (EditText) findViewById(R.id.e7);
        TextView t1=(TextView)findViewById(R.id.t1);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(addbook.this,0);
                integrator.initiateScan();
            }
        });

        date_pur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                EditText book_id = (EditText) findViewById(R.id.e1);
                EditText book_name = (EditText) findViewById(R.id.e2);
                EditText author = (EditText) findViewById(R.id.e3);
                EditText publication = (EditText) findViewById(R.id.e4);
                EditText quantity = (EditText) findViewById(R.id.e5);
                EditText edition = (EditText) findViewById(R.id.e6);
                EditText date_pur = (EditText) findViewById(R.id.e7);
                EditText price = (EditText) findViewById(R.id.e8);



                String a, b, c, d,e,f,g,h;

                a = book_id.getText().toString();
                b = book_name.getText().toString();
                c = author.getText().toString();
                d = publication.getText().toString();
                e = quantity.getText().toString();
                f = edition.getText().toString();
                g = date_pur.getText().toString();
                h = price.getText().toString();

                pDialog = new ProgressDialog(addbook.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client= RetroClient.getApiService();
                Call<addbookResponse> Response=client.getLogin(new addbookRequest(a,c,b,d,f,e,g,h));
                Response.enqueue(new Callback<addbookResponse>() {

                    @Override
                    public void onResponse(Call<addbookResponse> call, Response<addbookResponse> response) {

                        if(response.body().getSuccess().equals("1"))
                        {
                            pDialog.dismiss();
                            success_alert("SUCCESSFULL","Book added successfully");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("0")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not added. Check book id.");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("3")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Enter all details");
                            clear_text();
                        }
                        else {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Unknown error");
                            clear_text();
                        }



                    }

                    @Override
                    public void onFailure(Call<addbookResponse> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server down",Toast.LENGTH_LONG).show();
                        clear_text();
                        Log.e("Error:",t.toString());
                    }
                });



            }
        });




    }



    void clear_text()
    {
        EditText book_id = (EditText) findViewById(R.id.e1);
        EditText book_name = (EditText) findViewById(R.id.e2);
        EditText author = (EditText) findViewById(R.id.e3);
        EditText publication = (EditText) findViewById(R.id.e4);
        EditText quantity = (EditText) findViewById(R.id.e5);
        EditText edition = (EditText) findViewById(R.id.e6);
        EditText date_pur = (EditText) findViewById(R.id.e7);
        EditText price = (EditText) findViewById(R.id.e8);

        book_id.setText("");
        book_name.setText("");
        author.setText("");
        publication.setText("");
        quantity.setText("");
        edition.setText("");
        date_pur.setText("");
        price.setText("");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("id",R.id.e7);
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(addbook.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(addbook.this);
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
