package com.example.chesslearning.main;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Boolean> navBarVisibility= new MutableLiveData<Boolean>(false);
    public Boolean isNavBarVisible(){
            return navBarVisibility.getValue();
    }
}
