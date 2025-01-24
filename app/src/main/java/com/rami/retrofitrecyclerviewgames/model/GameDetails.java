package com.rami.retrofitrecyclerviewgames.model;

import com.google.gson.annotations.SerializedName;

public class GameDetails {
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("rating")
    public float rating;
    @SerializedName("released")
    public String releaseDate;
}
