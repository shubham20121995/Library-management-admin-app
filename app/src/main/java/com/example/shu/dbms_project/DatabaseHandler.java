package com.example.shu.dbms_project;

/**
 * Created by Shu on 24/09/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database";



    Context l;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        l = context;
        //3rd argument to be passed is CursorFactory instance
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + "book_table" + "( "
                + "book_id" + " INTEGER PRIMARY KEY," + "book_name" + " TEXT, "
                + "author" + " TEXT," + "publication" + " TEXT," + "quantity" + " INTEGER" + ")";

        String CREATE_STUDENT_TABLE = "CREATE TABLE " + "student_table" + "( "
                + "s_id" + " INTEGER," + "book_id" + " INTEGER, "
                + "return_date" + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "book_table");
        db.execSQL("DROP TABLE IF EXISTS " + "student_table");
        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addBook(String a, String b, String c, String d, int e) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("book_id", a);
            values.put("book_name", b);
            values.put("author", c);
            values.put("publication", d);
            values.put("quantity", e);


            // Inserting Row
            db.insert("book_table", null, values);
            Toast.makeText(l, "BOOK ADDED", Toast.LENGTH_SHORT).show();
            //2nd argument is String containing nullColumnHack
        }catch(Exception e1){
            Toast.makeText(l, "ENTER VALID DETAILS", Toast.LENGTH_SHORT).show();
        }
    }

    void addStudent(int a, int b, String c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("s_id", a);
        values.put("book_id", b);
        values.put("return_date", c);



        // Inserting Row
        db.insert("student_table", null, values);

        //2nd argument is String containing nullColumnHack

    }

    void set_quantity(int quantity,int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("quantity", quantity);
        db.update("book_table", cv, "book_id=" + id, null);
        db.close();


    }

    Cursor viewBook(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String qwery = "SELECT * FROM book_table WHERE book_id='" + id + "' ";
        Cursor cursor = db.rawQuery(qwery, null);
        return cursor;
    }

    Cursor viewallBooks()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String qwery = "SELECT * FROM book_table";
        Cursor cursor = db.rawQuery(qwery, null);
        return cursor;
    }
    void issue_book(int s1,int s2,String ret_date){
        int quantity=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String qwery = "SELECT * FROM book_table WHERE book_id='" + s1 + "' ";
        Cursor cursor = db.rawQuery(qwery, null);
        int count = cursor.getCount();

        if(count > 0)
        {
            if(cursor.moveToFirst())
            {
                do {
                    quantity=cursor.getInt(cursor.getColumnIndex("quantity"));

                }while(cursor.moveToNext());
            }
        }
        if (quantity>0) {
            quantity = quantity - 1;
            set_quantity(quantity,s1);
            addStudent(s2,s1,ret_date);
            Toast.makeText(l, "BOOK ISSUED", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(l, "BOOK NOT AVAILABLE", Toast.LENGTH_SHORT).show();

        }


    }


    void delete_book(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("book_table", "book_id" + " = ?",
                new String[]{s});
        db.delete("student_table", "book_id" + " = ?",
                new String[]{s});
        db.close();
        Toast.makeText(l, "BOOK DELETED", Toast.LENGTH_SHORT).show();


    }

    void return_book(int s1,int s2){
        int quantity=0;
        int a=0;
        SQLiteDatabase db = this.getWritableDatabase();
        String s3= "Select * FROM student_table WHERE s_id= " +s1+ " AND book_id= " +s2;
        Cursor c=db.rawQuery(s3,null);
        a=c.getCount();
        db.delete("student_table", "s_id=" + s1 + " AND book_id=" + s2, null);
        String qwery = "SELECT * FROM book_table WHERE book_id='" + s2 + "' ";
        Cursor cursor = db.rawQuery(qwery, null);
        int count = cursor.getCount();

        if(count > 0)
        {
            if(cursor.moveToFirst())
            {
                do {
                    quantity=cursor.getInt(cursor.getColumnIndex("quantity"));

                }while(cursor.moveToNext());
            }
        }
        quantity=quantity+a;
        set_quantity(quantity, s2);



    }
    Cursor viewAllStudents()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String qwery = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(qwery, null);
        return cursor;
    }
}