package com.example.chesslearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.chesslearning.R;
import com.example.chesslearning.app.App;
import com.example.chesslearning.databinding.OptionViewBinding;
import com.example.chesslearning.enums.OptionDifficulties;
import com.example.chesslearning.model.OptionModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {

    private ArrayList<OptionModel> list;
    private Context context;
    private ClickListener clickListener;
    public OptionsAdapter(ArrayList<OptionModel> list, Context context, ClickListener clickListener){
        this.list=list;
        this.context=context;
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OptionViewBinding binding= OptionViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OptionsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position),context);
        holder.binding.getRoot().setOnClickListener(v -> {
            clickListener.openOption(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface ClickListener{
        public void openOption(OptionModel optionModel);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public OptionViewBinding binding;

        public ViewHolder( OptionViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(OptionModel debut, Context context){
            Glide.with(binding.imgDebut)
                    .load(debut.getImage())
                    .into(binding.imgDebut);
            var name = debut.getName();
            if (App.sharedManager.isIdInLearnedOptions(debut.getId())){
                name+=" ✅";
            }
            binding.debutName.setText(name);
            binding.explanationDebut.setText(debut.getExplanation());
            if(Objects.equals(debut.getDifficulties(), OptionDifficulties.HARD.name())){
                binding.difficultiesName.setBackgroundTintList(context.getColorStateList(R.color.red_tint));
            }
            else if(Objects.equals(debut.getDifficulties(), OptionDifficulties.MEDIUM.name())) {
                binding.difficultiesName.setBackgroundTintList(context.getColorStateList(R.color.yellow_tint));
            }
            else{
                binding.difficultiesName.setBackgroundTintList(context.getColorStateList(R.color.green_tint));
            }
            binding.difficultiesName.setText(debut.getDifficulties());

        }
    }

}
