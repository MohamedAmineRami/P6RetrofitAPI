package com.rami.retrofitrecyclerviewgames;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_GAME_ID = "game_id";
    public static final String EXTRA_GAME_NAME = "game_name";
    public static final String EXTRA_GAME_IMAGE = "game_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // Get game details from intent
        int gameId = getIntent().getIntExtra(EXTRA_GAME_ID, -1);
        String gameName = getIntent().getStringExtra(EXTRA_GAME_NAME);
        String gameImage = getIntent().getStringExtra(EXTRA_GAME_IMAGE);

        // Setup views
        ImageView imageView = findViewById(R.id.game_details_image);
        TextView nameTextView = findViewById(R.id.game_details_name);

        // Load image
        Glide.with(this)
                .load(gameImage)
                .into(imageView);

        nameTextView.setText(gameName);
    }
}
