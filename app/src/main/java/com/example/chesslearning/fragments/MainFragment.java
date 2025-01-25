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
import com.example.chesslearning.adapter.DebutAdapter;
import com.example.chesslearning.databinding.FragmentLoginBinding;
import com.example.chesslearning.databinding.FragmentMainBinding;
import com.example.chesslearning.enums.DebutType;
import com.example.chesslearning.model.DebutModel;
import com.example.chesslearning.model.StepModel;
import com.example.chesslearning.repository.FirebaseRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;

public class MainFragment extends Fragment implements DebutAdapter.ClickListener{
    private FirebaseRepository firebaseDataBase;
    private FragmentMainBinding binding;
    private ArrayList<DebutModel> list=new ArrayList<>();
    private ArrayList<StepModel> steps;

    @Override
    public void openDebut(DebutModel debutModel) {
        Bundle bundle = new Bundle();
        Gson gson = new Gson();
        steps.removeIf(it-> !it.getIdDebut().equals(debutModel.getId()));
        Comparator<StepModel> comparator = Comparator.comparing(obj -> obj.getStepNum());
        steps.sort(comparator);
        bundle.putString("stepList", gson.toJson(steps));
        bundle.putString("debut", gson.toJson(debutModel));
        Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_debutFragment,bundle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMainBinding.inflate(inflater);
        firebaseDataBase = new ViewModelProvider(this).get(FirebaseRepository.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setObservers();
        setAdapter();
        firebaseDataBase.getInformation();
    }
    private void setObservers(){
        firebaseDataBase.isLoaded.observe(getViewLifecycleOwner(),isReady->{
            if(isReady){
                list=firebaseDataBase.getDebuts();
                steps = firebaseDataBase.getSteps();
                binding.progressBar.setVisibility(View.GONE);
                setAdapter();
                firebaseDataBase.isLoaded.setValue(false);
            }
        });
    }

    private void setAdapter(){
        binding.rvDebuts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false));
        binding.rvDebuts.setAdapter(new DebutAdapter(list, requireContext(),this));
    }
}