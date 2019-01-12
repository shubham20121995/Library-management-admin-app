package com.example.shu.dbms_project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import parsing.RetroClient;
import parsing.*;
import retrofit2.Call;
import retrofit2.Callback;


public class returnbook extends AppCompatActivity {
    ProgressDialog pDialog;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText return2 = (EditText) findViewById(R.id.return2);
        EditText return1 = (EditText) findViewById(R.id.return1);
        if (scanResult.getContents() != null) {

            if (requestCode == 0) {

                return2.setText(String.valueOf(scanResult.getContents()));
            } else {
                DESKeySpec keySpec = null;
                try {

                    keySpec = new DESKeySpec("yesterday".getBytes("UTF8"));
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = keyFactory.generateSecret(keySpec);

                    byte[] encrypedPwdBytes = Base64.decode(scanResult.getContents(), Base64.DEFAULT);

                    Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
                    String s = new String(plainTextPwdBytes, "UTF-8");
                    return1.setText(s);
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.returnbook);

        final DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

        Button return_button = (Button) findViewById(R.id.return_button);
        TextView return_t2=(TextView)findViewById(R.id.return_t2);
        TextView return_t1=(TextView)findViewById(R.id.return_t1);

        return_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(returnbook.this,1);
                integrator.initiateScan();
            }
        });

        return_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(returnbook.this,0);
                integrator.initiateScan();
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                EditText reg_no = (EditText) findViewById(R.id.return1);
                EditText book_id = (EditText) findViewById(R.id.return2);


                String a, b;

                a = reg_no.getText().toString();
                b = book_id.getText().toString();


                pDialog = new ProgressDialog(returnbook.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client= RetroClient.getApiService();
                Call<returnbookResponse> Response=client.getLogin4(new returnbookRequest(a,b));
                Response.enqueue(new Callback<returnbookResponse>() {

                    @Override
                    public void onResponse(Call<returnbookResponse> call, retrofit2.Response<returnbookResponse> response) {

                        if(response.body().getSuccess().equals("1"))
                        {
                            pDialog.dismiss();
                            success_alert("SUCCESSFULL","Book returned successfully");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("0")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not returned. Wrong book id.");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("3")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Enter all details");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("5")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Wrong details");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("6")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not returned");
                            clear_text();
                        }
                        else if(response.body().getSuccess().equals("4")){
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Book not returned");
                            clear_text();
                        }

                        else {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL","Unknown error");
                            clear_text();
                        }



                    }

                    @Override
                    public void onFailure(Call<returnbookResponse> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server down",Toast.LENGTH_LONG).show();
                        clear_text();
                        Log.e("Error:",t.toString());
                    }
                });

            }
        });

    }

    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(returnbook.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(returnbook.this);
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

    void clear_text()
    {
        EditText book_id = (EditText) findViewById(R.id.return2);
        EditText reg_no = (EditText) findViewById(R.id.return1);

        book_id.setText("");
        reg_no.setText("");

    }
}
