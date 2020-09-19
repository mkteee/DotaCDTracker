package com.example.dotacdtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dotacdtracker.Data.Hero;
import com.example.dotacdtracker.Data.HeroMap;
import com.example.dotacdtracker.Fragments.AgiHeroFragment;
import com.example.dotacdtracker.Fragments.IntHeroFragment;
import com.example.dotacdtracker.Fragments.StrHeroFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int HEROES_ADDED = 0;
    private StrHeroFragment strDialog;
    private AgiHeroFragment agiDialog;
    private IntHeroFragment intDialog;
    ArrayList<Hero> heroes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton img_btn = findViewById(R.id.first_button);
        img_btn.setImageResource(android.R.color.transparent);
        img_btn = findViewById(R.id.second_button);
        img_btn.setImageResource(android.R.color.transparent);
        img_btn = findViewById(R.id.third_button);
        img_btn.setImageResource(android.R.color.transparent);
        img_btn = findViewById(R.id.fourth_button);
        img_btn.setImageResource(android.R.color.transparent);
        img_btn = findViewById(R.id.fifth_button);
        img_btn.setImageResource(android.R.color.transparent);
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
        ImageView icon = (ImageView) view;              //get view of hero
        String hero_name = (String) icon.getTag();      //get the hero name

        TextView hero_text;
        ImageView img;
        ImageButton img_to_be_clickable;
        ImageButton img_to_be_disabled;

        switch (HEROES_ADDED){
            case 0:
                hero_text = findViewById(R.id.first_hero);
                img = findViewById(R.id.first_hero_img);
                img_to_be_clickable = findViewById(R.id.first_button);
                img_to_be_disabled = findViewById(R.id.fifth_button);
                break;
            case 1:
                hero_text = findViewById(R.id.second_hero);
                img = findViewById(R.id.second_hero_img);
                img_to_be_clickable = findViewById(R.id.second_button);
                img_to_be_disabled = findViewById(R.id.first_button);
                break;
            case 2:
                hero_text = findViewById(R.id.third_hero);
                img = findViewById(R.id.third_hero_img);
                img_to_be_clickable = findViewById(R.id.third_button);
                img_to_be_disabled = findViewById(R.id.second_button);
                break;
            case 3:
                hero_text = findViewById(R.id.fourth_hero);
                img = findViewById(R.id.fourth_hero_img);
                img_to_be_clickable = findViewById(R.id.fourth_button);
                img_to_be_disabled = findViewById(R.id.third_button);
                break;
            case 4:
                hero_text = findViewById(R.id.fifth_hero);
                img = findViewById(R.id.fifth_hero_img);
                img_to_be_clickable = findViewById(R.id.fifth_button);
                img_to_be_disabled = findViewById(R.id.fourth_button);
                break;
            default:
                return;
        }

        heroes.add(HeroMap.getHero(hero_name));

        //set image resource for the hero and get his name
        Resources res = getResources();
        String mDrawableName = heroes.get(HEROES_ADDED).getAvatar_name();
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        img.setImageResource(resID);
        hero_text.setText(heroes.get(HEROES_ADDED).getName());

        //disable and enable the corresponding delete buttons
        img_to_be_clickable.setEnabled(true);
        img_to_be_clickable.setImageResource(android.R.drawable.ic_delete);
        img_to_be_disabled.setImageResource(android.R.color.transparent);
        img_to_be_disabled.setEnabled(false);

        //close dialog and increment HEROES_ADDED
        if(strDialog != null) strDialog.dismiss();
        if(agiDialog != null) agiDialog.dismiss();
        if(intDialog != null) intDialog.dismiss();
        HEROES_ADDED++;
    }

    public void deleteHero(View view){
        TextView text;
        ImageView img;
        ImageView img_to_be_disabled;
        ImageView img_to_be_enabled;
        switch (view.getId()){
            case R.id.first_button:
                text = findViewById(R.id.first_hero);
                text.setText(R.string.first_hero);
                img = findViewById(R.id.first_hero_img);
                img.setImageResource(android.R.drawable.ic_menu_crop);

                img_to_be_disabled = findViewById(R.id.first_button);
                img_to_be_disabled.setImageResource(android.R.color.transparent);
                img_to_be_disabled.setEnabled(false);
                break;
            case R.id.second_button:
                text = findViewById(R.id.second_hero);
                text.setText(R.string.second_hero);
                img = findViewById(R.id.second_hero_img);
                img.setImageResource(android.R.drawable.ic_menu_crop);

                img_to_be_disabled = findViewById(R.id.second_button);
                img_to_be_disabled.setImageResource(android.R.color.transparent);
                img_to_be_disabled.setEnabled(false);
                img_to_be_enabled = findViewById(R.id.first_button);
                img_to_be_enabled.setImageResource(android.R.drawable.ic_delete);
                img_to_be_enabled.setEnabled(true);

                break;
            case R.id.third_button:
                text = findViewById(R.id.third_hero);
                text.setText(R.string.third_hero);
                img = findViewById(R.id.third_hero_img);
                img.setImageResource(android.R.drawable.ic_menu_crop);

                img_to_be_disabled = findViewById(R.id.third_button);
                img_to_be_disabled.setImageResource(android.R.color.transparent);
                img_to_be_disabled.setEnabled(false);
                img_to_be_enabled = findViewById(R.id.second_button);
                img_to_be_enabled.setImageResource(android.R.drawable.ic_delete);
                img_to_be_enabled.setEnabled(true);

                break;
            case R.id.fourth_button:
                text = findViewById(R.id.fourth_hero);
                text.setText(R.string.fourth_hero);
                img = findViewById(R.id.fourth_hero_img);
                img.setImageResource(android.R.drawable.ic_menu_crop);

                img_to_be_disabled = findViewById(R.id.fourth_button);
                img_to_be_disabled.setImageResource(android.R.color.transparent);
                img_to_be_disabled.setEnabled(false);
                img_to_be_enabled = findViewById(R.id.third_button);
                img_to_be_enabled.setImageResource(android.R.drawable.ic_delete);
                img_to_be_enabled.setEnabled(true);

                break;
            case R.id.fifth_button:
                text = findViewById(R.id.fifth_hero);
                text.setText(R.string.fifth_hero);
                img = findViewById(R.id.fifth_hero_img);
                img.setImageResource(android.R.drawable.ic_menu_crop);

                img_to_be_disabled = findViewById(R.id.fifth_button);
                img_to_be_disabled.setImageResource(android.R.color.transparent);
                img_to_be_disabled.setEnabled(false);
                img_to_be_enabled = findViewById(R.id.fourth_button);
                img_to_be_enabled.setImageResource(android.R.drawable.ic_delete);
                img_to_be_enabled.setEnabled(true);

                break;
            default:
                return;
        }
        heroes.remove(heroes.size()-1);
        HEROES_ADDED--;
    }
    public void onContinueClicked(View view){

        if(heroes.get(0) == null) return;

        Intent intent = new Intent(this, TrackerActivity.class);
        String [] heroes_string = new String[HEROES_ADDED];
        for(int i=0; i<HEROES_ADDED; i++) {
            heroes_string[i] = heroes.get(i).getId();
        }
        intent.putExtra("heroes_string", heroes_string);
        startActivity(intent);
    }
}