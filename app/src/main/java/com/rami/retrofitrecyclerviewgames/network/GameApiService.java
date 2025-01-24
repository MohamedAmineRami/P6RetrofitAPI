package com.rami.retrofitrecyclerviewgames.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.google.gson.annotations.SerializedName;
import com.rami.retrofitrecyclerviewgames.model.GameResponse;

public interface GameApiService {
    @GET("games")
    Call<GameResponse> searchGames(
            @Query("key") String apiKey,
            @Query("search") String query,
            @Query("page") int page,
            @Query("page_size") int pageSize
    );
}