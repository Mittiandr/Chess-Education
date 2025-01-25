package com.example.chesslearning.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chesslearning.model.DebutModel;
import com.example.chesslearning.model.StepModel;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FirebaseRepository extends ViewModel {
    private FirebaseFirestore database= FirebaseFirestore.getInstance();
    private ArrayList<DebutModel> debuts= new ArrayList<>();
    private ArrayList<StepModel> steps=new ArrayList<>();
    public MutableLiveData<Boolean> isLoaded= new MutableLiveData<Boolean>(false);

    public ArrayList<DebutModel> getDebuts() {
        return debuts;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }
    public void getInformation(){
        debuts.clear();
        steps.clear();
        database.collection("Debuts").get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        database.collection("Steps").get()
                                .addOnCompleteListener(stepTask -> {
                                    if (stepTask.isSuccessful()){
                                        for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                            DebutModel debut = documentSnapshot.toObject(DebutModel.class);
                                            debut.setId(documentSnapshot.getId());
                                            debuts.add(debut);
                                        }
                                        for(DocumentSnapshot documentSnapshot: stepTask.getResult().getDocuments()){
                                            StepModel step = documentSnapshot.toObject(StepModel.class);
                                            steps.add(step);
                                        }
                                        isLoaded.setValue(true);
                                    }
                                    else {
                                        Log.e("ERROR", stepTask.getException().getMessage());
                                    }
                                });
                    }
                    else{
                        Log.e("ERROR", task.getException().getMessage());
                    }
                });
    }

}
