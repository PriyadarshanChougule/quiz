package com.example.quiz;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class categoryActivity extends AppCompatActivity {

    private GridView catGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid = findViewById(R.id.catGridview);

        List<String> catList= new ArrayList<>();
        catList.add("cat 1");
        catList.add("cat 2");
        catList.add("cat 3");
        catList.add("cat 4");
        catList.add("cat 5");
        catList.add("cat 6");

        CategoryGridAdapter adapter= new CategoryGridAdapter(catList);
        catGrid.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            categoryActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}