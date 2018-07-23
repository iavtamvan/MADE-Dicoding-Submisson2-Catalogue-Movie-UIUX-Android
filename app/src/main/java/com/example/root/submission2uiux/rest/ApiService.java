package com.example.root.submission2uiux.rest;

import com.example.root.submission2uiux.BuildConfig;
import com.example.root.submission2uiux.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(BuildConfig.NowPlayingEn)
    Call<MovieModel>getMovieNowPlaying(@Query("language") String language);
    @GET(BuildConfig.NowPlayingEn)
    Call<MovieModel>getMovieUpComing(@Query("language") String language);
}
