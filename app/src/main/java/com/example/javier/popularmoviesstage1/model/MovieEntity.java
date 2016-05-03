package com.example.javier.popularmoviesstage1.model;

import android.os.Parcel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by javie on 03/04/2016.
 */
public class MovieEntity implements Serializable {

    private static final String DATE_FORMAT_EN = "yyyy-MM-dd";

    private long id;
    private boolean adult;
    private boolean video;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String overview;
    private Date releaseDate;
    private String posterPath;
    private String backdropPath;
    private double popularity;
    private long voteCount;
    private double voteAverage;

    /**
     * Minimal Constructor.
     */
    public MovieEntity() {

    }


    /**
     * Full Constructor.
     *
     * @param id
     * @param adult
     * @param originalTitle
     * @param originalLanguage
     * @param title
     * @param overview
     * @param releaseDate
     * @param posterPath
     * @param backdropPath
     * @param popularity
     * @param voteCount
     * @param voteAverage
     */
    public MovieEntity(long id, boolean adult, String originalTitle, String originalLanguage, String title, String overview, Date releaseDate, String posterPath, String backdropPath, double popularity, long voteCount, double voteAverage, boolean video) {
        this.id = id;
        this.adult = adult;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.video = video;
    }

    protected MovieEntity(Parcel in) {
        id = in.readLong();
        adult = in.readByte() != 0;
        video = in.readByte() != 0;
        originalTitle = in.readString();
        originalLanguage = in.readString();
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        popularity = in.readDouble();
        voteCount = in.readLong();
        voteAverage = in.readDouble();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_EN);
        String releaseDate = sdf.format(getReleaseDate());
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }


}
