package com.rami.retrofitrecyclerviewgames.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameResponse {
    @SerializedName("results")
    public List<Game> games;
}
