package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class FavCatActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchObserver, AdapterView.OnItemClickListener {

    private SearchView searchView;
    private ListView listView;
    private FavCatAdapter adapter;
    private ArrayList<Cat> cats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchViewMain);
        searchView.setOnQueryTextListener(this);

        listView= findViewById(R.id.listViewMain);
        cats= new ArrayList<Cat>();

        //adapter= new DeputyAdapter(deputies, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        cats= new ArrayList<Cat>();
        ApiServices.searchRequest(this, query, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onReceiveFavCatInfo(Cat cat) {
        if(!cats.contains(cat)){
            cats.add(cat);
            //adapter.setDeputies(deputies);
            //adapter.notifyDataSetChanged();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent(MainActivity.this, FavCatActivity.class);
        setContentView(R.layout.activity_favcat);
        intent.putExtra("cat", cats.get(position));
        startActivity(intent);
    }
}
