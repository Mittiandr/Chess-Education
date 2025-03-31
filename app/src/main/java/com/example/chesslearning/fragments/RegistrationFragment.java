package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chesslearning.R;
import com.example.chesslearning.databinding.FragmentLoginBinding;
import com.example.chesslearning.databinding.FragmentRegistrationBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding binding;
    private FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater);
        auth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        applyClick();
    }

    private void applyClick() {
        binding.registrationButton.setOnClickListener(v -> {
            if (isDataCorrect()) {
                register();
            }
        });
        binding.authorizationButton.setOnClickListener(v -> {
            goBack();
        });

    }

    private void register() {
        auth.createUserWithEmailAndPassword(binding.textLogin.getText().toString(),
                        binding.textPassword.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        goBack();
                    } else {
                        Toast.makeText(requireContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
    }

    private boolean isDataCorrect() {
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.textLogin.getText()).matches()) {
            Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.incorrect_email), Toast.LENGTH_SHORT).show();
        } else if (binding.textPassword.getText().toString().length() < 6 || binding.textPassword.getText().toString().contains(" ")) {
            Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.incorrect_password), Toast.LENGTH_SHORT).show();
        } else if (!binding.textPassword.getText().toString().equals(binding.repeatPassword.getText().toString())) {
            Toast.makeText(requireContext(), requireContext().getResources().getString(R.string.passwords_mismatch), Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    private void goBack() {
        Navigation.findNavController(requireView()).popBackStack();
    }
}