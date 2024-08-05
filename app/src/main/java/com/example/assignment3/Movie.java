package com.example.assignment3;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String year;
    private String imdbID;
    private String posterUrl;
    private String type;
    private String description;
    private String rating;
    private String studio;

    public Movie(String title, String year, String imdbID, String posterUrl, String type) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.posterUrl = posterUrl;
        this.type = type;
        this.description = "";
        this.rating = "";
        this.studio = "";
    }

    // Getters and setters for all fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
}
