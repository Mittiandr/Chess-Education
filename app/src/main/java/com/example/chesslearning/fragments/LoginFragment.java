package com.example.chesslearning.fragments;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chesslearning.R;
import com.example.chesslearning.app.App;
import com.example.chesslearning.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore dataBase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBase = FirebaseFirestore.getInstance();
        applyClick();
    }

    private void applyClick() {
        binding.authorizationButton.setOnClickListener(v -> {
            findNavController(v).navigate(R.id.action_loginFragment_to_registrationFragment);
        });
        binding.authorizeButton.setOnClickListener(v -> {
            checkEnterData();
        });
    }


    private void checkEnterData() {
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.textLogin.getText()).matches()) {
            Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.incorrect_email), Toast.LENGTH_SHORT).show();
        } else if (binding.textPassword.getText().toString().length() < 6 || binding.textPassword.getText().toString().contains(" ")) {

            Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.incorrect_password), Toast.LENGTH_SHORT).show();
        } else {
            allCorrect();
        }
    }

    private void allCorrect() {
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(binding.textLogin.getText().toString(), binding.textPassword.getText().toString()).addOnCompleteListener(requireActivity(), task -> {
            binding.authorizeButton.setEnabled(false);
            if (task.isSuccessful()) {
                findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_mainFragment);
                createUser();
            }
        }).addOnFailureListener(requireActivity(), error -> {
            binding.authorizeButton.setEnabled(true);
            Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_LONG).show();
        });

    }

    private void createUser() {
        App.sharedManager.userAuthorize();
        binding.authorizeButton.setEnabled(true);
    }

}
