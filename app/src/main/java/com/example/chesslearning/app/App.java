package com.example.chesslearning.app;

import android.app.Application;
import android.content.Context;

import com.example.chesslearning.shared.SharedPrefManager;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;

public class App extends Application {
        public static SharedPrefManager sharedManager;
        public static Context appContext;

        public void onCreate(){
            super.onCreate();
            appContext = getApplicationContext();
            sharedManager = new SharedPrefManager(getBaseContext());
            FirebaseApp.initializeApp(this);
        }
}
