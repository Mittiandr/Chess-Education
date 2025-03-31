package com.example.chesslearning.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chesslearning.model.OptionModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseRepository extends ViewModel {
    private final List<String> collections = Arrays.asList("Debuts","Tactics","Castling","Figures");
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private ArrayList<OptionModel> options = new ArrayList<>();
    public MutableLiveData<Boolean> isLoaded = new MutableLiveData<Boolean>(false);

    public ArrayList<OptionModel> getDebuts() {
        return options;
    }
    public List<String> getCollectionsName(){
        return collections;
    }

    public void getInformation() {
        options.clear();
        collections.forEach(collectionName -> {
            database.collection(collectionName).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                OptionModel option = documentSnapshot.toObject(OptionModel.class);
                                option.setId(documentSnapshot.getId());
                                options.add(option);
                            }
                            isLoaded.setValue(true);
                        } else {
                            Log.e("ERROR", task.getException().getMessage());
                        }
                    });
        });

    }
}


