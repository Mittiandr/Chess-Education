package com.example.chesslearning.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesslearning.databinding.OptionNameViewBinding;
import com.example.chesslearning.databinding.ProfyGamesViewBinding;

import java.util.ArrayList;
import java.util.List;

public class ProfyGamesAdapter extends RecyclerView.Adapter<ProfyGamesAdapter.ViewHolder> {
    private ArrayList<String> bestGames;
    private OnClickListener onClickListener;

    public ProfyGamesAdapter(ArrayList<String> bestGames, OnClickListener onClickListener) {
        this.bestGames = bestGames;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ProfyGamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProfyGamesViewBinding binding = ProfyGamesViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProfyGamesAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
        holder.binding.tvBestGames.setOnClickListener(v -> {
            onClickListener.openLink(bestGames.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return bestGames.size();
    }

    public interface OnClickListener {
        public void openLink(String link);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ProfyGamesViewBinding binding;

        public ViewHolder(ProfyGamesViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int positions) {
            binding.tvBestGames.setText("Game " + (positions + 1));
        }
    }
}
