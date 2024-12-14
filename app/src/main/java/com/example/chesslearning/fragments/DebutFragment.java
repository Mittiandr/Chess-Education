package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesslearning.R;
import com.example.chesslearning.databinding.FragmentDebutBinding;
import com.example.chesslearning.databinding.FragmentMainBinding;

import java.util.zip.Inflater;

public class DebutFragment extends Fragment {
    private FragmentDebutBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentDebutBinding.inflate(inflater);
        return binding.getRoot();
    }
}