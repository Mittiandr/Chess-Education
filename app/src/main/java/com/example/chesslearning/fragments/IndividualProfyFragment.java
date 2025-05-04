package com.example.chesslearning.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chesslearning.R;
import com.example.chesslearning.adapter.ProfyGamesAdapter;
import com.example.chesslearning.databinding.FragmentIndividualProfyBinding;
import com.example.chesslearning.databinding.FragmentProfyBinding;
import com.example.chesslearning.model.ProfessionalModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IndividualProfyFragment extends Fragment implements ProfyGamesAdapter.OnClickListener {

    private FragmentIndividualProfyBinding binding;
    private ProfessionalModel professionalModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIndividualProfyBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        professionalModel = getProfy();
        setUpView();
    }

    private void setUpView() {
        setAdapter();
        binding.tvBornProfy.setText(professionalModel.getBorn());
        binding.tvCountryProfy.setText(requireContext().getResources().getString(R.string.country)+professionalModel.getCountry());
        binding.tvRateProfy.setText(requireContext().getResources().getString(R.string.pick_rate)+professionalModel.getPeakRate());
        binding.tvNameProfy.setText(professionalModel.getName());
        binding.tvExplanationProfy.setText(professionalModel.getExplanation());
        Glide.with(binding.ivProfy)
                .load(professionalModel.getImage())
                .into(binding.ivProfy);
    }

    @Override
    public void openLink(String link) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    private void setAdapter() {
        binding.rvBestGames.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvBestGames.setAdapter(new ProfyGamesAdapter(professionalModel.getBestGames(), this));
    }

    private ProfessionalModel getProfy() {
        Gson gson = new Gson();
        Type typeToken = new TypeToken<ProfessionalModel>() {
        }.getType();
        return gson.fromJson(getArguments().getString("Profy"), typeToken);
    }
}