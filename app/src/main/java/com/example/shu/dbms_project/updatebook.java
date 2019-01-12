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

import parsing.*;
import parsing.RetroClient;
import parsing.searchbookRequest;
import parsing.searchbookResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Shu on 24/09/2016.
 */
public class updatebook extends AppCompatActivity {
    ProgressDialog pDialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText b_id_u8 = (EditText) findViewById(R.id.b_id_u8);
        if (scanResult.getContents() != null) {
            b_id_u8.setText(String.valueOf(scanResult.getContents()));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatebook);

        Button search_button = (Button)findViewById(R.id.search_details_button);
        TextView up1 = (TextView) findViewById(R.id.up1);
        up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(updatebook.this,0);
                integrator.initiateScan();
            }
        });
        EditText u5 = (EditText) findViewById(R.id.u5);
        u5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v,R.id.u5);
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                final EditText b_id=(EditText)findViewById(R.id.b_id_u8);
                final EditText u1=(EditText)findViewById(R.id.u1);
                final EditText u2=(EditText)findViewById(R.id.u2);
                final EditText u3=(EditText)findViewById(R.id.u3);
                final EditText u4=(EditText)findViewById(R.id.u4);
                final EditText u5=(EditText)findViewById(R.id.u5);
                final EditText u6=(EditText)findViewById(R.id.u6);
                final EditText u7=(EditText)findViewById(R.id.u7);
                String a, b, c, d,e,f,g,h;

                a = b_id.getText().toString();

                pDialog = new ProgressDialog(updatebook.this);
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
                            u1.setText(response.body().getBook_name());
                            u2.setText(response.body().getAuthor());
                            u3.setText(response.body().getPublication());
                            u6.setText(response.body().getNo_of_copies());
                            u4.setText(response.body().getEdition());
                            u7.setText(response.body().getPrice());
                            u5.setText(response.body().getDate_pur());

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


        Button update_button = (Button)findViewById(R.id.update_button);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                final EditText b_id=(EditText)findViewById(R.id.b_id_u8);
                final EditText u1=(EditText)findViewById(R.id.u1);
                final EditText u2=(EditText)findViewById(R.id.u2);
                final EditText u3=(EditText)findViewById(R.id.u3);
                final EditText u4=(EditText)findViewById(R.id.u4);
                final EditText u5=(EditText)findViewById(R.id.u5);
                final EditText u6=(EditText)findViewById(R.id.u6);
                final EditText u7=(EditText)findViewById(R.id.u7);
                String a, b, c, d,e,f,g,h;

                a = b_id.getText().toString();
                b = u1.getText().toString();
                c = u2.getText().toString();
                d = u3.getText().toString();
                e = u4.getText().toString();
                f = u5.getText().toString();
                g = u6.getText().toString();
                h = u7.getText().toString();

                pDialog = new ProgressDialog(updatebook.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client= RetroClient.getApiService();
                Call<updatebookResponse> Response=client.getLogin6(new updatebookRequest(a,c,b,d,e,g,f,h));
                Response.enqueue(new Callback<updatebookResponse>() {

                    @Override
                    public void onResponse(Call<updatebookResponse> call, retrofit2.Response<updatebookResponse> response) {

                        if(response.body().getSuccess().equals("1"))
                        {
                            success_alert("SUCCESSFULL","Book details successfully updated");
                            clear_text();
                            pDialog.dismiss();

                        }
                        else if(response.body().getSuccess().equals("0")){
                            pDialog.dismiss();
                            clear_text();
                            fail_alert("UNSUCCESSFULL","Book details not updated");

                        }
                        else if(response.body().getSuccess().equals("3")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Enter all details");

                        }
                        else {
                            pDialog.dismiss();
                            clear_text();
                            fail_alert("UNSUCCESSFULL","Unknown error");

                        }



                    }

                    @Override
                    public void onFailure(Call<updatebookResponse> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server down",Toast.LENGTH_LONG).show();

                        Log.e("Error:",t.toString());
                    }
                });



            }
        });
    }

    void clear_text()
    {
        final EditText b_id=(EditText)findViewById(R.id.b_id_u8);
        final EditText u1=(EditText)findViewById(R.id.u1);
        final EditText u2=(EditText)findViewById(R.id.u2);
        final EditText u3=(EditText)findViewById(R.id.u3);
        final EditText u4=(EditText)findViewById(R.id.u4);
        final EditText u5=(EditText)findViewById(R.id.u5);
        final EditText u6=(EditText)findViewById(R.id.u6);
        final EditText u7=(EditText)findViewById(R.id.u7);
        b_id.setText("");
        u1.setText("");
        u2.setText("");
        u3.setText("");
        u4.setText("");
        u5.setText("");
        u6.setText("");
        u7.setText("");
    }
    public void showDatePickerDialog(View v, int a) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("id",a);
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(updatebook.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(updatebook.this);
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
