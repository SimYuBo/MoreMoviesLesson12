package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnShowList;
    Spinner spnRating;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        spnRating = findViewById(R.id.spnRating);

        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        rating = "G";
                        break;

                    case 1:
                        rating = "PG";
                        break;

                    case 2:
                        rating = "PG13";
                        break;

                    case 3:
                        rating = "NC16";
                        break;

                    case 4:
                        rating = "M18";
                        break;

                    case 5:
                        rating = "R21";
                        break;

                    default:
                        rating = "";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = 0;
                if (etYear.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Year is empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    year = Integer.parseInt(etYear.getText().toString());
                }

                if (title.isEmpty() || genre.isEmpty() || year > 2022 || year < 1960){
                    Toast.makeText(MainActivity.this, "Insert unsuccessful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertMovie(title, genre, year, rating);

                    if (inserted_id != -1) {
                        Toast.makeText(MainActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MovieList.class);
                startActivity(i);
            }
        });
    }
}