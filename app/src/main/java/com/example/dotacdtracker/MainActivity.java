package com.example.dotacdtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addStr(View view){
        FragmentManager fm = getSupportFragmentManager();
        StrHeroFragment dialog = StrHeroFragment.newInstance();
        dialog.show(fm, "str_dialog");
    }
    public void addAgi(View view){
    }
    public void addInt(View view){
    }
}