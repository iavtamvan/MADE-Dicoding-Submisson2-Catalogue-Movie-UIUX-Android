package com.example.root.submission2uiux.rest;

import com.example.root.submission2uiux.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("now_playing?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US")
    Call<MovieModel>getMovieNowPlaying();
    @GET("upcoming?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US")
    Call<MovieModel>getMovieUpComing();
}
