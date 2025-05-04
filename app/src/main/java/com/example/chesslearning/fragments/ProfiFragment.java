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
import com.example.chesslearning.adapter.ProfyAdapter;
import com.example.chesslearning.databinding.FragmentOptionBinding;
import com.example.chesslearning.databinding.FragmentProfyBinding;
import com.example.chesslearning.model.OptionModel;
import com.example.chesslearning.model.ProfessionalModel;
import com.example.chesslearning.repository.FirebaseRepository;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ProfiFragment extends Fragment implements ProfyAdapter.SelectProfy {
    private FragmentProfyBinding binding;
    private FirebaseRepository firebaseDataBase;
    private ArrayList<ProfessionalModel> list = new ArrayList<>();
    @Override
    public void select(ProfessionalModel professionalModel) {
        Gson gson = new Gson();
        Bundle bundle = new Bundle();
        bundle.putString("Profy", gson.toJson(professionalModel));
        Navigation.findNavController(requireView()).navigate(R.id.action_profiFragment_to_individualProfyFragment, bundle);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfyBinding.inflate(inflater);
        firebaseDataBase = new ViewModelProvider(this).get(FirebaseRepository.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseDataBase.getProfessionals();
        setObservers();
    }

    private void setObservers() {
        firebaseDataBase.isLoaded.observe(getViewLifecycleOwner(), isReady -> {
            if (isReady) {
                list = firebaseDataBase.getProfies();
                setAdapter(list);
                firebaseDataBase.isLoaded.setValue(false);
            }
        });
    }

    private void setAdapter(ArrayList<ProfessionalModel> professionals) {
        binding.rvProfy.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvProfy.setAdapter(new ProfyAdapter(professionals, this));
    }
}