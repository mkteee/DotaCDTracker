package com.example.dotacdtracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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
    CountDownTimer[] cdTimer = new CountDownTimer[8];
    boolean[] hasStarted = new boolean[7];  //1-5 spells -- 6 for aegis -- 7 for neutral items
    int roshanState = 0;                    //separated because spawn-range

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
        initializeLayout();
    }

    public void onSpellClicked(View view){
        TextView txt;
        ImageView indicator;

        switch(view.getId()){
            case R.id.first_spell:
            case R.id.spell_btn1:
                if(hasStarted[0]){
                    cdTimer[0].cancel();
                    hasStarted[0] = false;
                }
                txt = findViewById(R.id.first_spell_cd);
                indicator = findViewById(R.id.first_indicator);
                startCountDown(txt, indicator, 0);
                break;
            case R.id.second_spell:
            case R.id.spell_btn2:
                if(hasStarted[1]){
                    cdTimer[1].cancel();
                    hasStarted[1] = false;
                }
                txt = findViewById(R.id.second_spell_cd);
                indicator = findViewById(R.id.second_indicator);
                startCountDown(txt, indicator, 1);
                break;
            case R.id.third_spell:
            case R.id.spell_btn3:
                if(hasStarted[2]){
                    cdTimer[2].cancel();
                    hasStarted[2] = false;
                }
                txt = findViewById(R.id.third_spell_cd);
                indicator = findViewById(R.id.third_indicator);
                startCountDown(txt, indicator, 2);
                break;
            case R.id.fourth_spell:
            case R.id.spell_btn4:
                if(hasStarted[3]){
                    cdTimer[3].cancel();
                    hasStarted[3] = false;
                }
                txt = findViewById(R.id.fourth_spell_cd);
                indicator = findViewById(R.id.fourth_indicator);
                startCountDown(txt, indicator,3);
                break;
            case R.id.fifth_spell:
            case R.id.spell_btn5:
                if(hasStarted[4]){
                    cdTimer[4].cancel();
                    hasStarted[4] = false;
                }
                txt = findViewById(R.id.fifth_spell_cd);
                indicator = findViewById(R.id.fifth_indicator);
                startCountDown(txt, indicator,4);
                break;
        }
    }

    public void onNeutralClicked(View view){

    }

    /**
     * Roshan timer, which spawns between 8-11 Minutes. Timer will run from 8 to 0 and turn the
     * indicator yellow, which means a spawn is possible. Then it will run a timer from 3 to 0 and
     * turn green afterwards.
     * @param view image of roshan
     */
    public void onRoshanClicked(View view){
        final TextView field = findViewById(R.id.roshan_cd);
        final ImageView indicator = findViewById(R.id.roshan_indicator);
        onAegisClicked(findViewById(R.id.aegis));   //automatically assume that aegis is picked up

        if(roshanState > 1){
            cdTimer[6].cancel();
            roshanState = 0;
        }
        if(roshanState == 0) {
            roshanState = 1;
            indicator.setImageResource(R.drawable.ic_spell_not_ready);
            cdTimer[6] = new CountDownTimer(480000, 995) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    roshanState = 1;
                    field.setText("" + (int) (millisUntilFinished / 1000));
                }
                @Override
                public void onFinish() {
                    field.setText(getString(R.string.spell_ready));
                    roshanState = 2;
                    indicator.setImageResource(R.drawable.ic_spell_maybe);

                    //start other timer
                    startRoshanSpawnRangeTimer();
                }
            }.start();
        }
    }

    public void startRoshanSpawnRangeTimer(){
        
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

    public void startCountDown(final TextView field, final ImageView indicator, final int spell_number){
        final Spell spell = spells.get(spell_number);
        final Hero hero = heroes.get(spell_number);

        float cooldown = spell.getCooldown();
        float lvl_red = spell.getLvl_reduction()[spell.getCurr_lvl()];
        float talent_red = 0;
        float aghs_red = 0;
        boolean hasOctarine = spell.hasOctarine();
        boolean hasQuickening = spell.hasQuickening();
        boolean hasPrism = spell.hasPrism();

        if(hero.isAghs_Active()) aghs_red = spell.getAghs_reduction();
        if(hero.isTalent_Active()) talent_red = spell.getTalent_reduction();

        float octarine = 0;
        float neutral = 0;
        if(hasQuickening) neutral = 0.13f;      //13% for quickening
        if(hasPrism) neutral = 0.2f;            //20% for prism
        if(hasOctarine) octarine = 0.25f;       //25% for octarine
        cooldown = cooldown - lvl_red;          //subtract lvl based cd

        //calculate the actual cooldown after boni and reductions
        long calc_cooldown = 1000 * (long) cooldownCalc(cooldown, talent_red, octarine, neutral, aghs_red);

        /*
         * Color the indicator red and start the CountDownTimer to start the cooldown
         */
        indicator.setImageResource(R.drawable.ic_spell_not_ready);
        cdTimer[spell_number] = new CountDownTimer(calc_cooldown, 995) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                hasStarted[spell_number] = true;
                field.setText(""+(int)(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                field.setText(getString(R.string.spell_ready));
                hasStarted[spell_number] = false;
                indicator.setImageResource(R.drawable.ic_spell_ready);
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
        cooldown = cooldown - talent_flat - aghs;
        return (cooldown * (1- talent_percent) * (1-octarine) * (1-neutral));
    }

    public void initializeLayout(){
        int curr_hero = 0;

        ImageView[] spell_img1 = {findViewById(R.id.first_spell), findViewById(R.id.second_spell),
        findViewById(R.id.third_spell), findViewById(R.id.fourth_spell), findViewById(R.id.fifth_spell)};
        ImageView[] spell_img2 = {findViewById(R.id.spell_btn1), findViewById(R.id.spell_btn2),
        findViewById(R.id.spell_btn3), findViewById(R.id.spell_btn4), findViewById(R.id.spell_btn5)};

        ImageView[] indicators = {findViewById(R.id.first_indicator), findViewById(R.id.second_indicator),
        findViewById(R.id.third_indicator), findViewById(R.id.fourth_indicator), findViewById(R.id.fifth_indicator)};
        TextView [] spell_cd = {findViewById(R.id.first_spell_cd), findViewById(R.id.second_spell_cd),
        findViewById(R.id.third_spell_cd), findViewById(R.id.fourth_spell_cd), findViewById(R.id.fifth_spell_cd)};

        ImageView[] aghs_pic = {findViewById(R.id.aghs1_pic), findViewById(R.id.aghs2_pic),
        findViewById(R.id.aghs3_pic), findViewById(R.id.aghs4_pic), findViewById(R.id.aghs5_pic)};
        ImageView[] talent_pic = {findViewById(R.id.talent1_pic), findViewById(R.id.talent2_pic),
        findViewById(R.id.talent3_pic), findViewById(R.id.talent4_pic), findViewById(R.id.talent5_pic)};

        RadioButton [] aghs_btn = {findViewById(R.id.aghs1), findViewById(R.id.aghs2), findViewById(R.id.aghs3),
        findViewById(R.id.aghs4), findViewById(R.id.aghs5)};
        RadioButton [] talent_btn = {findViewById(R.id.talent1), findViewById(R.id.talent2),
        findViewById(R.id.talent3), findViewById(R.id.talent4), findViewById(R.id.talent5)};
        Button [] lvl_btn = {findViewById(R.id.lvl1), findViewById(R.id.lvl2), findViewById(R.id.lvl3),
        findViewById(R.id.lvl4), findViewById(R.id.lvl5)};
        Button [] more_btn = {findViewById(R.id.options1), findViewById(R.id.options2),
        findViewById(R.id.options3), findViewById(R.id.options4), findViewById(R.id.options5)};

        Resources res = getResources();

        //view.setColorFilter(Color.argb(150,200,200,200));
        for(Hero hero : heroes){
            String mDrawableName = heroes.get(curr_hero).getIcon_name();
            int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
            spell_img1[curr_hero].setImageResource(resID);
            spell_img2[curr_hero].setImageResource(resID);

            if(!hero.hasTalent()) {
                talent_pic[curr_hero].setColorFilter(Color.argb(100,250,100,100));
                talent_btn[curr_hero].setEnabled(false);
            }
            if(!hero.hasAghs()) {
                aghs_pic[curr_hero].setColorFilter(Color.argb(100,250,100,100));
                aghs_btn[curr_hero].setEnabled(false);
            }
            curr_hero++;
        }
        while(curr_hero<5){
            spell_img1[curr_hero].setEnabled(false);
            spell_img1[curr_hero].setColorFilter(Color.argb(250,200,200,200));
            spell_img2[curr_hero].setImageResource(0);
            spell_img2[curr_hero].setEnabled(false);
            indicators[curr_hero].setImageResource(0);
            spell_cd[curr_hero].setText("");
            aghs_pic[curr_hero].setImageResource(0);
            aghs_btn[curr_hero].setVisibility(View.GONE);
            talent_pic[curr_hero].setImageResource(0);
            talent_btn[curr_hero].setVisibility(View.GONE);
            lvl_btn[curr_hero].setEnabled(false);
            lvl_btn[curr_hero].setVisibility(View.GONE);
            more_btn[curr_hero].setEnabled(false);
            more_btn[curr_hero].setVisibility(View.GONE);
            curr_hero++;
        }
    }
}
