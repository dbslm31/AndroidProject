package com.example.androidproject;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.widget.Button;

import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CatObserver {


    Button catButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        catButton = findViewById(R.id.randomCatBut);
        catButton.setOnClickListener(this);
    }



    @Override
    public void onReceiveCatInfo(Cat cat) {
        Intent i = new Intent(getApplicationContext(),CatActivity.class);
        i.putExtra("cat", cat);
        startActivity(i);
    }





    @Override
    public void onClick(View v) {
        ApiServices.getRandomCatImage(this, this);
    }
}