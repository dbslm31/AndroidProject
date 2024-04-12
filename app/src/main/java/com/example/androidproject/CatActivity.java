package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.android.volley.Response;

import java.util.ArrayList;

public class CatActivity extends AppCompatActivity implements View.OnClickListener, CatObserver {

    private ImageView imageView;
    Button catButton, favButton;
    ImageView heartButton;



    private Cat cat;



    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        textView = findViewById(R.id.textViewCatFact);

        catButton = findViewById(R.id.randomCatBut);
        catButton.setOnClickListener(this);
        favButton = findViewById(R.id.favcat);
        favButton.setOnClickListener(this);
        heartButton = findViewById(R.id.heart);
        heartButton.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if(v==catButton){
            /*finish();
            ApiServices.getRandomCatImage(this, (CatObserver) this);
            Intent i = new Intent(getApplicationContext(),CatActivity.class);
            startActivity(i);*/
            ApiServices.getRandomCatImage(this, this);
        }

         else if(v==favButton){
            Intent i = new Intent(getApplicationContext(),FavCatActivity.class);
            startActivity(i);
        }
         else if(v==heartButton){

        }
    }


    @Override
    public void onReceiveCatInfo(Cat cat) {
        textView.setText("Name: "+ cat.getName()+"\n"+
                "Origin: " + cat.getOrigin()+"\n"+
                "Adaptability: " + cat.getAdaptability()+"\n"+
                "Affection level: " + cat.getAffection_level()+"\n"+
                "Child friendly: " + cat.getChildFriendly()+"\n"+
                "Intelligence: " + cat.getIntelligence()+"\n"+
                "Social needs: " + cat.getSocialNeeds()

        );
        ApiServices.loadCatAvatar(this, cat, imageView);
    }
}

