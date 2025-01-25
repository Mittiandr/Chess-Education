package com.example.chesslearning.shared;

import android.content.Context;
import android.content.SharedPreferences;

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
}
