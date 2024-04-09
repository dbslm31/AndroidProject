package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.android.volley.Response;

import java.util.ArrayList;

public class CatActivity extends AppCompatActivity

{

    private ImageView imageView;

    private ListView listView;
    private CatAdapter adapter;
    private ArrayList<Cat> cats;



    private TextView textViewName, textViewCirco, textViewEmail, textViewGroupe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cat cat= (Cat) getIntent().getSerializableExtra("cat");
        String catName = cat.getName();
        ApiServices.getRandomCatImage(this, (Response.ErrorListener) this);
        //setContentView(R.layout.activity_deputy);
        //textViewName= findViewById(R.id.textViewDeputyName);





        adapter= new CatAdapter(cats, this);
        listView.setAdapter(adapter);
        Log.d("lol","test");




    }

    @Override
    protected void onResume() {
        super.onResume();
        Cat cat= (Cat) getIntent().getSerializableExtra("cat");
       // textViewName.setText(cat.getName());

       // ApiServices.loadCatAvatar(this, cat.getNameForAvatar(), imageView);
    }
}

