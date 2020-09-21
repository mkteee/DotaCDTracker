package com.example.dotacdtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dotacdtracker.Data.Hero;
import com.example.dotacdtracker.Data.HeroMap;
import com.example.dotacdtracker.Data.Spell;

import java.util.ArrayList;

public class TrackerActivity extends AppCompatActivity {

    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<Spell> spells = new ArrayList<>();
    CountDownTimer[] cdTimer = new CountDownTimer[5];
    boolean[] hasStarted = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        Intent intent = getIntent();
        String [] heroes_string = intent.getStringArrayExtra("heroes_string");
        assert heroes_string != null;
        for(String hero : heroes_string){
            heroes.add(HeroMap.getHero(hero));
        }
        for(Hero hero : heroes) {
            spells.add(new Spell(hero.getSpell(), hero.getCooldown(), hero.getLvl_reduction(),
                    hero.getTalent_reduction(), hero.getAghs_reduction(), hero.getIcon_name()));


        }
    }

    public void onSpellClicked(View view){
        TextView txt;

        switch(view.getId()){
            case R.id.first_spell:
            case R.id.spell_btn1:
                if(hasStarted[0]){
                    cdTimer[0].cancel();
                    hasStarted[0] = false;
                }
                txt = findViewById(R.id.first_spell_cd);
                startCountDown(txt, 0);
                break;
            case R.id.second_spell:
            case R.id.spell_btn2:

            case R.id.third_spell:
            case R.id.spell_btn3:

            case R.id.fourth_spell:
            case R.id.spell_btn4:

            case R.id.fifth_spell:
            case R.id.spell_btn5:

        }
    }

    public void onNeutralClicked(View view){

    }

    public void onRoshanClicked(View view){

    }

    public void onAegisClicked(View view){

    }

    public void onOkClicked(View view){

    }

    public void onRetroClicked(View view){

    }

    public void onResetClicked(View view){

    }

    public void onLvlClicked(View view){

    }

    public void startCountDown(final TextView field, final int spell_number){
        final Spell spell = spells.get(spell_number);
        final Hero hero = heroes.get(spell_number);

        float cooldown = spell.getCooldown();
        float lvl_red = spell.getLvl_reduction()[spell.getCurr_lvl()];
        float talent_red = 0;
        float aghs_red = 0;
        boolean hasOctarine = spell.hasOctarine();
        boolean hasQuickening = spell.hasQuickening();
        boolean hasPrism = spell.hasPrism();

        if(hero.hasAghs()) aghs_red = spell.getAghs_reduction();
        if(hero.hasTalent()) talent_red = spell.getTalent_reduction();

        float octarine = 0;
        float neutral = 0;
        if(hasQuickening) neutral = 0.13f;      //13% for quickening
        if(hasPrism) neutral = 0.2f;            //20% for prism
        if(hasOctarine) octarine = 0.25f;       //25% for octarine
        cooldown = cooldown - lvl_red;          //subtract lvl based cd

        long calc_cooldown = 1000 * (long) cooldownCalc(cooldown, talent_red, octarine, neutral, aghs_red);

        cdTimer[spell_number] = new CountDownTimer(calc_cooldown, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                hasStarted[spell_number] = true;
                field.setText(""+(double)(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                field.setText(getString(R.string.spell_ready));
                hasStarted[spell_number] = false;
            }
        }.start();

    }

    public float cooldownCalc(float cooldown, float talent, float octarine, float neutral,
                             float aghs){
        float talent_flat = 0;
        float talent_percent = 0;

        if(talent < 1){
            talent_percent = talent;
        }else{
            talent_flat = talent;
        }

        return (cooldown * (1- talent_percent) * (1-octarine) * (1-neutral)
                - talent_flat - aghs);
    }
}
