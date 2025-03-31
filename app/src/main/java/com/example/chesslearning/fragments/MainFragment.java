package com.example.chesslearning.fragments;

import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chesslearning.R;
import com.example.chesslearning.adapter.OptionsAdapter;
import com.example.chesslearning.adapter.OptionsNamesAdapter;
import com.example.chesslearning.app.App;
import com.example.chesslearning.databinding.FragmentMainBinding;
import com.example.chesslearning.enums.OptionType;
import com.example.chesslearning.model.OptionModel;
import com.example.chesslearning.repository.FirebaseRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements OptionsAdapter.ClickListener, OptionsNamesAdapter.SelectOption {
    private FirebaseRepository firebaseDataBase;
    private FragmentMainBinding binding;
    private ArrayList<OptionModel> list = new ArrayList<>();
    private OptionType currentOptionType= OptionType.DEBUTS;

    @Override
    public void selectOption(OptionType optionType) {
        currentOptionType= optionType;
        setAdapter(filterOptionsList(list,currentOptionType));
    }

    @Override
    public void openOption(OptionModel optionModel) {
        Bundle bundle = new Bundle();
        Gson gson = new Gson();
        bundle.putString("option", gson.toJson(optionModel));
        Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_debutFragment, bundle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);
        firebaseDataBase = new ViewModelProvider(this).get(FirebaseRepository.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setObservers();
        firebaseDataBase.getInformation();
        applyClick();
    }

    private void setObservers() {
        firebaseDataBase.isLoaded.observe(getViewLifecycleOwner(), isReady -> {
            if (isReady) {
                list = firebaseDataBase.getDebuts();
                binding.progressBar.setVisibility(View.GONE);
                setAdapter(filterOptionsList(list,currentOptionType));
                firebaseDataBase.isLoaded.setValue(false);
            }
        });
    }

    private void setAdapter(ArrayList<OptionModel> options) {
        binding.rvDebuts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvDebuts.setAdapter(new OptionsAdapter(options, requireContext(), this));
        binding.rvOption.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvOption.setAdapter(new OptionsNamesAdapter(firebaseDataBase.getCollectionsName(), this));
    }
    private void applyClick(){
        binding.signOut.setOnClickListener(v-> {
            App.sharedManager.userLogout();
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_loginFragment);
        });
    }
    private ArrayList<OptionModel> filterOptionsList(ArrayList<OptionModel> options, OptionType optionType){
        ArrayList<OptionModel> filterList = new ArrayList<>();
        switch (optionType){
            case FIGURES:
                options.forEach(option->{
                    if(option.getId().contains("figures")){
                        filterList.add(option);
                    }
                });
                break;
            case CASTLING:
                options.forEach(option->{
                    if(option.getId().contains("castle")){
                        filterList.add(option);}
                    });
                break;
            case TACTICS:
                options.forEach(option->{
                    if(option.getId().contains("tactic")){
                        filterList.add(option);}
                });
                break;
            case DEBUTS:
                options.forEach(option->{
                    if(option.getId().contains("Debut")){
                        filterList.add(option);}
                });
                break;
        }
        return filterList;
    }
}