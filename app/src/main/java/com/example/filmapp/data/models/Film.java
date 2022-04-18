package com.example.filmapp.data.models;

import com.google.gson.annotations.SerializedName;

public class Film {
    String id;
    String title;
    String movie_banner;
    String description;
    String director;
    String release_date;
    String running_time;
    String image;

    public String getImage() {
        return image;
    }

    public String getMovie_banner() {
        return movie_banner;
    }

    public String getDirector() {
        return director;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getRunning_time() {
        return running_time;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

}
