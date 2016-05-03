package com.example.javier.popularmoviesstage1.api;

import android.net.Uri;
import android.util.Log;

import com.example.javier.popularmoviesstage1.BuildConfig;
import com.example.javier.popularmoviesstage1.model.MovieEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Class to interact with the theMovieDB.
 * Created by javier on 28/03/2016.
 */
public class MovieAPI {


    private static final String LOG_TAG = "MovieApi";

    public static final String POPULAR_CALL = "pCall";
    public static final String TOP_CALL = "tCall";

    public static final String API_KEY = BuildConfig.MOVIE_DB_API_KEY;
    public static final String MOVIE_DB_BASE_IMAGE_URI = "http://image.tmdb.org/t/p/";

    private static final String MOVIE_DB_BASE_URI = "http://api.themoviedb.org/3/";
    private static final String QUESTION_MARK = "?";
    private static final String POPULAR_QUERY = "movie/popular";
    private static final String TOP_RATED_QUERY = "movie/top_rated";
    private static final String API_KEY_PARAM = "api_key";

    public static class ImageSizes {
        public static final String W92 = "w92";
        public static final String W154 = "w154";
        public static final String W185 = "w185";
        public static final String W342 = "w342";
        public static final String W500 = "w500";
        public static final String W780 = "w780";
        public static final String ORIGINAL = "original";

    }


    private static List<MovieEntity> callMovieDBApi(String inputUrl) {

        List<MovieEntity> lMoviesFetched = null;
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        try {

            //instanciate a fresh URL with our inputUrl String.
            URL url = new URL(inputUrl);

            //Create the request to theMovieDb and open the connection.
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (null == inputStream) {
                //You know nothing Jhon Snow...
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            String moviesJsonStr = buffer.toString();

            lMoviesFetched = new JsonParserMovieAPI().parseMoviesJson(moviesJsonStr);

            if (null != lMoviesFetched && !lMoviesFetched.isEmpty()) {
                Log.d(LOG_TAG, "getPopularMovies: Fetched " + lMoviesFetched.size() + " Movies from the theMovieDb");
            }


        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Something wrong building the URL", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error handling the readers", e);
        } finally {
            //Always close the connection.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            //Always close the Buffers
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.d(LOG_TAG, "Error closing the BufferReader.", e);
                }
            }
        }
        return lMoviesFetched;
    }


    public static List<MovieEntity> getPopularMovies() {

        List<MovieEntity> lMoviesFetched = null;

        Uri builtUri = Uri.parse(MOVIE_DB_BASE_URI + POPULAR_QUERY + QUESTION_MARK).buildUpon().
                appendQueryParameter(API_KEY_PARAM, API_KEY).build();

        lMoviesFetched = callMovieDBApi(builtUri.toString());

        return lMoviesFetched;
    }

    public static List<MovieEntity> getTopRatedMovies() {

        List<MovieEntity> lMoviesFetched = null;

        Uri builtUri = Uri.parse(MOVIE_DB_BASE_URI + TOP_RATED_QUERY + QUESTION_MARK).buildUpon().
                appendQueryParameter(API_KEY_PARAM, API_KEY).build();

        lMoviesFetched = callMovieDBApi(builtUri.toString());

        return lMoviesFetched;
    }


}


