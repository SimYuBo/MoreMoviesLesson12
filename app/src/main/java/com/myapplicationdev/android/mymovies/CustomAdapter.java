package com.myapplicationdev.android.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView ivRating = rowView.findViewById(R.id.imageView);

        Movie currentMovie = movieList.get(position);
        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText(currentMovie.getYear());

        if (currentMovie.getRating().equals("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (currentMovie.getRating().equals("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if (currentMovie.getRating().equals("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if (currentMovie.getRating().equals("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (currentMovie.getRating().equals("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if (currentMovie.getRating().equals("R21")) {
            ivRating.setImageResource(R.drawable.rating_r21);
        }
        return rowView;
    }

}
