package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesslearning.R;
import com.example.chesslearning.adapter.DebutAdapter;
import com.example.chesslearning.databinding.FragmentLoginBinding;
import com.example.chesslearning.databinding.FragmentMainBinding;
import com.example.chesslearning.enums.DebutType;
import com.example.chesslearning.model.DebutModel;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private ArrayList<DebutModel> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMainBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();
    }

    private void setAdapter(){
        list.add(new DebutModel("https://i.ibb.co/pr1NwkJ/italian-game.png","Italian Defence", DebutType.HARD,"The Italian Defence is a chess opening that begins with the moves 1. e4 e5 2. Nf3 Nc6 3. Bc4. It is one of the oldest and most respected openings in chess, dating back to the 16th century. The key idea behind the Italian Defence is for Black to counterattack and develop solidly while keeping the position flexible. Unlike the Ruy Lopez, which aims for a more strategic buildup, the Italian Defence often leads to open, tactical positions where both sides have chances for rapid development and piece activity. Common continuations for Black include 3... Bc5, the most popular, leading to the Giuoco Piano, or 3... Nf6, leading to the more sharp and tactical Two Knights Defense. The Italian Defence is well-suited for players who enjoy dynamic, open positions and tactical skirmishes."));
        list.add(new DebutModel("https://i.ibb.co/NWZWtLY/sicilian-big.jpg","Berd Defence", DebutType.MEDIUM,"The Italian Defence is a chess opening that begins with the moves 1. e4 e5 2. Nf3 Nc6 3. Bc4. It is one of the oldest and most respected openings in chess, dating back to the 16th century. The key idea behind the Italian Defence is for Black to counterattack and develop solidly while keeping the position flexible. Unlike the Ruy Lopez, which aims for a more strategic buildup, the Italian Defence often leads to open, tactical positions where both sides have chances for rapid development and piece activity. Common continuations for Black include 3... Bc5, the most popular, leading to the Giuoco Piano, or 3... Nf6, leading to the more sharp and tactical Two Knights Defense. The Italian Defence is well-suited for players who enjoy dynamic, open positions and tactical skirmishes."));
        list.add(new DebutModel("https://i.ibb.co/2kdXyXy/danish-gambit.png","Danish Gambit", DebutType.EASY,"The Italian Defence is a chess opening that begins with the moves 1. e4 e5 2. Nf3 Nc6 3. Bc4. It is one of the oldest and most respected openings in chess, dating back to the 16th century. The key idea behind the Italian Defence is for Black to counterattack and develop solidly while keeping the position flexible. Unlike the Ruy Lopez, which aims for a more strategic buildup, the Italian Defence often leads to open, tactical positions where both sides have chances for rapid development and piece activity. Common continuations for Black include 3... Bc5, the most popular, leading to the Giuoco Piano, or 3... Nf6, leading to the more sharp and tactical Two Knights Defense. The Italian Defence is well-suited for players who enjoy dynamic, open positions and tactical skirmishes."));
        binding.rvDebuts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false));
        binding.rvDebuts.setAdapter(new DebutAdapter(list, requireContext()));
    }
}