package com.example.javier.popularmoviesstage1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.javier.popularmoviesstage1.adapters.MovieAdapter;
import com.example.javier.popularmoviesstage1.api.MovieAPI;
import com.example.javier.popularmoviesstage1.async.AsyncResponse;
import com.example.javier.popularmoviesstage1.async.FetchMoviesTask;
import com.example.javier.popularmoviesstage1.model.MovieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javie on 03/04/2016.
 */
public class MovieFragment extends Fragment {

    private MovieAdapter mMoviesAdapter = null;
    private List<MovieEntity> mListMovies = null;
    /**
     * progress dialog to show user that the backup is processing.
     */
    private ProgressDialog dialog;

    public MovieFragment() {
        this.setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);


        mListMovies = new ArrayList<MovieEntity>();
        dialog = new ProgressDialog(inflater.getContext());
        mMoviesAdapter = new MovieAdapter(getActivity(), mListMovies);
        fetchMoviesToMovieTask(MovieAPI.POPULAR_CALL);




        GridView mgridView = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        mgridView.setAdapter(mMoviesAdapter);

        mgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieAdapter movieAdapter = (MovieAdapter) parent.getAdapter();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("SELECTED_MOVIE", movieAdapter.getItem(position));
                startActivity(intent);
            }
        });
        return viewRoot;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.moviefragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_sort_popular) {
            fetchMoviesToMovieTask(MovieAPI.POPULAR_CALL);
            return true;
        } else if (id == R.id.action_sort_rated) {
            fetchMoviesToMovieTask(MovieAPI.TOP_CALL);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchMoviesToMovieTask(String criteriaCall) {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask(new AsyncResponse() {
            @Override
            public void onPostExecuteDelegate(List<MovieEntity> results) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                mListMovies = results;
                updatePosterAdapter();
            }

            @Override
            public void onPreExecute() {
                dialog.setMessage("Loading...");
                dialog.show();
            }
        });
        fetchMoviesTask.execute(criteriaCall);
        mMoviesAdapter.notifyDataSetChanged();
    }

    // updates the ArrayAdapter of poster images
    private void updatePosterAdapter() {
        mMoviesAdapter.clear();
        for (MovieEntity movie : mListMovies) {
            mMoviesAdapter.add(movie);
        }
    }

}
