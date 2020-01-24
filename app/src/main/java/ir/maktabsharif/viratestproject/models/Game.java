package ir.maktabsharif.viratestproject.models;

import com.squareup.moshi.Json;

public class Game {

    @Json(name = "title")
    private String mTitle;

    @Json(name = "description")
    private String mDescription;

    @Json(name = "rate")
    private String mRate;

    @Json(name = "image")
    private String mImageURL;

    @Json(name = "video")
    private String mVideoURL;

    @Json(name = "players_count")
    private int mPlayersCount;

    @Json(name = "genre")
    private Genre mGenre;


    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getRate() {
        return mRate;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public String getVideoURL() {
        return mVideoURL;
    }

    public int getPlayersCount() {
        return mPlayersCount;
    }

    public Genre getGenre() {
        return mGenre;
    }
}
