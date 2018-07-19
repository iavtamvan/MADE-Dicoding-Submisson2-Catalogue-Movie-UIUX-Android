package com.example.root.submission2uiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.submission2uiux.R;
import com.example.root.submission2uiux.feature.activity.DetailActivity;
import com.example.root.submission2uiux.helper.Config;
import com.example.root.submission2uiux.model.MovieModel;
import com.example.root.submission2uiux.model.ResultsItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iav_root on 7/16/18.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>  implements Filterable {
    private Context context;
    //TODO 1
    private List<ResultsItem> listPopuler;
    private List<ResultsItem> searchResult;
    //    private ArrayList<MovieModel> listData;

    //TODO 2
    public MovieAdapter(Context context, ArrayList<ResultsItem> listPopuler) {
        this.context = context;
        this.listPopuler = listPopuler;
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder holder, final int position) {
        //TODO 3
//        http://image.tmdb.org/t/p/w185/5N20rQURev5CNDcMjHVUZhpoCNC.jpg
        holder.tvJudulFilm.setText(listPopuler.get(position).getTitle());
        holder.tvoverview.setText(listPopuler.get(position).getOverview() + " ...");

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
        String inputDateStr= listPopuler.get(position).getReleaseDate();
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);



        holder.tvrelease.setText(outputDateStr);

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + listPopuler.get(position).getPosterPath())
                .into(holder.ivGambarFilm);

        holder.btn_item_list_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Config.BUNDLE_POSTER_IMAGE, "https://image.tmdb.org/t/p/w500" + listPopuler.get(position).getPosterPath());
                intent.putExtra(Config.BUNDLE_TITTLE, holder.tvJudulFilm.getText().toString().trim());
                intent.putExtra(Config.BUNDLE_OVERVIEW, listPopuler.get(position).getOverview());
                intent.putExtra(Config.BUNDLE_RELEASE_DATE, holder.tvrelease.getText().toString().trim());

                intent.putExtra(Config.BUNDLE_VOTE_COUNT, listPopuler.get(position).getVoteCount());
                intent.putExtra(Config.BUNDLE_VOTE_AVERAGE, listPopuler.get(position).getVoteAverage());
                intent.putExtra(Config.BUNDLE_POPULARITY, listPopuler.get(position).getPopularity());
                intent.putExtra(Config.BUNDLE_ORIGINAL_LANGUAGE, listPopuler.get(position).getOriginalLanguage());
                intent.putExtra(Config.BUNDLE_BACKDROPH_IMAGE, "https://image.tmdb.org/t/p/w500" + listPopuler.get(position).getBackdropPath());
                context.startActivity(intent);
            }
        });

        holder.btn_item_list_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = holder.tvJudulFilm.getText().toString().trim();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    //TODO 4
    @Override
    public int getItemCount() {
        return listPopuler.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<ResultsItem> resultsItems = new ArrayList<>();

                if (searchResult == null)
                    searchResult = listPopuler;
                if (constraint != null){
                    if (listPopuler != null &searchResult.size()>0){
                        for (final ResultsItem g : searchResult){
//                            if (g.getTitle().equalsIgnoreCase();
                            if (g.getTitle().toLowerCase().contains(constraint.toString()))resultsItems.add(g);
                        }
                    }
                    oReturn.values = resultsItems;
                }

                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listPopuler = (ArrayList<ResultsItem>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarFilm;
        TextView tvJudulFilm;
        TextView tvoverview;
        TextView tvrelease;
        CardView cv_klick_detail;
        Button btn_item_list_share;
        Button btn_item_list_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivGambarFilm = itemView.findViewById(R.id.iv_item_list_poster);
            tvJudulFilm = itemView.findViewById(R.id.tv_item_list_tittle);
            tvoverview = itemView.findViewById(R.id.tv_item_list_overview);
            tvrelease = itemView.findViewById(R.id.tv_item_list_date);
            cv_klick_detail = itemView.findViewById(R.id.cv_klik_detail);
            btn_item_list_share = itemView.findViewById(R.id.btn_item_list_share);
            btn_item_list_detail = itemView.findViewById(R.id.btn_item_list_detail);
        }
    }
}
