package com.example.chesslearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chesslearning.R;
import com.example.chesslearning.databinding.RecycleViewStepBinding;
import com.example.chesslearning.model.StepModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private ArrayList<StepModel> list;
    private ClickListener clickListener;
    private Context context;

    public StepsAdapter(ArrayList<StepModel> list, ClickListener clickListener,Context context) {
        this.list = list;
        this.clickListener=clickListener;
        this.context = context;
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
            holder.binding.nextStepButton.setText(context.getResources().getString(R.string.finish));
        }
        holder.bind(list.get(position));
        holder.binding.nextStepButton.setOnClickListener(v -> {
            clickListener.nextStep(position);
        });
        holder.binding.backStepButton.setOnClickListener(view -> {
            clickListener.backStep(position);
        });
        holder.binding.explanationButton.setOnClickListener(v -> {
            createMaterialDialog(list.get(position).getExplanation());
        });
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    public interface ClickListener{
        public void nextStep(int position);
        public void backStep(int position);
    }

    private void createMaterialDialog(String explanation){
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context)
                .setTitle("More Information")
                .setMessage(explanation)
                .setPositiveButton("Close", (d, which)->{
                });
        dialog.create();
        dialog.show();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public RecycleViewStepBinding binding;
        public ViewHolder(RecycleViewStepBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(StepModel step){
            binding.tvDebutEXplanationStep.setText(step.getShortExplanation().replace("\\n","\n"));
            Glide.with(binding.imgDebut)
                    .load(step.getImage())
                    .into(binding.imgDebut);
        }

    }
}
