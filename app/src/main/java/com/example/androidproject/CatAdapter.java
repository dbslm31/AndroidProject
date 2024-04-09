package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {

    private ArrayList<Cat> cats;
    private Context context;

    public CatAdapter(ArrayList<Cat> cats, Context context) {
        this.cats = cats;
        this.context = context;
    }

    public void setDeputies(ArrayList<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public int getCount() {
        return cats.size();
    }

    @Override
    public Object getItem(int position) {
        return cats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cats.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView==null){
      //      convertView = LayoutInflater.from(context).inflate(R.layout.item_deputy, parent, false);
       // }
        //TextView textViewName= convertView.findViewById(R.id.textViewItemDeputyName);
       // textViewName.setText(deputies.get(position).getFirstname()+
         //       " "+deputies.get(position).getLastname());

        //ImageView imageView= convertView.findViewById(R.id.imageViewItemDeputy);
        //ApiServices.loadCatAvatar(context, cats.get(position).getNameForAvatar(), imageView);
        return convertView;
    }
}

