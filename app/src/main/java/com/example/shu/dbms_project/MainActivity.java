package com.example.shu.dbms_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> StudentId;
    List<String> BookId;
    List<String> ReturnDate;
    int year,month,day,hour;

    DatabaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR_OF_DAY);
        String s3=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        Log.e("Date1", s3);
        Log.e("Hour1", String.valueOf(hour));
        c.add(Calendar.DATE, 1);
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR);
        String s4=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        Log.e("Date2", s4);
        Log.e("Hour2", String.valueOf(hour));
        c.setTime(new Date());
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR);
        String s5=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        Log.e("Date1", s5);
        Log.e("Hour1", String.valueOf(hour));
         dh = new DatabaseHandler(getApplicationContext());

        ImageButton b1=(ImageButton)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addbook.class);
                startActivity(intent);
            }
        });


        ImageButton b2=(ImageButton)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,deletebook.class);
                startActivity(intent);
            }
        });

        ImageButton b3=(ImageButton)findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,issuebook.class);
                startActivity(intent);
            }
        });

        ImageButton b4=(ImageButton)findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,returnbook.class);
                startActivity(intent);
            }
        });

        ImageButton b5=(ImageButton)findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,searchbook.class);
                startActivity(intent);
            }
        });

        ImageButton b6=(ImageButton)findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                    Intent intent = new Intent(MainActivity.this, BookDetails.class);

                    startActivity(intent);

            }
        });

        ImageButton b9=(ImageButton)findViewById(R.id.b9);
        b9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this, searchuser.class);

                startActivity(intent);

            }
        });


        ImageButton b12=(ImageButton)findViewById(R.id.b12);
        b12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this, reissuebook.class);

                startActivity(intent);

            }
        });
        ImageButton b7=(ImageButton)findViewById(R.id.b7);
        b7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, All_issued.class);

                startActivity(intent);
            }
        });


        ImageButton b8=(ImageButton)findViewById(R.id.b8);
        b8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //Uncomment the below code to Set the message and title from the strings.xml file
                //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("DO YOU WANT TO EXIT")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                               finish();
                            }

                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = builder.create();

                // show it
                alertDialog.show();
            }
        });
        ImageButton b10=(ImageButton)findViewById(R.id.b10);
        b10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,updatebook.class);
                startActivity(intent);
            }
        });

        ImageButton b11=(ImageButton)findViewById(R.id.b11);
        b11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ResDetails.class);
                startActivity(intent);
            }
        });

    }
}
