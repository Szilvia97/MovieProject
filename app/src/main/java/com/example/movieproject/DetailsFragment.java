package com.example.movieproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieproject.Model.Movie;
import com.google.gson.Gson;


public class DetailsFragment extends Fragment {

    ImageButton btn_x, btn_star;
    ImageView iv_posterDet;
    TextView tv_rating, tv_detTitle, tv_detDesc;
    MovieSqliteHelper movieSqliteHelper;
    Movie movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        btn_x = view.findViewById(R.id.btn_x);
        btn_star = view.findViewById(R.id.btn_star);
        iv_posterDet = view.findViewById(R.id.iv_posterDet);
        tv_rating = view.findViewById(R.id.tv_rating);
        tv_detTitle = view.findViewById(R.id.tv_detTitle);
        tv_detDesc = view.findViewById(R.id.tv_detDesc);
        tv_detDesc.setMovementMethod(new ScrollingMovementMethod());


        movieSqliteHelper = new MovieSqliteHelper(getActivity().getApplicationContext());

        String movieJson = getArguments().getString("movie");
        Gson gson = new Gson();
        movie = gson.fromJson(movieJson, Movie.class);

        tv_rating.setText("Rating: " + movie.getRating());
        tv_detTitle.setText(movie.getTitle());
        tv_detDesc.setText(movie.getDescription());

        btn_x.setOnClickListener(OnClickListenerBack);
        btn_star.setOnClickListener(OnClickListenerStar);

        Glide.with(getActivity()).
                load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).
                placeholder(R.drawable.ic_launcher_foreground).
                into(iv_posterDet);

        return view;
    }

    private View.OnClickListener OnClickListenerBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            getFragmentManager().popBackStack();

        }
    };

    private View.OnClickListener OnClickListenerStar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         Toast.makeText(getContext(), "Added to favorites.", Toast.LENGTH_LONG).show();

        movieSqliteHelper.addMovie(movie);

        }
    };
}
