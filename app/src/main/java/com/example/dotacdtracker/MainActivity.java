package com.example.dotacdtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int HEROES_ADDED = 0;
    private StrHeroFragment strDialog;
    private AgiHeroFragment agiDialog;
    private IntHeroFragment intDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addStr(View view){
        FragmentManager fm = getSupportFragmentManager();
        strDialog = StrHeroFragment.newInstance();
        strDialog.show(fm, "str_dialog");
    }
    public void addAgi(View view){
        FragmentManager fm = getSupportFragmentManager();
        agiDialog = AgiHeroFragment.newInstance();
        agiDialog.show(fm, "agi_dialog");
    }
    public void addInt(View view){
        FragmentManager fm = getSupportFragmentManager();
        intDialog = IntHeroFragment.newInstance();
        intDialog.show(fm, "int_dialog");
    }
    public void heroClicked(View view){
        ImageView icon = (ImageView) view;
        String hero_name = (String) icon.getTag();
        Hero hero = HeroMap.getHero(hero_name);
        TextView hero_text;
        ImageView img = findViewById(R.id.first_hero_img);

        Resources res = getResources();
        String mDrawableName = hero.getAvatar_name();
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());

        switch (HEROES_ADDED){
            case 0:
                hero_text = findViewById(R.id.first_hero);
                break;
            case 1:
                hero_text = findViewById(R.id.second_hero);
                img = findViewById(R.id.second_hero_img);
                break;
            case 2:
                hero_text = findViewById(R.id.third_hero);
                img = findViewById(R.id.third_hero_img);
                break;
            case 3:
                hero_text = findViewById(R.id.fourth_hero);
                img = findViewById(R.id.fourth_hero_img);
                break;
            case 4:
                hero_text = findViewById(R.id.fifth_hero);
                img = findViewById(R.id.fifth_hero_img);
                break;
            default:
                return;
        }
        img.setImageResource(resID);
        hero_text.setText(hero.getName());
        if(strDialog != null) strDialog.dismiss();
        if(agiDialog != null) agiDialog.dismiss();
        if(intDialog != null) intDialog.dismiss();
    }
}