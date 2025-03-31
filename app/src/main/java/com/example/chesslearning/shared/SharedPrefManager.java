package com.example.chesslearning.shared;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class SharedPrefManager {
    private SharedPreferences sharedPreferences;
    public SharedPrefManager(Context baseContext) {
        this.sharedPreferences = baseContext.getSharedPreferences("Chess Learning", Context.MODE_PRIVATE);
    }

    public boolean isUserAuthorized() {
        return sharedPreferences.getBoolean("isUserAuthorized", false);
    }

    public void userAuthorize() {
        sharedPreferences.edit().putBoolean("isUserAuthorized", true).apply();
    }

    public void userLogout() {
        sharedPreferences.edit().putBoolean("isUserAuthorized", false).apply();
    }
    public void addLearnedOption(String id){
        HashSet<String> learnedOptions = getLeanedOptions();
        learnedOptions.add(id);
        sharedPreferences.edit().putStringSet("learnedOptions",learnedOptions).apply();
    }
    private HashSet<String> getLeanedOptions(){
        return (HashSet<String>) sharedPreferences.getStringSet("learnedOptions", new HashSet<String>());
    }
    public boolean isIdInLearnedOptions(String id){
        return getLeanedOptions().contains(id);
    }
}
