package com.example.danielscal.starwarsmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by DanielScal on 2/17/18.
 */

public class MovieDetailActivity extends AppCompatActivity {
    private Context mContext;
    private TextView title1;
    private TextView description;
    private ImageView poster;
    private View view;
    private RadioButton yes;
    private RadioButton no;
    private RadioButton like;
    private Button submitButton;
    private Integer position;
    private String checkedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //get recipe data from main activiy
        mContext = this;

        description = findViewById(R.id.description_detail);
        poster = findViewById(R.id.poster);
        title1 = findViewById(R.id.title_detail);
        submitButton = findViewById(R.id.submit_button);
        yes = findViewById(R.id.seen_yes);
        no = findViewById(R.id.seen_no);
        like = findViewById(R.id.like);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //construct intent
                Intent radioIntent = new Intent();
                //put two boolean values into the intent
                radioIntent.putExtra("checkedRadioButton", checkedButton);
                radioIntent.putExtra("position", position);

                //send back to main activity
                setResult(Activity.RESULT_OK, radioIntent);
                finish();
            }
        });

        String title = this.getIntent().getExtras().getString("title");

        title1.setText(this.getIntent().getExtras().getString("title"));
        description.setText(this.getIntent().getExtras().getString("description"));
        position = this.getIntent().getExtras().getInt("position");
        Picasso.with(mContext).load(this.getIntent().getExtras().getString("thumbnail")).into(poster);
        setTitle(title);


    }

    public void onRadioButtonClicked(View view) {
        //is the button now checked
        boolean checked = ((RadioButton) view).isChecked();
        //check which radio button was clicked
        switch (view.getId()) {
            case R.id.seen_yes:
                if (checked)
                    checkedButton = yes.getText().toString();
                    System.out.println("hello" + checkedButton);
                    break;
            case R.id.seen_no:
                if (checked)
                    checkedButton = no.getText().toString();
                    break;
            case R.id.like:
                if (checked)
                    checkedButton = like.getText().toString();
                    break;
        }

    }
}
