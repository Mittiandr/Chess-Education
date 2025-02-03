package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesslearning.R;
import com.example.chesslearning.adapter.StepsAdapter;
import com.example.chesslearning.databinding.FragmentDebutBinding;
import com.example.chesslearning.databinding.FragmentMainBinding;
import com.example.chesslearning.model.DebutModel;
import com.example.chesslearning.model.StepModel;
import com.example.chesslearning.repository.FirebaseRepository;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.Inflater;

public class DebutFragment extends Fragment implements StepsAdapter.ClickListener {
    private FragmentDebutBinding binding;
    private DebutModel currentDebut;
    private ArrayList<StepModel> steps;

    @Override
    public void nextStep(int position) {
        binding.stepsViewPager.setCurrentItem(position + 1);
    }

    @Override
    public void backStep(int position) {
        binding.stepsViewPager.setCurrentItem(position - 1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDebutBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDebutFromBundle(getArguments());
        setupView();
        applyClick();

    }
    public void applyClick(){
        binding.ivBackButton.setOnClickListener(v->{
            Navigation.findNavController(v).popBackStack();
        });
    }
    private void setupView() {
        binding.tvDebutName.setText(currentDebut.getNameDebut());
        setAdapter();
    }

    private void setAdapter() {
        binding.stepsViewPager.setAdapter(new StepsAdapter(steps, this));

    }

    private void getDebutFromBundle(Bundle bundle) {
        Type type = new TypeToken<DebutModel>() {
        }.getType();
        Gson gson = new Gson();
        currentDebut = gson.fromJson(bundle.getString("debut", ""), type);
        steps = gson.fromJson(bundle.getString("stepList", ""), new TypeToken<ArrayList<StepModel>>() {
        }.getType());
    }

}