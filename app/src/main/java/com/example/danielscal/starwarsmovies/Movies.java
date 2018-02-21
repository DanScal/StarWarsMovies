package com.example.danielscal.starwarsmovies;

import android.content.Context;
import android.graphics.Movie;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by DanielScal on 2/17/18.
 */

public class Movies {
    public String title;
    public int episodeNumber;
    public String description;
    public ArrayList<String> mainCharacters;
    public String url;
    public String poster;
    public boolean hasSeenUpdated = false;
    public String hasSeenString = "Has Seen?";

    public static ArrayList<Movies> getMoviesFromFile(String filename, Context context) {
        ArrayList<Movies> movieList = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset ("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            //for loop to go through each movie in your movie array
            for (int i = 0; i < movies.length(); i++){
                Movies movie = new Movies();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episodeNumber = movies.getJSONObject(i).getInt("episode_number");
                movie.description = movies.getJSONObject(i).getString("description");

                JSONArray mainCharacters = movies.getJSONObject(i).getJSONArray("main_characters");
                ArrayList<String> characters = new ArrayList<>();
                if (mainCharacters != null){
                    int len = mainCharacters.length();
                    for (int l=0; l<len; l++){
                        characters.add(mainCharacters.get(l).toString());
                    }
                }

                movie.mainCharacters = characters;
                movie.url = movies.getJSONObject(i).getString("url");
                movie.poster = movies.getJSONObject(i).getString("poster");

                //add to arraylist
                movieList.add(movie);

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
