package com.example.javier.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javier.popularmoviesstage1.api.MovieAPI;
import com.example.javier.popularmoviesstage1.model.MovieEntity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends ActionBarActivity {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
        /**
         * A placeholder fragment containing a simple view.
         */

    }

    public static class DetailFragment extends Fragment {

        private TextView titleDetail;
        private TextView releaseDate;
        private TextView overview;
        private TextView rating;
        private TextView voteCount;
        private TextView title;
        private ImageView miniPoster;
        private ImageView backPoster;


        public DetailFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);


            titleDetail = (TextView) rootView.findViewById(R.id.titleDetail);
            rating = (TextView) rootView.findViewById(R.id.rating);
            overview = (TextView) rootView.findViewById(R.id.plotText);
            releaseDate = (TextView) rootView.findViewById(R.id.releaseDateText);
            voteCount = (TextView) rootView.findViewById(R.id.voteText);
            miniPoster = (ImageView) rootView.findViewById(R.id.posterMiniImage);
            backPoster = (ImageView) rootView.findViewById(R.id.headerPoster);

            if (intent != null && intent.hasExtra("SELECTED_MOVIE")) {


                MovieEntity movie = (MovieEntity) intent.getSerializableExtra("SELECTED_MOVIE");

                if (movie != null) {
                    titleDetail.setText(movie.getTitle());
                    overview.setText(movie.getOverview());
                    releaseDate.setText(movie.getReleaseDateStr());
                    rating.setText(String.valueOf(movie.getVoteAverage()));
                    voteCount.setText(String.valueOf(movie.getVoteCount()));
                    titleDetail.setText(movie.getTitle());


                    Picasso.with(getActivity()).load(MovieAPI.MOVIE_DB_BASE_IMAGE_URI +
                            MovieAPI.ImageSizes.W185 +
                            movie.getPosterPath()).fit().into(miniPoster);

                    Picasso.with(getActivity()).load(MovieAPI.MOVIE_DB_BASE_IMAGE_URI +
                            MovieAPI.ImageSizes.W500 +
                            movie.getBackdropPath()).fit().into(backPoster);


                }


            }
            return rootView;
        }
    }


}