package com.example.dotacdtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
        FragmentManager fm = getSupportFragmentManager();
        AgiHeroFragment dialog = AgiHeroFragment.newInstance();
        dialog.show(fm, "agi_dialog");
    }
    public void addInt(View view){
        FragmentManager fm = getSupportFragmentManager();
        IntHeroFragment dialog = IntHeroFragment.newInstance();
        dialog.show(fm, "int_dialog");
    }
    public void heroClicked(View view){
        ImageView icon = (ImageView) view;
        String hero_name = (String) icon.getTag();
        Log.i("deb", hero_name);
    }
}