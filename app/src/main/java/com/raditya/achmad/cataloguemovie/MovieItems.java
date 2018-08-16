package com.raditya.achmad.cataloguemovie;

import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Achmad on 14-07-18
 */
public class MovieItems {
    private String movTitle;
    private String movOverview;
    private String movVote;
    private String movRating;
    private String movDateRelease;
    private String movImage;

    public MovieItems(JSONObject obj) {

        //Get value according to JSON object name value
        try {
            String title = obj.getString("title");
            String overview = obj.getString("overview");
            String dateRelease = obj.getString("release_date");
            String image = obj.getString("poster_path");
            String rating = obj.getString("vote_average");
            String vote = obj.getString("vote_count");


            this.movTitle = title;
            this.movOverview = overview;
            this.movDateRelease = dateRelease;
            this.movImage = image;
            this.movRating = rating;
            this.movVote = vote;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // Setter and Getter function
    public String getMovTitle() {
        return movTitle;
    }

    public void setMovTitle(String movTitle) {
        this.movTitle = movTitle;
    }

    public String getMovOverview() {
        return movOverview;
    }

    public void setMovOverview(String movOverview) {
        this.movOverview = movOverview;
    }

    public String getMovVote() {
        return movVote;
    }

    public void setMovVote(String vote) {
        this.movVote = movVote;
    }

    public String getMovRating() {
        return movRating;
    }

    public void setMovRating(String movRating) {
        this.movRating = movRating;
    }

    public String getMovDateRelease() {
        return movDateRelease;
    }

    public void setMovDateRelease(String movDateRelease) {
        this.movDateRelease = movDateRelease;
    }

    public String getMovImage() {
        return movImage;
    }

    public void setMovImage(String movImage) {
        this.movImage = movImage;
    }
}
