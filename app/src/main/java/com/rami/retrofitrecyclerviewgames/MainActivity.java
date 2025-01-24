package com.rami.retrofitrecyclerviewgames;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rami.retrofitrecyclerviewgames.adapter.GameAdapter;
import com.rami.retrofitrecyclerviewgames.model.Game;
import com.rami.retrofitrecyclerviewgames.model.GameDetails;
import com.rami.retrofitrecyclerviewgames.model.GameResponse;
import com.rami.retrofitrecyclerviewgames.network.GameApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "2780e762d2ac4baaaeeca3c57429877e";
    private static final int PAGE_SIZE = 20;

    private GameApiService apiService;
    private GameAdapter gameAdapter;
    private RecyclerView gamesRecyclerView;
    private EditText searchEditText;

    private int currentPage = 1;
    private String currentQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrofit setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(GameApiService.class);

        // RecyclerView setup
        gamesRecyclerView = findViewById(R.id.games_recyclerview);
        gamesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        gameAdapter = new GameAdapter(this::showGameDetails);
        gamesRecyclerView.setAdapter(gameAdapter);

        searchEditText = findViewById(R.id.search_edittext);
        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(v -> {
            currentQuery = searchEditText.getText().toString();
            currentPage = 1;
            searchGames();
        });
    }

    private void searchGames() {
        if (TextUtils.isEmpty(currentQuery)) {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.searchGames(API_KEY, currentQuery, currentPage, PAGE_SIZE)
                .enqueue(new Callback<GameResponse>() {
                    @Override
                    public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (currentPage == 1) {
                                gameAdapter.setGames(response.body().games);
                            } else {
                                // For pagination if needed
                                // gameAdapter.addGames(response.body().games);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "No juegos encontrados", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GameResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showGameDetails(Game game) {
        Intent intent = new Intent(this, GameDetailsActivity.class);
        intent.putExtra(GameDetailsActivity.EXTRA_GAME_ID, game.id);
        intent.putExtra(GameDetailsActivity.EXTRA_GAME_NAME, game.name);
        intent.putExtra(GameDetailsActivity.EXTRA_GAME_IMAGE, game.imageUrl);
        startActivity(intent);
    }
}