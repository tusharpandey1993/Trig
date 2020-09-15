package com.trig.trigapp;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.navHostFragment);
        toolbar = findViewById(R.id.toolbar);
        // Set up ActionBar
//        setSupportActionBar(toolbar);

      /*  getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new LoginFragment(), LoginFragment.class.getSimpleName())
                .commit();*/
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }*/
}
