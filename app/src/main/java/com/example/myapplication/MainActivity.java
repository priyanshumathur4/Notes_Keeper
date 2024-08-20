package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NotesFragment.OnNoteUpdateListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the appropriate fragment based on login status
        if (savedInstanceState == null) {
            loadFragmentBasedOnLoginStatus();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the fragment based on login status when resuming
        loadFragmentBasedOnLoginStatus();
    }

    private void loadFragmentBasedOnLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("NotesAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("logged_in", false);

        if (isLoggedIn) {
            // Load NotesFragment if logged in
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new NotesFragment())
                    .commit();
        } else {
            // Load LoginFragment if not logged in
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void onNoteUpdated() {
        // Find the NotesFragment and update it
        NotesFragment fragment = (NotesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.updateNotes();
            Log.d("MainActivity", "Note updated in NotesFragment");
        } else {
            Log.d("MainActivity", "NotesFragment not found");
        }
    }
}
