package com.rami.retrofitrecyclerviewgames.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rami.retrofitrecyclerviewgames.R;
import com.rami.retrofitrecyclerviewgames.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private List<Game> games = new ArrayList<>();
    private OnGameClickListener listener;

    public interface OnGameClickListener {
        void onGameClick(Game game);
    }

    public GameAdapter(OnGameClickListener listener) {
        this.listener = listener;
    }

    public void setGames(List<Game> newGames) {
        games.clear();
        games.addAll(newGames);
        notifyDataSetChanged();
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = games.get(position);
        holder.bind(game);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView gameImage;
        TextView gameName;

        GameViewHolder(View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.game_image);
            gameName = itemView.findViewById(R.id.game_name);
        }

        void bind(Game game) {
            Glide.with(itemView.getContext())
                    .load(game.imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .into(gameImage);

            gameName.setText(game.name);

            itemView.setOnClickListener(v -> listener.onGameClick(game));
        }
    }
}