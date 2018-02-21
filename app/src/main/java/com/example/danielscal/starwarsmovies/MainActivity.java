package com.example.danielscal.starwarsmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    String radioButtonResult;
    private ArrayList<Movies> movieList;
    MovieAdapter adapter;
    static final int HAS_SEEN_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        movieList = Movies.getMoviesFromFile("movies.json", this);

        adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        //1. each row should be clickable
        //when the row is clicked, the intent is created and sent

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies selectedMovie = movieList.get(position);

                //create intent package and add all the information needed for detail page
                //call startActivity with that intent

                //explicit
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

                //put title and instruction URL
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("thumbnail", selectedMovie.poster);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("position", position);

                //startActivity(detailIntent);
                startActivityForResult(detailIntent, HAS_SEEN_REQUEST);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HAS_SEEN_REQUEST) {
            if (resultCode == RESULT_OK) { //the second activity is sending data
                radioButtonResult = data.getStringExtra("checkedRadioButton");
                Integer pos = data.getIntExtra("position", 0);
                Movies X = movieList.get(pos);
                X.hasSeenUpdated = true;
                X.hasSeenString = radioButtonResult;
                movieList.set(pos, X);
                adapter.notifyDataSetChanged();
            }
            if(resultCode == RESULT_CANCELED){
                radioButtonResult = "Something went wrong";
            }
        }
    }
}


