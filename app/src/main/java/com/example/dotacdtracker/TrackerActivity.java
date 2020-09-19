package com.example.dotacdtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dotacdtracker.Data.Hero;
import com.example.dotacdtracker.Data.HeroMap;

import java.util.ArrayList;

public class TrackerActivity extends AppCompatActivity {

    ArrayList<Hero> heroes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        Intent intent = getIntent();
        String [] heroes_string = intent.getStringArrayExtra("heroes_string");
        for(String hero : heroes_string){
            heroes.add(HeroMap.getHero(hero));
        }
    }

    public void onSpellClicked(View view){

    }

    public void onNeutralClicked(View view){

    }

    public void onRoshanClicked(View view){

    }

    public void onAegisClicked(View view){

    }

}