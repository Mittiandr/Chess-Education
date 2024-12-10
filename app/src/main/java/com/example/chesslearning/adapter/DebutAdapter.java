package com.example.chesslearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chesslearning.R;
import com.example.chesslearning.databinding.DebutViewBinding;
import com.example.chesslearning.enums.DebutType;
import com.example.chesslearning.model.DebutModel;

import java.util.ArrayList;
import java.util.Objects;

public class DebutAdapter extends RecyclerView.Adapter<DebutAdapter.ViewHolder> {
    private ArrayList<DebutModel> list;
    private Context context;
    public DebutAdapter(ArrayList<DebutModel> list,Context context){
        this.list=list;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DebutViewBinding binding= DebutViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DebutAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position),context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public DebutViewBinding binding;

        public ViewHolder( DebutViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(DebutModel debut, Context context){
            Glide.with(binding.imgDebut)
                    .load(debut.getImage())
                    .into(binding.imgDebut);
            binding.debutName.setText(debut.getNameDebut());
            binding.explanationDebut.setText(debut.getExplanation());
            if(Objects.equals(debut.getDifficulties(), DebutType.HARD.name())){
                binding.difficultiesName.setBackgroundTintList(context.getColorStateList(R.color.red_tint));
            }
            else if(Objects.equals(debut.getDifficulties(), DebutType.MEDIUM.name())) {
                binding.difficultiesName.setBackgroundTintList(context.getColorStateList(R.color.yellow_tint));
            }
            else{
                binding.difficultiesName.setBackgroundTintList(context.getColorStateList(R.color.green_tint));
            }
            binding.difficultiesName.setText(debut.getDifficulties());

        }
    }

}
