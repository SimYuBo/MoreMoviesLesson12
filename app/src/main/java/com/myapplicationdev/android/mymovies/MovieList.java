package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    Button btnFilter, btnBack;
    ArrayList<Movie> alMovie;
    ListView lvMovie;
    CustomAdapter caSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        btnFilter = findViewById(R.id.btnFilter);
        btnBack = findViewById(R.id.btnBack);
        lvMovie= findViewById(R.id.lvMovie);
        alMovie = new ArrayList<>();
        caSong = new CustomAdapter(this, R.layout.row, alMovie);
        lvMovie.setAdapter(caSong);

        DBHelper dbh = new DBHelper(MovieList.this);
        alMovie.clear();
        alMovie.addAll(dbh.getAllMovies());
        caSong.notifyDataSetChanged();

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MovieList.this);
                alMovie.clear();
                String filter = "PG";
                alMovie.addAll(dbh.getFilterMovies(filter));

                caSong.notifyDataSetChanged();
            }
        });

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = alMovie.get(position);
                Intent i = new Intent(MovieList.this,
                        ModifyMovie.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(MovieList.this);
        alMovie.clear();
        alMovie.addAll(dbh.getAllMovies());
        caSong.notifyDataSetChanged();
    }
}