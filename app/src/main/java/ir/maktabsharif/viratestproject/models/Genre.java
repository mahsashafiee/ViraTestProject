package ir.maktabsharif.viratestproject.models;

import com.squareup.moshi.Json;

public class Genre {

    @Json(name = "id")
    private Long mId;

    @Json(name = "name")
    private String mName;

    @Json(name = "image")
    private String mImageURL;

    public Long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getImageURL() {
        return mImageURL;
    }
}
