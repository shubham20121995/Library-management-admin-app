package com.example.shu.dbms_project;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import parsing.All_books;
import parsing.*;

/**
 * Created by shubham on 12/12/2016.
 */

public class Adapter6 extends BaseAdapter {

    Context context;
    ArrayList all_books;
    Adapter6(Context context, ArrayList<res_books> all_books)
    {

        this.context=context;
        this.all_books=all_books;

    }





    @Override
    public int getCount() {
        return all_books.size();
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
        View v=inflate.inflate(R.layout.adapter6,null);
        h.res_bookId=(TextView)v.findViewById(R.id.res_bookId);
        h.res_name=(TextView)v.findViewById(R.id.res_name);
        h.res_date=(TextView)v.findViewById(R.id.res_date);
        h.res_regno=(TextView)v.findViewById(R.id.res_regno);


        res_books s=new res_books();
        s= (res_books) all_books.get(position);
        String s1 = "<b>" + "BOOK ID :" + "</b> " + (String) s.getBook_id();
        h.res_bookId.setText(Html.fromHtml(s1));
        String s2 = "<b>" + "BOOK NAME : " + "</b> " + (String) s.getBook_name();
        h.res_name.setText(Html.fromHtml(s2));
        String s3 = "<b>" + "REG NO : " + "</b> " + (String) s.getRegistration_no();
        h.res_regno.setText(Html.fromHtml(s3));
        String s4 = "<b>" + "RESERVED DATE : " + "</b> " + (String) s.getReserved_date();
        h.res_date.setText(Html.fromHtml(s4));

        return v;
    }

    class holderid{

        TextView res_bookId,res_name,res_regno,res_date;

    }
}
