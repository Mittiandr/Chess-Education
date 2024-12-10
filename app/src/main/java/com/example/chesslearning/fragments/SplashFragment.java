package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesslearning.R;
import com.example.chesslearning.app.App;
import com.example.chesslearning.databinding.FragmentMainBinding;
import com.example.chesslearning.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentSplashBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(App.sharedManager.isUserAuthorized()){
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_mainFragment);
        }
        else {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_loginFragment);

        }
    }
}