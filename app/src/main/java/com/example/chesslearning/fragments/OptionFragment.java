package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesslearning.adapter.StepsAdapter;
import com.example.chesslearning.app.App;
import com.example.chesslearning.databinding.FragmentOptionBinding;
import com.example.chesslearning.model.OptionModel;
import com.example.chesslearning.model.StepModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OptionFragment extends Fragment implements StepsAdapter.ClickListener {
    private FragmentOptionBinding binding;
    private OptionModel currentDebut;
    private ArrayList<StepModel> steps;

    @Override
    public void nextStep(int position) {
        if (position == steps.size() - 1) {
            App.sharedManager.addLearnedOption(currentDebut.getId());
            Navigation.findNavController(requireView()).popBackStack();
        } else {
            binding.stepsViewPager.setCurrentItem(position + 1);
        }
    }

    @Override
    public void backStep(int position) {
        binding.stepsViewPager.setCurrentItem(position - 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOptionBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDebutFromBundle(getArguments());
        setupView();
        applyClick();

    }

    public void applyClick() {
        binding.ivBackButton.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
    }

    private void setupView() {
        binding.tvDebutName.setText(currentDebut.getName());
        setAdapter();
    }

    private void setAdapter() {
        binding.stepsViewPager.setAdapter(new StepsAdapter(steps, this, requireContext()));

    }

    private void getDebutFromBundle(Bundle bundle) {
        Type type = new TypeToken<OptionModel>() {
        }.getType();
        Gson gson = new Gson();
        currentDebut = gson.fromJson(bundle.getString("option", ""), type);
        steps = currentDebut.getSteps();
    }

}