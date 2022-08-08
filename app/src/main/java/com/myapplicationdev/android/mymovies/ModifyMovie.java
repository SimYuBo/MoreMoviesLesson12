package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifyMovie extends AppCompatActivity {

    EditText etID, etTitle, etGenre, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    Movie data;
    Spinner spnRating;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        etID = findViewById(R.id.etId);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spnRating = findViewById(R.id.spnRating2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        etID.setFocusable(false);
        etID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));

        spnRating
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyMovie.this);
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = 0;
                if (etYear.getText().toString().isEmpty()) {
                    Toast.makeText(ModifyMovie.this, "Year is empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    year = Integer.parseInt(etYear.getText().toString());
                }
                if (title.isEmpty() || genre.isEmpty() || year > 2022 || year < 1960){
                    Toast.makeText(ModifyMovie.this, "Update unsuccessful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    data.setTitle(title);
                    data.setGenre(genre);
                    data.setYear(year);
                    data.setRating(rating);
                    dbh.updateMovie(data);
                    dbh.close();
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Confirm Delete?");
                myBuilder.setMessage("Are you sure you want to delete the movie " + data.getTitle());

                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        DBHelper dbh = new DBHelper(ModifyMovie.this);
                        dbh.deleteMovie(data.getId());
                        finish();
                    }
                });

                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Discard Changes?");
                myBuilder.setMessage("Are you sure you want to discard changes?");

                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        finish();
                    }
                });

                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}