package com.example.root.submission2uiux.feature.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.submission2uiux.R;
import com.example.root.submission2uiux.helper.Config;

public class DetailActivity extends AppCompatActivity {

    private String image, tittle, overview, release, voteCount, voteAverage, popularity, language, backdrophImage;
    private ImageView ivDetailBackdrophImage;
    private ImageView ivDetailMovie;
    private TextView tvDetailReleaseDate;
    private TextView tvDetailVoteAverage;
    private TextView tvDetailVoteCount;
    private TextView tvDetailOriginalLanguage;
    private TextView tvDetailOverview;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        initView();


        Intent intent = getIntent();
        image = intent.getStringExtra(Config.BUNDLE_POSTER_IMAGE);
        tittle = intent.getStringExtra(Config.BUNDLE_TITTLE);

        getSupportActionBar().setTitle(tittle);
        overview = intent.getStringExtra(Config.BUNDLE_OVERVIEW);
        release = intent.getStringExtra(Config.BUNDLE_RELEASE_DATE);

        voteCount = intent.getStringExtra(Config.BUNDLE_VOTE_COUNT);
        voteAverage = intent.getStringExtra(Config.BUNDLE_VOTE_AVERAGE);
        popularity = intent.getStringExtra(Config.BUNDLE_POPULARITY);
        language = intent.getStringExtra(Config.BUNDLE_ORIGINAL_LANGUAGE);
        backdrophImage = intent.getStringExtra(Config.BUNDLE_BACKDROPH_IMAGE);

        Glide.with(this).load(backdrophImage).error(R.drawable.ic_launcher_background).into(ivDetailBackdrophImage);
        Glide.with(this).load(image).error(R.drawable.ic_launcher_background).into(ivDetailMovie);

        tvDetailReleaseDate.setText(release);
        tvDetailVoteAverage.setText(voteAverage);
        tvDetailVoteCount.setText(voteCount);
        tvDetailOriginalLanguage.setText(language);
        tvDetailOverview.setText(overview);
        rating.setRating(Float.parseFloat(voteAverage));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView() {
        ivDetailBackdrophImage = (ImageView) findViewById(R.id.iv_detail_backdroph_image);
        ivDetailMovie = (ImageView) findViewById(R.id.iv_detail_movie);
        tvDetailReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        tvDetailVoteAverage = (TextView) findViewById(R.id.tv_detail_vote_average);
        tvDetailVoteCount = (TextView) findViewById(R.id.tv_detail_vote_count);
        tvDetailOriginalLanguage = (TextView) findViewById(R.id.tv_detail_original_language);
        tvDetailOverview = (TextView) findViewById(R.id.tv_detail_overview);
        rating = (RatingBar) findViewById(R.id.rating);
    }


}
