package com.example.shu.dbms_project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import parsing.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shu on 24/09/2016.
 */
public class issuebook extends AppCompatActivity {
    ProgressDialog pDialog;
    Date da;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.e("request code",String.valueOf(requestCode));
        Log.e("result code",String.valueOf(resultCode));
        Log.e("intent",String.valueOf(intent));
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);


        if (scanResult.getContents() != null) {
            if (requestCode == 0) {
                EditText issue1 = (EditText) findViewById(R.id.issue1);
                issue1.setText(scanResult.getContents());
            } else {
                DESKeySpec keySpec = null;
                try {
                    EditText issue2 = (EditText) findViewById(R.id.issue2);
                    keySpec = new DESKeySpec("yesterday".getBytes("UTF8"));
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = keyFactory.generateSecret(keySpec);

                    byte[] encrypedPwdBytes = Base64.decode(scanResult.getContents(), Base64.DEFAULT);

                    Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
                    String s = new String(plainTextPwdBytes, "UTF-8");
                    issue2.setText(s);
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
        setContentView(R.layout.issuebook);
        final DatabaseHandler dh;
        dh = new DatabaseHandler(getApplicationContext());
        Date date=new Date();
        String str;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=dateFormat.parse(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        str=dateFormat.format(date);

        EditText issue_date = (EditText) findViewById(R.id.issue3);
        issue_date.setText(str);
        Button issue_button = (Button) findViewById(R.id.issue_button);

        issue_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showDatePickerDialog(v, R.id.issue3);
            }
        });
        EditText return_date = (EditText) findViewById(R.id.issue4);
        return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v,R.id.issue4);
            }
        });

        TextView issue_t1=(TextView)findViewById(R.id.issue_t1);
        TextView issue_t2=(TextView)findViewById(R.id.issue_t2);
        issue_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(issuebook.this,0);
                integrator.initiateScan();
            }
        });

        issue_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(issuebook.this,1);
                integrator.initiateScan();
            }
        });
        issue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

               final EditText book_id = (EditText) findViewById(R.id.issue1);
                final EditText reg_no = (EditText) findViewById(R.id.issue2);
                final EditText issue_date = (EditText) findViewById(R.id.issue3);
                final EditText return_date = (EditText) findViewById(R.id.issue4);

                String a,b,c,d;

                a = book_id.getText().toString();
                b = reg_no.getText().toString();
                c = issue_date.getText().toString();
                d = return_date.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    da = dateFormat.parse(d);


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if((da!=null)) {

                        pDialog = new ProgressDialog(issuebook.this);
                        pDialog.setMessage("Please wait...");
                        pDialog.setCancelable(false);
                        pDialog.show();


                        ApiService client = RetroClient.getApiService();
                        Call<issue_bookResponse> Response = client.getLogin3(new issue_bookRequest(b, a, c, d));
                        Response.enqueue(new Callback<issue_bookResponse>() {

                            @Override
                            public void onResponse(Call<issue_bookResponse> call, retrofit2.Response<issue_bookResponse> response) {

                                if (response.body().getSuccess().equals("1")) {
                                    pDialog.dismiss();
                                    success_alert("SUCCESSFULL", "Book issued successfully");
                                    clear_text();
                                } else if (response.body().getSuccess().equals("0")) {
                                    pDialog.dismiss();
                                    fail_alert("UNSUCCESSFULL", "Book not available");
                                    clear_text();
                                } else if (response.body().getSuccess().equals("3")) {
                                    pDialog.dismiss();
                                    fail_alert("UNSUCCESSFULL", "Enter all details");
                                    clear_text();
                                } else if (response.body().getSuccess().equals("4")) {
                                    pDialog.dismiss();
                                    fail_alert("UNSUCCESSFULL", "Book not issued");
                                    clear_text();
                                } else if (response.body().getSuccess().equals("6")) {
                                    pDialog.dismiss();

                                    fail_alert("UNSUCCESSFULL", "BOOK ALREADY ISSUED");
                                    clear_text();
                                } else if (response.body().getSuccess().equals("7")) {
                                    pDialog.dismiss();
                                    fail_alert("UNSUCCESSFULL", "Book not issued");
                                    clear_text();
                                } else if (response.body().getSuccess().equals("5")) {
                                    pDialog.dismiss();
                                    fail_alert("UNSUCCESSFULL", "Book not issued. Invalid registration number.");
                                    clear_text();
                                } else {
                                    pDialog.dismiss();
                                    fail_alert("UNSUCCESSFULL", "Unknown error");
                                    clear_text();
                                }


                            }

                            @Override
                            public void onFailure(Call<issue_bookResponse> call, Throwable t) {
                                pDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Server down", Toast.LENGTH_LONG).show();
                                book_id.setText("");
                                Log.e("Error:", t.toString());
                            }
                        });

                }else{
                    fail_alert("CHECK DATE", "Enter return date");
                }


            }
        });

    }

    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(issuebook.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(issuebook.this);
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
        final EditText book_id = (EditText) findViewById(R.id.issue1);
        final EditText reg_no = (EditText) findViewById(R.id.issue2);
        final EditText issue_date = (EditText) findViewById(R.id.issue3);
        final EditText return_date = (EditText) findViewById(R.id.issue4);

        book_id.setText("");
        reg_no.setText("");

        return_date.setText("");

    }
    public void showDatePickerDialog(View v,int a) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("id",a);
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
