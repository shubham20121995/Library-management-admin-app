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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import parsing.ApiService;
import parsing.Book_details;
import parsing.Books;
import parsing.RetroClient;
import parsing.books_issued_request;
import parsing.books_issued_response;
import parsing.searchbookRequest;
import parsing.searchbookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shubham on 12/27/2016.
 */

public class searchuser extends AppCompatActivity {
    ProgressDialog pDialog;
    ArrayList<Books> issued_books = new ArrayList<Books>();
    com.example.shu.dbms_project.NestedListView list;
    Adapter4 ad;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.e("request code",String.valueOf(requestCode));
        Log.e("result code",String.valueOf(resultCode));
        Log.e("intent",String.valueOf(intent));
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);


        if (scanResult.getContents() != null) {

                DESKeySpec keySpec = null;
                try {
                    EditText b_id = (EditText) findViewById(R.id.b_id);
                    keySpec = new DESKeySpec("yesterday".getBytes("UTF8"));
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = keyFactory.generateSecret(keySpec);

                    byte[] encrypedPwdBytes = Base64.decode(scanResult.getContents(), Base64.DEFAULT);

                    Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
                    String s = new String(plainTextPwdBytes, "UTF-8");
                    b_id.setText(s);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);

        list=(com.example.shu.dbms_project.NestedListView)findViewById(R.id.listview5);

        Button search_button = (Button)findViewById(R.id.search_button);

        TextView search1=(TextView)findViewById(R.id.search1);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(searchuser.this,0);
                integrator.initiateScan();
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                EditText b_id=(EditText)findViewById(R.id.b_id) ;
                String r=b_id.getText().toString();
                pDialog = new ProgressDialog(searchuser.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiService client= RetroClient.getApiService();
                Call<books_issued_response> booksIssuedResponse=client.getLogin9(new books_issued_request(r));
                booksIssuedResponse.enqueue(new Callback<books_issued_response>() {

                    @Override
                    public void onResponse(Call<books_issued_response> call, Response<books_issued_response> response) {

                        if(response.body().getSuccess().equals("true"))
                        {


                            issued_books.clear();
                            issued_books=response.body().getBooks();
                            ad=new Adapter4(searchuser.this,issued_books);
                            list.setAdapter(ad);

                            pDialog.dismiss();

                        }
                        else if(response.body().getSuccess().equals("3"))
                        {
                            issued_books.clear();

                            pDialog.dismiss();
                            Toast.makeText(searchuser.this,"Enter registration number",Toast.LENGTH_LONG).show();
                        }
                        else
                        {   issued_books.clear();

                             pDialog.dismiss();
                            Toast.makeText(searchuser.this,"No books issued",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<books_issued_response> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(searchuser.this,"Server down",Toast.LENGTH_LONG).show();
                        Log.e("Error:",t.toString());
                    }
                });




            }
        });

    }
    public void success_alert(String title, String body)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(searchuser.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(searchuser.this);
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

