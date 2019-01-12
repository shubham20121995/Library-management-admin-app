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

/**
 * Created by shubham on 12/12/2016.
 */

public class Adapter2 extends BaseAdapter {

    Context context;
    ArrayList all_books;
    Adapter2(Context context, ArrayList<All_books> all_books)
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
        View v=inflate.inflate(R.layout.adapter_2,null);
        h.book_id2=(TextView)v.findViewById(R.id.book_id2);
        h.book_name2=(TextView)v.findViewById(R.id.book_name2);
        h.author=(TextView)v.findViewById(R.id.author);
        h.publication=(TextView)v.findViewById(R.id.publication);
        h.edition=(TextView)v.findViewById(R.id.edition);
        h.no_of_copies=(TextView)v.findViewById(R.id.no_of_copies);

        All_books s=new All_books();
        s= (All_books) all_books.get(position);
        String s1 = "<b>" + "BOOK ID :" + "</b> " + (String) s.getBook_id();
        h.book_id2.setText(Html.fromHtml(s1));
        String s2 = "<b>" + "BOOK NAME : " + "</b> " + (String) s.getBook_name();
        h.book_name2.setText(Html.fromHtml(s2));
        String s3 = "<b>" + "AUTHOR : " + "</b> " + (String) s.getAuthor();
        h.author.setText(Html.fromHtml(s3));
        String s4 = "<b>" + "PUBLICATION : " + "</b> " + (String) s.getPublication();
        h.publication.setText(Html.fromHtml(s4));
        String s5 = "<b>" + "EDITION : " + "</b> " + (String) s.getEdition();
        h.edition.setText(Html.fromHtml(s5));
        String s6 = "<b>" + "NO OF COPIES : " + "</b> " + (String) s.getNo_of_copies();
        h.no_of_copies.setText(Html.fromHtml(s6));

        return v;
    }

    class holderid{

        TextView book_id2,book_name2,author,publication,edition,no_of_copies;

    }
}
