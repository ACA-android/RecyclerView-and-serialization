package com.dm.recyclerview2;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {

    private String title;
    private int duration = 120;
    private int year = 1994;
    private String director = "Guy Richie";
    private Bitmap coverImage = null;
    private List<String> actors = new ArrayList<>();

    private int criticScore = 5;

    private Movie() {

    }

    private Movie(Parcel in) {
        title = in.readString();
        duration = in.readInt();
        year = in.readInt();
        director = in .readString();
        coverImage = in.readParcelable(null);
        in.readStringList(actors);
        criticScore = in.readInt();
    }

    public void setCriticScore(int criticScore) {
        this.criticScore = criticScore;
    }

    public int getCriticScore() {
        return criticScore;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public Bitmap getCoverImage() {
        return coverImage;
    }

    public List<String> getActors() {
        return actors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(duration);
        dest.writeInt(year);
        dest.writeString(director);
        dest.writeParcelable(coverImage, flags);
        dest.writeStringList(actors);
        dest.writeInt(criticScore);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static class Builder {
        private Movie movie;

        public Builder(String name) {
            movie = new Movie();
            movie.title = name;
        }

        public Builder setDuration(int duration) {
            movie.duration = duration;
            return this;
        }

        public Builder setYear(int year) {
            movie.year = year;
            return this;
        }

        public Builder setDirector(String director) {
            movie.director = director;
            return this;
        }

        public Builder setScore(int score) {
            movie.criticScore = score;
            return this;
        }

        public Builder setCoverImage(Bitmap image) {
            movie.coverImage = image;
            return this;
        }

        public Builder setActors(List<String> actors) {
            movie.actors = actors;
            return this;
        }

        public Movie create() {
            return movie;
        }
    }
}
