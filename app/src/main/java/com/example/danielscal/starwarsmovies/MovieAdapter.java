package com.example.danielscal.starwarsmovies;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DanielScal on 2/17/18.
 */

public class MovieAdapter extends BaseAdapter {

    //adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Movies> mMovieList;
    private LayoutInflater mInflater;

    //constructor
    public MovieAdapter(Context mContext, ArrayList<Movies> mMovieList) {
        //initialize instance variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //list of methods we need to override

    //gives you the number of recipes in the data source
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    //returns the item at a specific position in the data source
    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    //returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //check if the view already exists
        //if yes you don't need to inflate and findViewbyID again
        if (convertView == null) {
            //inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);

            //add the views to the holder
            holder = new ViewHolder();
            //views
            holder.titleTextView = convertView.findViewById(R.id.title);
            holder.descriptionTextView = convertView.findViewById(R.id.description_short);
            holder.characterTextView = convertView.findViewById(R.id.characters);
            holder.hasSeenTextView = convertView.findViewById(R.id.has_seen);
            holder.thumbnailImageView = convertView.findViewById(R.id.thumbnail);

            //add the holder to the view
            //for future use
            convertView.setTag(holder);
        } else {
            //get the view holder from convertview
            holder = (ViewHolder) convertView.getTag();
        }

        //get relevant subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView characterTextView = holder.characterTextView;
        TextView hasSeenTextView = holder.hasSeenTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        //get corresponding recipe for each row
        Movies movie = (Movies) getItem(position);

        //title
        titleTextView.setText(movie.title);

        //description
        descriptionTextView.setText(movie.description);

        //character
        String char1 = movie.mainCharacters.get(0);
        String char2 = movie.mainCharacters.get(1);
        String char3 = movie.mainCharacters.get(2);
        String characters = char1 + ", " + char2 + ", " + char3;
        characterTextView.setText(characters);

        //has seen
        if(movie.hasSeenUpdated)
            hasSeenTextView.setText(movie.hasSeenString);

        //thumbnail
        Picasso.with(mContext).load(movie.poster).into(thumbnailImageView);
        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView characterTextView;
        public TextView hasSeenTextView;
        public ImageView thumbnailImageView;
    }
}