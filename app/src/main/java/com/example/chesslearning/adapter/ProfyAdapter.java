package com.example.chesslearning.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesslearning.databinding.ProfessionalItemBinding;
import com.example.chesslearning.model.ProfessionalModel;

import java.util.List;

public class ProfyAdapter extends RecyclerView.Adapter<ProfyAdapter.ViewHolder> {
    private List<ProfessionalModel> profyList;
    private SelectProfy selectProfy;

    public ProfyAdapter(List<ProfessionalModel> profyList, SelectProfy selectProfy) {
        this.profyList = profyList;
        this.selectProfy = selectProfy;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProfessionalItemBinding binding = ProfessionalItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(profyList.get(position));
        holder.binding.tvItemProfy.setOnClickListener(v ->{
            selectProfy.select(profyList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return profyList.size();
    }

    public interface SelectProfy {
        public void select(ProfessionalModel professionalModel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ProfessionalItemBinding binding;

        public ViewHolder(ProfessionalItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProfessionalModel profy) {
            binding.tvItemProfy.setText(profy.getName());
        }
    }
}


