package com.example.shu.dbms_project;

/**
 * Created by shubham on 12/27/2016.
 */

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import parsing.Books;



import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import parsing.Books;
import parsing.Books2;

/**
 * Created by shubham on 12/27/2016.
 */

public class Adapter5 extends BaseAdapter {

    Context context;
    ArrayList issued_books;
    Adapter5(Context context,ArrayList<Books2> issued_books)
    {

        this.context=context;
        this.issued_books=issued_books;

    }





    @Override
    public int getCount() {
        return issued_books.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holderid h=new holderid();

        LayoutInflater inflate=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflate.inflate(R.layout.adapter5,null);
        h.book_id=(TextView)v.findViewById(R.id.book_id);
        h.book_name=(TextView)v.findViewById(R.id.book_name);
        h.reg_no=(TextView)v.findViewById(R.id.reg_no);
        h.date_of_issue=(TextView)v.findViewById(R.id.date_of_issue);
        h.return_date=(TextView)v.findViewById(R.id.return_date);

        Books2 s=new Books2();
        s= (Books2) issued_books.get(position);
        String s1 = "<b>" + "BOOK ID :" + "</b> " + (String) s.getBook_id();
        h.book_id.setText(Html.fromHtml(s1));
        String s2 = "<b>" + "BOOK NAME : " + "</b> " + (String) s.getBook_name();
        h.book_name.setText(Html.fromHtml(s2));
        String s5 = "<b>" + "REGISTRATION NUMBER : " + "</b> " + (String) s.getRegistration_no();
        h.reg_no.setText(Html.fromHtml(s5));
        String s3 = "<b>" + "DATE OF ISSUE : " + "</b> " + (String) s.getDate_of_issue();
        h.date_of_issue.setText(Html.fromHtml(s3));
        String s4 = "<b>" + "RETURN DATE : " + "</b> " + (String) s.getReturn_date();
        h.return_date.setText(Html.fromHtml(s4));


        return v;
    }

    class holderid{

        TextView book_id,book_name,reg_no,date_of_issue,return_date;

    }
}
