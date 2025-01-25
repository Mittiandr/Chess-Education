package com.example.chesslearning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chesslearning.databinding.RecycleViewStepBinding;
import com.example.chesslearning.model.DebutModel;
import com.example.chesslearning.model.StepModel;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private ArrayList<StepModel> list;
    private ClickListener clickListener;

    public StepsAdapter(ArrayList<StepModel> list, ClickListener clickListener) {
        this.list = list;
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycleViewStepBinding binding = RecycleViewStepBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StepsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position==0){
            holder.binding.backStepButton.setVisibility(View.GONE);
        }
        if(position==list.size()-1){
            holder.binding.nextStepButton.setVisibility(View.GONE);
        }
        holder.bind(list.get(position));
        holder.binding.nextStepButton.setOnClickListener(v -> {
            clickListener.nextStep(position);
        });
        holder.binding.backStepButton.setOnClickListener(view -> {
            clickListener.backStep(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickListener{
        public void nextStep(int position);
        public void backStep(int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public RecycleViewStepBinding binding;
        public ViewHolder(RecycleViewStepBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(StepModel step){
            binding.tvDebutEXplanationStep.setText(step.getExplanation());
            Glide.with(binding.imgDebut)
                    .load(step.getImage())
                    .into(binding.imgDebut);
        }

    }
}
