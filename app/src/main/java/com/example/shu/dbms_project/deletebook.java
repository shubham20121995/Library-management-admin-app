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

import parsing.*;
import parsing.RetroClient;
import parsing.addbookRequest;
import parsing.addbookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class deletebook extends AppCompatActivity {
    ProgressDialog pDialog;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText delete = (EditText) findViewById(R.id.delete);
        if (scanResult.getContents() != null) {
            delete.setText(String.valueOf(scanResult.getContents()));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletebook);

        final DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

        Button delete_button = (Button) findViewById(R.id.delete_button);

        TextView delete1=(TextView) findViewById(R.id.delete1);
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(deletebook.this,0);
                integrator.initiateScan();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                final EditText book_id = (EditText) findViewById(R.id.delete);
                final EditText copies = (EditText) findViewById(R.id.delete2);
                String c,d;

                c = book_id.getText().toString();

                d = copies.getText().toString();



                pDialog = new ProgressDialog(deletebook.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client= RetroClient.getApiService();
                Call<delete_bookResponse> Response=client.getLogin2(new delete_bookRequest(c,d));
                Response.enqueue(new Callback<delete_bookResponse>() {

                    @Override
                    public void onResponse(Call<delete_bookResponse> call, Response<delete_bookResponse> response) {

                        if(response.body().getSuccess().equals("1"))
                        {
                            pDialog.dismiss();
                            success_alert("SUCCESSFULL","Book deleted successfully");
                            book_id.setText("");
                        }
                        else if(response.body().getSuccess().equals("0")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not deleted");
                            book_id.setText("");
                        }
                        else if(response.body().getSuccess().equals("3")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Enter all details");
                            book_id.setText("");
                        }
                        else if(response.body().getSuccess().equals("4")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not found");
                            book_id.setText("");
                        }
                        else {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Unknown error");
                            book_id.setText("");
                        }



                    }

                    @Override
                    public void onFailure(Call<delete_bookResponse> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server down",Toast.LENGTH_LONG).show();
                        book_id.setText("");
                        Log.e("Error:",t.toString());
                    }
                });

            }
        });

    }
    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(deletebook.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(deletebook.this);
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
