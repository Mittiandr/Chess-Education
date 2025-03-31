package com.example.chesslearning.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.BoolRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesslearning.databinding.OptionNameViewBinding;
import com.example.chesslearning.databinding.OptionViewBinding;
import com.example.chesslearning.enums.OptionType;
import com.example.chesslearning.model.OptionModel;

import java.util.ArrayList;
import java.util.List;

public class OptionsNamesAdapter extends RecyclerView.Adapter<OptionsNamesAdapter.ViewHolder> {

    private List<String> nameCollection;
    private SelectOption selectOption;

    public OptionsNamesAdapter(List<String> nameCollection, SelectOption selectOption) {
        this.nameCollection = nameCollection;
        this.selectOption = selectOption;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OptionNameViewBinding binding = OptionNameViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OptionsNamesAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(nameCollection.get(position));
        holder.binding.tvNameOption.setOnClickListener(v ->{
            switch (position){
                case 0:
                    selectOption.selectOption(OptionType.DEBUTS);
                    break;
                case 1:
                    selectOption.selectOption(OptionType.TACTICS);
                    break;
                case 2:
                    selectOption.selectOption(OptionType.CASTLING);
                    break;
                case 3:
                    selectOption.selectOption(OptionType.FIGURES);
                    break;
                default:
                    selectOption.selectOption(OptionType.DEBUTS);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameCollection.size();
    }

    public interface SelectOption {
        public void selectOption(OptionType optionType);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public OptionNameViewBinding binding;

        public ViewHolder(OptionNameViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String nameOption) {
            binding.tvNameOption.setText(nameOption);
        }
    }
}
