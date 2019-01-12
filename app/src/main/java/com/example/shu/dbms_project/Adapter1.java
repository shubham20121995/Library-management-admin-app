package com.example.shu.dbms_project;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shu on 24/09/2016.
 */

public class Adapter1 extends BaseAdapter {

    Context context;

    ArrayList<String> StudentId=new ArrayList<String>();
    ArrayList<String> BookId = new ArrayList<String>();
    ArrayList<String> ReturnDate = new ArrayList<String>();

    Adapter1(Context context, ArrayList<String> StudentId, ArrayList<String> BookId, ArrayList<String> ReturnDate){

        this.context=context;
        this.StudentId=StudentId;
        this.BookId=BookId;
        this.ReturnDate=ReturnDate;

    }
    @Override
    public int getCount() {
        return ReturnDate.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holderid h=new holderid();

        LayoutInflater inflate=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflate.inflate(R.layout.alldetails2,null);
        h.StudentID=(TextView)v.findViewById(R.id.s_id);
        h.BookID=(TextView)v.findViewById(R.id.b_id);
        h.ReturnDate=(TextView)v.findViewById(R.id.ret_date);

        String s3 = "<b>" + "RETURN DATE : " + "</b> " +(String)ReturnDate.get(position);
        h.ReturnDate.setText(Html.fromHtml(s3));
        String s1 = "<b>" + "STUDENT ID : " + "</b> " + (String) StudentId.get(position);
        h.StudentID.setText(Html.fromHtml(s1));
        String s2 = "<b>" + "BOOK ID : " + "</b> " + (String) BookId.get(position);
        h.BookID.setText(Html.fromHtml(s2));


        return v;
    }
    class holderid{

        TextView StudentID,BookID,ReturnDate;

    }
}
