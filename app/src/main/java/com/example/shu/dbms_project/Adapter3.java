package com.example.shu.dbms_project;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import parsing.Book_details;

/**
 * Created by shubham on 12/22/2016.
 */

public class Adapter3 extends BaseAdapter {

    Context context;
    ArrayList details;
    Adapter3(Context context,ArrayList<Book_details> details)
    {

        this.context=context;
        this.details=details;

    }





    @Override
    public int getCount() {
        return details.size();
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
        View v=inflate.inflate(R.layout.adapter3,null);
        h.reg_no_issued=(TextView)v.findViewById(R.id.reg_no_issued);
        h.return_date_issued=(TextView)v.findViewById(R.id.return_date_issued);


        Book_details s=new Book_details();
        s= (Book_details) details.get(position);
        String s1 = "<b>" + "REGISTRATION NO :" + "</b> " + (String) s.getRegistration_no();
        Log.e("reg_no",s1);
        h.reg_no_issued.setText(Html.fromHtml(s1));
        String s2 = "<b>" + "RETURN DATE : " + "</b> " + (String) s.getReturn_date();
        h.return_date_issued.setText(Html.fromHtml(s2));

        return v;
    }

    class holderid{

        TextView reg_no_issued,return_date_issued;

    }
}
