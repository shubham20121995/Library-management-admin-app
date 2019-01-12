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
public class reissuebook extends AppCompatActivity {
    ProgressDialog pDialog;
    Date da;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText issue1 = (EditText) findViewById(R.id.re_issue1);
        EditText issue2 = (EditText) findViewById(R.id.re_issue2);
        if (scanResult.getContents() != null) {
            issue1.setText(String.valueOf(scanResult.getContents()));

            if (requestCode == 0) {
                issue1.setText(String.valueOf(scanResult.getContents()));
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
        setContentView(R.layout.reissuebook);
        final DatabaseHandler dh;
        dh = new DatabaseHandler(getApplicationContext());

        Button issue_button = (Button) findViewById(R.id.issue_button);

        EditText return_date = (EditText) findViewById(R.id.re_issue4);
        return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v,R.id.re_issue4);
            }
        });

        TextView issue_t1=(TextView)findViewById(R.id.re_issue_t1);
        TextView issue_t2=(TextView)findViewById(R.id.re_issue_t2);

        issue_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(reissuebook.this,1);
                integrator.initiateScan();
            }
        });
        issue_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(reissuebook.this,0);
                integrator.initiateScan();
            }
        });

        issue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                final EditText book_id = (EditText) findViewById(R.id.re_issue1);
                final EditText reg_no = (EditText) findViewById(R.id.re_issue2);

                final EditText return_date = (EditText) findViewById(R.id.re_issue4);

                String a, b, c, d;

                a = book_id.getText().toString();
                b = reg_no.getText().toString();

                d = return_date.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    da = dateFormat.parse(d);


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if((da!=null)) {
                pDialog = new ProgressDialog(reissuebook.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client = RetroClient.getApiService();
                Call<reissuebookResponse> Response = client.getLogin8(new reissuebookRequest(b, a, d));
                Response.enqueue(new Callback<reissuebookResponse>() {

                    @Override
                    public void onResponse(Call<reissuebookResponse> call, retrofit2.Response<reissuebookResponse> response) {

                        if (response.body().getSuccess().equals("1")) {
                            pDialog.dismiss();
                            success_alert("SUCCESSFULL", "Book re-issued successfully");
                            clear_text();
                        } else if (response.body().getSuccess().equals("0")) {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL", "Book not re-issued ");
                            clear_text();
                        } else if (response.body().getSuccess().equals("3")) {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL", "Enter all details");
                            clear_text();
                        } else if (response.body().getSuccess().equals("4")) {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL", "Book not re-issued. Wrong book id");
                            clear_text();
                        } else if (response.body().getSuccess().equals("5")) {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL", "Book not re-issued. Invalid registration number.");
                            clear_text();
                        } else {
                            pDialog.dismiss();
                            fail_alert("UNSUCCESSFULL", "Unknown error");
                            clear_text();
                        }


                    }

                    @Override
                    public void onFailure(Call<reissuebookResponse> call, Throwable t) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(reissuebook.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(reissuebook.this);
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
        final EditText book_id = (EditText) findViewById(R.id.re_issue1);
        final EditText reg_no = (EditText) findViewById(R.id.re_issue2);

        final EditText return_date = (EditText) findViewById(R.id.re_issue4);

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
