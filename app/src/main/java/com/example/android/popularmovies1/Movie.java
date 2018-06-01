package com.example.android.popularmovies1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabrina on 5/31/18.
 */

public class Movie implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Movie[size];
        }
    };

    //parse using gson
    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("id")
    private String id;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("vote_average")
    private String voteAvg;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLang;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genres;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public Movie(Parcel in) {
        this.voteCount = in.readString();
        this.id = in.readString();
        this.video = in.readByte() != 0;    //read boolean value
        this.voteAvg = in.readString();
        this.title = in.readString();
        this.popularity = in.readString();
        this.posterPath = in.readString();
        this.originalLang = in.readString();
        this.originalTitle = in.readString();

        //in.readList(this.genres, List.class.getClassLoader());  //read into this.genres

        this.backdropPath = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.releaseDate = in.readString();
    }

    //all getter methods
    public String getVoteCount() {
        return voteCount;
    }

    public String getId() {
        return id;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getVoteAvg() {
        return voteAvg;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLang() {
        return originalLang;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.voteCount);
        dest.writeString(this.id);
        dest.writeByte((byte) (this.video ? 1 : 0));     //write boolean as byte
        dest.writeString(this.voteAvg);
        dest.writeString(this.title);
        dest.writeString(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.originalLang);
        dest.writeString(this.originalTitle);
        //dest.writeList(this.genres);
        dest.writeString(this.backdropPath);
        dest.writeByte((byte) (this.adult ? 1 : 0));
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
    }
}
