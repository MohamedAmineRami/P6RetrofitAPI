package com.rami.retrofitrecyclerviewgames.model;

import com.google.gson.annotations.SerializedName;

public class Game {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("background_image")
    public String imageUrl;
}
