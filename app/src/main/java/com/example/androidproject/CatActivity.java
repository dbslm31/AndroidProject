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



    private Cat cat;



    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        textView = findViewById(R.id.textViewCatFact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cat cat= (Cat) getIntent().getSerializableExtra("cat");
        textView = findViewById(R.id.textViewCatFact);
        textView.setText("Name: "+ cat.getName()+"\n"+
                        "Origin: " + cat.getOrigin()+"\n"+
                        "Adaptability: " + cat.getAdaptability()+"\n"+
                        "Affection level: " + cat.getAffection_level()+"\n"+
                        "Child friendly: " + cat.getChildFriendly()+"\n"+
                        "Intelligence: " + cat.getIntelligence()+"\n"+
                        "Social needs: " + cat.getSocialNeeds()

                );
        imageView = findViewById(R.id.imageViewCat);
        ApiServices.loadCatAvatar(this, cat, imageView);
    }
}

