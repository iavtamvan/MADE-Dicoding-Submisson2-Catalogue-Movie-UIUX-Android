package com.example.root.submission2uiux.feature.fragment;


import android.app.ProgressDialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.root.submission2uiux.R;
import com.example.root.submission2uiux.adapter.MovieAdapter;
import com.example.root.submission2uiux.helper.Config;
import com.example.root.submission2uiux.model.MovieModel;
import com.example.root.submission2uiux.model.ResultsItem;
import com.example.root.submission2uiux.rest.ApiService;
import com.example.root.submission2uiux.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    private ArrayList<ResultsItem> listResults;
    private SearchView svNowPlayingMovie;
    private RecyclerView rvNowPlayingMovie;
    private MovieAdapter movieAdapter;
    private EditText edtSearch;

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        initView(view);

        listResults = new ArrayList<>();
        getMovieNowPlaying();


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                movieAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
    private void getMovieNowPlaying() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Sync data...", false, false);
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getMovieNowPlaying().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    listResults = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), listResults);
                    rvNowPlayingMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvNowPlayingMovie.setAdapter(movieAdapter);
                    loading.dismiss();
//                    Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        svNowPlayingMovie = (SearchView) view.findViewById(R.id.sv_now_playing_movie);
        rvNowPlayingMovie = (RecyclerView) view.findViewById(R.id.rv_now_playing_movie);
        edtSearch = (EditText) view.findViewById(R.id.edt_search);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getActivity().getMenuInflater().inflate(R.menu.home, menu);
//        MenuItem item = menu.findItem(R.id.search);
//
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setIconifiedByDefault(true);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query)
//            {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                movieAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
}
