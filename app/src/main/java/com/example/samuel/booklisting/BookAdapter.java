package com.example.samuel.booklisting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Samuel on 30/05/2017.
 */

public class BookAdapter extends ArrayAdapter<Books> {

//private TextView title_text_view,author_text_view,publisher_text_view,description_text_view;
    public BookAdapter(@NonNull Context context,  ArrayList<Books> books) {
        super(context,R.layout.booklayout ,books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView == null){
            holder = new viewHolder();
            convertView = inflater.inflate(R.layout.booklayout,parent,false);
           holder.title_text_view = (TextView)convertView.findViewById(R.id.Title_text_view);
            holder. publisher_text_view = (TextView)convertView.findViewById(R.id.publisher_text_view);
            holder.author_text_view = (TextView)convertView.findViewById(R.id.author_text_view);
            holder.description_text_view = (TextView) convertView.findViewById(R.id.description_text_view);
            convertView.setTag(holder);
        }
        else{holder = (viewHolder) convertView.getTag();
        }
        Books book = getItem(position);

        holder.title_text_view.setText(book.getTitle());
        holder.publisher_text_view.setText(book.getPublisher());
        holder.description_text_view.setText(book.getDescription());
        holder.author_text_view.setText(book.getAuthors());



        return convertView;
    }
    class viewHolder{
        private TextView title_text_view,author_text_view,publisher_text_view,description_text_view;


    }
}
