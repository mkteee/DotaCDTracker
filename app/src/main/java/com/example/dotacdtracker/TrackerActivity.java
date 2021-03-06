package com.example.dotacdtracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dotacdtracker.Data.Hero;
import com.example.dotacdtracker.Data.HeroMap;
import com.example.dotacdtracker.Data.Spell;
import com.example.dotacdtracker.Fragments.OptionsFragment;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * TrackerActivity which is the main screen while you are in-game. Will show every important
 * spell for each hero and give you options to lvl up or assign neutral items etc. to the
 * wanted spell. Cooldowns should consider their respective chosen options to correctly calculate
 * and present the cooldown. Can be reset by clicking again or using the more button.
 * Also has a tracker for Roshan, Aegis and Neutral Items.
 */
public class TrackerActivity extends AppCompatActivity {

    private OptionsFragment optionsDialog;
    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<Spell> spells = new ArrayList<>();
    CountDownTimer[] cdTimer = new CountDownTimer[8];
    long [] timeLeft = new long[5];
    long [] pauseMillis = new long[3];      //1 roshan -- 2 aegis -- 3 neutral
    boolean[] hasStarted = new boolean[8];  //1-5 spells -- 6 roshan -- 7 aegis -- 8 neutral
    int currHero = 0;
    boolean roshanSecondTimer = false;

    /**
     * After calling super it will extract the Hero objects given through their id.
     * Will also initialize the layout by calling initializeLayout.
     * @param savedInstanceState saved state
     */
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

    /**
     * Handler for the clicking on spells. Starts the cooldown for the corresponding spell by
     * calling the startCountDown for the clicked spell.
     * @param view spell_img which was clicked
     */
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
                startSpellCountDown(txt, indicator, 0, 0);
                break;
            case R.id.second_spell:
            case R.id.spell_btn2:
                if(hasStarted[1]){
                    cdTimer[1].cancel();
                    hasStarted[1] = false;
                }
                txt = findViewById(R.id.second_spell_cd);
                indicator = findViewById(R.id.second_indicator);
                startSpellCountDown(txt, indicator, 1, 0);
                break;
            case R.id.third_spell:
            case R.id.spell_btn3:
                if(hasStarted[2]){
                    cdTimer[2].cancel();
                    hasStarted[2] = false;
                }
                txt = findViewById(R.id.third_spell_cd);
                indicator = findViewById(R.id.third_indicator);
                startSpellCountDown(txt, indicator, 2, 0);
                break;
            case R.id.fourth_spell:
            case R.id.spell_btn4:
                if(hasStarted[3]){
                    cdTimer[3].cancel();
                    hasStarted[3] = false;
                }
                txt = findViewById(R.id.fourth_spell_cd);
                indicator = findViewById(R.id.fourth_indicator);
                startSpellCountDown(txt, indicator,3, 0);
                break;
            case R.id.fifth_spell:
            case R.id.spell_btn5:
                if(hasStarted[4]){
                    cdTimer[4].cancel();
                    hasStarted[4] = false;
                }
                txt = findViewById(R.id.fifth_spell_cd);
                indicator = findViewById(R.id.fifth_indicator);
                startSpellCountDown(txt, indicator,4, 0);
                break;
        }
    }

    public void onPauseClicked(View view){
        Button btn = (Button) view;
        if(btn.getText().toString().equals("Pause")){
            for(int i=0; i<8; i++){
                if(hasStarted[i]){
                    cdTimer[i].cancel();
                }
            }
            btn.setText(getString(R.string.resume));
            btn.setBackgroundColor(Color.parseColor("#1ac93a"));
        }else{
            for(int i=0; i<8; i++){
                final TextView field;
                final ImageView indicator;
                if(hasStarted[i]){
                    switch (i){
                        case 0:
                            field = findViewById(R.id.first_spell_cd);
                            indicator = findViewById(R.id.first_indicator);
                            startSpellCountDown(field, indicator, 0, timeLeft[0]/1000);
                            break;
                        case 1:
                            field = findViewById(R.id.second_spell_cd);
                            indicator = findViewById(R.id.second_indicator);
                            startSpellCountDown(field, indicator, 0, timeLeft[1]/1000);
                            break;
                        case 2:
                            field = findViewById(R.id.third_spell_cd);
                            indicator = findViewById(R.id.third_indicator);
                            startSpellCountDown(field, indicator, 0, timeLeft[2]/1000);
                            break;
                        case 3:
                            field = findViewById(R.id.fourth_spell_cd);
                            indicator = findViewById(R.id.fourth_indicator);
                            startSpellCountDown(field, indicator, 0, timeLeft[3]/1000);
                            break;
                        case 4:
                            field = findViewById(R.id.fifth_spell_cd);
                            indicator = findViewById(R.id.fifth_indicator);
                            startSpellCountDown(field, indicator, 0, timeLeft[4]/1000);
                            break;
                        case 5:
                            field = findViewById(R.id.roshan_cd);
                            indicator = findViewById(R.id.roshan_indicator);

                            if(!roshanSecondTimer) {
                                cdTimer[5] = new CountDownTimer(pauseMillis[0], 995) {
                                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        field.setText("Not up " + String.format("%d:%02d ",
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        pauseMillis[0] = millisUntilFinished;
                                    }

                                    @Override
                                    public void onFinish() {
                                        indicator.setImageResource(R.drawable.ic_spell_maybe);

                                        //start other timer
                                        startRoshanSpawnRangeTimer();
                                    }
                                }.start();
                            }else{
                                cdTimer[5] = new CountDownTimer(180000-pauseMillis[0], 995) {
                                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        field.setText("Maybe up "+String.format("%d:%02d ",
                                                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        pauseMillis[0] = millisUntilFinished;
                                    }
                                    @Override
                                    public void onFinish() {
                                        field.setText(getString(R.string.roshan_up));
                                        indicator.setImageResource(R.drawable.ic_spell_maybe);
                                        hasStarted[5] = false;
                                    }
                                }.start();

                            }
                            break;
                        case 6:
                            field = findViewById(R.id.aegis_cd);
                            indicator = findViewById(R.id.aegis_indicator);
                            hasStarted[6] = true;
                            cdTimer[6] = new CountDownTimer(pauseMillis[1], 995) {
                                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    field.setText(""+String.format("%d:%02d ",
                                            TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                    pauseMillis[1] = millisUntilFinished;
                                }
                                @Override
                                public void onFinish() {
                                    field.setText(getString(R.string.aegis_down));
                                    indicator.setImageResource(R.drawable.ic_spell_ready);
                                    hasStarted[6] = false;
                                }
                            }.start();
                            break;
                        case 7:
                    }
                }
            }
            btn.setText(getString(R.string.pause));
            btn.setBackgroundColor(Color.parseColor("#ffff4444"));
        }

    }

    /**
     * Neutral item timer. Destroys current timer and sets a new one after next tier is reached.
     * Timings are:
     * 7min-17min  : tier1 (420.000milli)
     * 17min-27min : tier2 (600.000milli)
     * 27min-37min : tier3 (600.000milli)
     * 37min-60min : tier4 (600.000milli)
     * 60min+      : tier5 (1.380.000milli)
     * @param view one of three neutral buttons
     */
    public void onNeutralClicked(View view){
        if(hasStarted[7]){
            cdTimer[7].cancel();
            ((ImageView) findViewById(R.id.tier1_indicator)).setImageResource(R.drawable.ic_spell_not_ready);
            ((ImageView) findViewById(R.id.tier2_indicator)).setImageResource(R.drawable.ic_spell_not_ready);
            ((ImageView) findViewById(R.id.tier3_indicator)).setImageResource(R.drawable.ic_spell_not_ready);
            ((ImageView) findViewById(R.id.tier4_indicator)).setImageResource(R.drawable.ic_spell_not_ready);
            ((ImageView) findViewById(R.id.tier5_indicator)).setImageResource(R.drawable.ic_spell_not_ready);
        }
        switch (view.getId()){
            case R.id.neutral_button1:
                startNeutralTimer(460000, (ImageView) findViewById(R.id.tier1_indicator));
                break;
            case R.id.neutral_button2:
                startNeutralTimer(440000, (ImageView) findViewById(R.id.tier1_indicator));
        }
    }

    /**
     * Helping Method to start the timer for neutral items. Needs the current indicator and will
     * pass the according millis to the next timer until all items have dropped.
     * @param millis passed millis depending on current tier of items
     * @param indicator indicator which should turn green after this cycle is over
     */
    public void startNeutralTimer(int millis, final ImageView indicator){
        final TextView field = findViewById(R.id.neutral_timer);

        hasStarted[7] = true;
        cdTimer[7] = new CountDownTimer(millis, 995) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {
                field.setText("Next item in "+String.format("%d:%02d ",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                pauseMillis[2] = millisUntilFinished;
            }
            @Override
            public void onFinish() {
                indicator.setImageResource(R.drawable.ic_spell_ready);
                switch (indicator.getId()){
                    case R.id.tier1_indicator:
                        startNeutralTimer(600000, (ImageView) findViewById(R.id.tier2_indicator));
                        break;
                    case R.id.tier2_indicator:
                        startNeutralTimer(600000, (ImageView) findViewById(R.id.tier3_indicator));
                        break;
                    case R.id.tier3_indicator:
                        startNeutralTimer(600000, (ImageView) findViewById(R.id.tier4_indicator));
                        break;
                    case R.id.tier4_indicator:
                        startNeutralTimer(1380000, (ImageView) findViewById(R.id.tier5_indicator));
                        break;
                    case R.id.tier5_indicator:
                        field.setText(getString(R.string.dropped));
                }
            }
        }.start();
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

        if(hasStarted[5]) cdTimer[5].cancel();

        indicator.setImageResource(R.drawable.ic_spell_not_ready);
        hasStarted[5] = true;
        roshanSecondTimer = false;
        cdTimer[5] = new CountDownTimer(480000, 995) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {
                field.setText("Not up "+String.format("%d:%02d ",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                pauseMillis[0] = millisUntilFinished;
            }
            @Override
            public void onFinish() {
                indicator.setImageResource(R.drawable.ic_spell_maybe);

                //start other timer
                startRoshanSpawnRangeTimer();
                }
        }.start();

        //assume aegis timer starts as well
        onAegisClicked(findViewById(R.id.aegis));

    }

    public void startRoshanSpawnRangeTimer(){
        final TextView field = findViewById(R.id.roshan_cd);
        final ImageView indicator = findViewById(R.id.roshan_indicator);
        roshanSecondTimer = true;
        cdTimer[5] = new CountDownTimer(180000, 995) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {
                field.setText("Maybe up "+String.format("%d:%02d ",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                pauseMillis[0] = millisUntilFinished;
            }
            @Override
            public void onFinish() {
                field.setText(getString(R.string.roshan_up));
                indicator.setImageResource(R.drawable.ic_spell_maybe);
                hasStarted[5] = false;
            }
        }.start();
        
    }

    public void onAegisClicked(View view){
        final TextView field = findViewById(R.id.aegis_cd);
        final ImageView indicator = findViewById(R.id.aegis_indicator);

        if(hasStarted[6]) cdTimer[6].cancel();

        indicator.setImageResource(R.drawable.ic_spell_not_ready);
        hasStarted[6] = true;
        cdTimer[6] = new CountDownTimer(300000, 995) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {
                field.setText(""+String.format("%d:%02d ",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                pauseMillis[1] = millisUntilFinished;
            }
            @Override
            public void onFinish() {
                field.setText(getString(R.string.aegis_down));
                indicator.setImageResource(R.drawable.ic_spell_ready);
                hasStarted[6] = false;
            }
        }.start();
    }

    public void onMoreClicked(View view){
        Bundle args = new Bundle();
        switch (view.getId()){
            case R.id.options1:
                args.putBoolean("lvl", heroes.get(0).hasLvl());
                args.putBoolean("aghs", heroes.get(0).hasAghs());
                args.putBoolean("talent", heroes.get(0).hasTalent());
                args.putInt("curr_lvl", spells.get(0).getCurr_lvl());
                args.putBoolean("octarine", spells.get(0).hasOctarine());
                args.putBoolean("quickening", spells.get(0).hasQuickening());
                args.putBoolean("prism", spells.get(0).hasPrism());
                args.putBoolean("curr_aghs", spells.get(0).hasAghs());
                args.putBoolean("curr_talent", spells.get(0).hasTalent());
                currHero = 0;
                break;
            case R.id.options2:
                args.putBoolean("lvl", heroes.get(1).hasLvl());
                args.putBoolean("aghs", heroes.get(1).hasAghs());
                args.putBoolean("talent", heroes.get(1).hasTalent());
                args.putInt("curr_lvl", spells.get(1).getCurr_lvl());
                args.putBoolean("octarine", spells.get(1).hasOctarine());
                args.putBoolean("quickening", spells.get(1).hasQuickening());
                args.putBoolean("prism", spells.get(1).hasPrism());
                args.putBoolean("curr_aghs", spells.get(1).hasAghs());
                args.putBoolean("curr_talent", spells.get(1).hasTalent());
                currHero = 1;
                break;
            case R.id.options3:
                args.putBoolean("lvl", heroes.get(2).hasLvl());
                args.putBoolean("aghs", heroes.get(2).hasAghs());
                args.putBoolean("talent", heroes.get(2).hasTalent());
                args.putInt("curr_lvl", spells.get(2).getCurr_lvl());
                args.putBoolean("octarine", spells.get(2).hasOctarine());
                args.putBoolean("quickening", spells.get(2).hasQuickening());
                args.putBoolean("prism", spells.get(2).hasPrism());
                args.putBoolean("curr_aghs", spells.get(2).hasAghs());
                args.putBoolean("curr_talent", spells.get(2).hasTalent());
                currHero = 2;
                break;
            case R.id.options4:
                args.putBoolean("lvl", heroes.get(3).hasLvl());
                args.putBoolean("aghs", heroes.get(3).hasAghs());
                args.putBoolean("talent", heroes.get(3).hasTalent());
                args.putInt("curr_lvl", spells.get(3).getCurr_lvl());
                args.putBoolean("octarine", spells.get(3).hasOctarine());
                args.putBoolean("quickening", spells.get(3).hasQuickening());
                args.putBoolean("prism", spells.get(3).hasPrism());
                args.putBoolean("curr_aghs", spells.get(3).hasAghs());
                args.putBoolean("curr_talent", spells.get(3).hasTalent());
                currHero = 3;
                break;
            case R.id.options5:
                args.putBoolean("lvl", heroes.get(4).hasLvl());
                args.putBoolean("aghs", heroes.get(4).hasAghs());
                args.putBoolean("talent", heroes.get(4).hasTalent());
                args.putInt("curr_lvl", spells.get(4).getCurr_lvl());
                args.putBoolean("octarine", spells.get(4).hasOctarine());
                args.putBoolean("quickening", spells.get(4).hasQuickening());
                args.putBoolean("prism", spells.get(4).hasPrism());
                args.putBoolean("curr_aghs", spells.get(4).hasAghs());
                args.putBoolean("curr_talent", spells.get(4).hasTalent());
                currHero = 4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        FragmentManager fm = getSupportFragmentManager();
        optionsDialog = OptionsFragment.newInstance();
        optionsDialog.setArguments(args);
        optionsDialog.show(fm, "options_dialog");
    }

    public void onOkClicked(View view){
        Button activity_btn;
        switch (currHero){
            case 0:
                activity_btn = findViewById(R.id.lvl1);
                switch (spells.get(0).getCurr_lvl()){
                    case 0:
                        activity_btn.setText(getString(R.string.ult_lvl1));
                        break;
                    case 1:
                        activity_btn.setText(getString(R.string.ult_lvl2));
                        break;
                    case 2:
                        activity_btn.setText(getString(R.string.ult_lvl3));
                }
                break;
            case 1:
                activity_btn = findViewById(R.id.lvl2);
                switch (spells.get(1).getCurr_lvl()){
                    case 0:
                        activity_btn.setText(getString(R.string.ult_lvl1));
                        break;
                    case 1:
                        activity_btn.setText(getString(R.string.ult_lvl2));
                        break;
                    case 2:
                        activity_btn.setText(getString(R.string.ult_lvl3));
                }
                break;
            case 2:
                activity_btn = findViewById(R.id.lvl3);
                switch (spells.get(2).getCurr_lvl()){
                    case 0:
                        activity_btn.setText(getString(R.string.ult_lvl1));
                        break;
                    case 1:
                        activity_btn.setText(getString(R.string.ult_lvl2));
                        break;
                    case 2:
                        activity_btn.setText(getString(R.string.ult_lvl3));
                }
                break;
            case 3:
                activity_btn = findViewById(R.id.lvl4);
                switch (spells.get(3).getCurr_lvl()){
                    case 0:
                        activity_btn.setText(getString(R.string.ult_lvl1));
                        break;
                    case 1:
                        activity_btn.setText(getString(R.string.ult_lvl2));
                        break;
                    case 2:
                        activity_btn.setText(getString(R.string.ult_lvl3));
                }
                break;
            case 4:
                activity_btn = findViewById(R.id.lvl5);
                switch (spells.get(4).getCurr_lvl()){
                    case 0:
                        activity_btn.setText(getString(R.string.ult_lvl1));
                        break;
                    case 1:
                        activity_btn.setText(getString(R.string.ult_lvl2));
                        break;
                    case 2:
                        activity_btn.setText(getString(R.string.ult_lvl3));
                }
                break;

        }
        if(optionsDialog != null) optionsDialog.dismiss();
    }

    public void onRetroClicked(View view){
        if(hasStarted[currHero]) {
            TextView field;
            ImageView indicator;
            int spell_number = currHero;
            switch (currHero){
                case 0:
                    field = findViewById(R.id.first_spell_cd);
                    indicator = findViewById(R.id.first_indicator);
                    break;
                case 1:
                    field = findViewById(R.id.second_spell_cd);
                    indicator = findViewById(R.id.second_indicator);
                    break;
                case 2:
                    field = findViewById(R.id.third_spell_cd);
                    indicator = findViewById(R.id.third_indicator);
                    break;
                case 3:
                    field = findViewById(R.id.fourth_spell_cd);
                    indicator = findViewById(R.id.fourth_indicator);
                    break;
                case 4:
                    field = findViewById(R.id.fifth_spell_cd);
                    indicator = findViewById(R.id.fifth_indicator);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + currHero);
            }
            cdTimer[currHero].cancel();
            startSpellCountDown(field, indicator, spell_number, timeLeft[currHero]/1000);
        }
    }

    public void onAghsClicked(View view){
        switch (view.getId()){
            case R.id.aghs1:
                if(((CheckBox) view).isChecked()){
                    spells.get(0).setCurr_aghs(true);
                }else{
                    spells.get(0).setCurr_aghs(false);
                }
                break;
            case R.id.aghs2:
                if(((CheckBox) view).isChecked()){
                    spells.get(1).setCurr_aghs(true);
                }else{
                    spells.get(1).setCurr_aghs(false);
                }
                break;
            case R.id.aghs3:
                if(((CheckBox) view).isChecked()){
                    spells.get(2).setCurr_aghs(true);
                }else{
                    spells.get(2).setCurr_aghs(false);
                }
                break;
            case R.id.aghs4:
                if(((CheckBox) view).isChecked()){
                    spells.get(3).setCurr_aghs(true);
                }else{
                    spells.get(3).setCurr_aghs(false);
                }
                break;
            case R.id.aghs5:
                if(((CheckBox) view).isChecked()){
                    spells.get(4).setCurr_aghs(true);
                }else{
                    spells.get(4).setCurr_aghs(false);
                }
        }
    }

    public void onAghsClickedDialog(View view){
        CheckBox activity_aghs;
        switch(currHero){
            case 0:
                activity_aghs = findViewById(R.id.aghs1);
                if(((CheckBox) view).isChecked()){
                    spells.get(0).setCurr_aghs(true);
                    activity_aghs.setChecked(true);
                }else{
                    spells.get(0).setCurr_aghs(false);
                    activity_aghs.setChecked(false);
                }
                break;
            case 1:
                activity_aghs = findViewById(R.id.aghs2);
                if(((CheckBox) view).isChecked()){
                    spells.get(1).setCurr_aghs(true);
                    activity_aghs.setChecked(true);
                }else{
                    spells.get(1).setCurr_aghs(false);
                    activity_aghs.setChecked(false);
                }
                break;
            case 2:
                activity_aghs = findViewById(R.id.aghs3);
                if(((CheckBox) view).isChecked()){
                    spells.get(2).setCurr_aghs(true);
                    activity_aghs.setChecked(true);
                }else{
                    spells.get(2).setCurr_aghs(false);
                    activity_aghs.setChecked(false);
                }
                break;
            case 3:
                activity_aghs = findViewById(R.id.aghs4);
                if(((CheckBox) view).isChecked()){
                    spells.get(3).setCurr_aghs(true);
                    activity_aghs.setChecked(true);
                }else{
                    spells.get(3).setCurr_aghs(false);
                    activity_aghs.setChecked(false);
                }
                break;
            case 4:
                activity_aghs = findViewById(R.id.aghs5);
                if(((CheckBox) view).isChecked()){
                    spells.get(4).setCurr_aghs(true);
                    activity_aghs.setChecked(true);
                }else{
                    spells.get(4).setCurr_aghs(false);
                    activity_aghs.setChecked(false);
                }
        }
    }

    public void onTalentClickedDialog(View view){
        CheckBox activity_talent;
        switch(currHero){
            case 0:
                activity_talent = findViewById(R.id.talent1);
                if(((CheckBox) view).isChecked()){
                    spells.get(0).setCurr_talent(true);
                    activity_talent.setChecked(true);
                }else{
                    spells.get(0).setCurr_talent(false);
                    activity_talent.setChecked(false);
                }
                break;
            case 1:
                activity_talent = findViewById(R.id.talent2);
                if(((CheckBox) view).isChecked()){
                    spells.get(1).setCurr_talent(true);
                    activity_talent.setChecked(true);
                }else{
                    spells.get(1).setCurr_talent(false);
                    activity_talent.setChecked(false);
                }
                break;
            case 2:
                activity_talent = findViewById(R.id.talent3);
                if(((CheckBox) view).isChecked()){
                    spells.get(2).setCurr_talent(true);
                    activity_talent.setChecked(true);
                }else{
                    spells.get(2).setCurr_talent(false);
                    activity_talent.setChecked(false);
                }
                break;
            case 3:
                activity_talent = findViewById(R.id.talent4);
                if(((CheckBox) view).isChecked()){
                    spells.get(3).setCurr_talent(true);
                    activity_talent.setChecked(true);
                }else{
                    spells.get(3).setCurr_talent(false);
                    activity_talent.setChecked(false);
                }
                break;
            case 4:
                activity_talent = findViewById(R.id.talent5);
                if(((CheckBox) view).isChecked()){
                    spells.get(4).setCurr_talent(true);
                    activity_talent.setChecked(true);
                }else{
                    spells.get(4).setCurr_talent(false);
                    activity_talent.setChecked(false);
                }
                break;
        }
    }

    public void onQuickeningClicked(CheckBox quickening, CheckBox prism){
        switch (currHero){
            case 0:
                if(quickening.isChecked()){
                    if(prism.isChecked()){
                        prism.setChecked(false);
                        spells.get(0).setPrism(false);
                    }
                    spells.get(0).setQuickening(true);
                }else{
                    spells.get(0).setQuickening(false);
                }
                break;
            case 1:
                if(quickening.isChecked()){
                    if(prism.isChecked()){
                        prism.setChecked(false);
                        spells.get(1).setPrism(false);
                    }
                    spells.get(1).setQuickening(true);
                }else{
                    spells.get(1).setQuickening(false);
                }
                break;
            case 2:
                if(quickening.isChecked()){
                    if(prism.isChecked()){
                        prism.setChecked(false);
                        spells.get(2).setPrism(false);
                    }
                    spells.get(2).setQuickening(true);
                }else{
                    spells.get(2).setQuickening(false);
                }
                break;
            case 3:
                if(quickening.isChecked()){
                    if(prism.isChecked()){
                        prism.setChecked(false);
                        spells.get(3).setPrism(false);
                    }
                    spells.get(3).setQuickening(true);
                }else{
                    spells.get(3).setQuickening(false);
                }
                break;
            case 4:
                if(quickening.isChecked()){
                    if(prism.isChecked()){
                        prism.setChecked(false);
                        spells.get(4).setPrism(false);
                    }
                    spells.get(4).setQuickening(true);
                }else{
                    spells.get(4).setQuickening(false);
                }
        }
    }

    public void onPrismClicked(CheckBox prism, CheckBox quickening){
        switch (currHero) {
            case 0:
                if (prism.isChecked()) {
                    if (quickening.isChecked()) {
                        quickening.setChecked(false);
                        spells.get(0).setQuickening(false);
                    }
                    spells.get(0).setPrism(true);
                } else {
                    spells.get(0).setPrism(false);
                }
                break;
            case 1:
                if (prism.isChecked()) {
                    if (quickening.isChecked()) {
                        quickening.setChecked(false);
                        spells.get(1).setQuickening(false);
                    }
                    spells.get(1).setPrism(true);
                } else {
                    spells.get(1).setPrism(false);
                }
                break;
            case 2:
                if (prism.isChecked()) {
                    if (quickening.isChecked()) {
                        quickening.setChecked(false);
                        spells.get(2).setQuickening(false);
                    }
                    spells.get(2).setPrism(true);
                } else {
                    spells.get(2).setPrism(false);
                }
                break;
            case 3:
                if (prism.isChecked()) {
                    if (quickening.isChecked()) {
                        quickening.setChecked(false);
                        spells.get(3).setQuickening(false);
                    }
                    spells.get(3).setPrism(true);
                } else {
                    spells.get(3).setPrism(false);
                }
                break;
            case 4:
                if (prism.isChecked()) {
                    if (quickening.isChecked()) {
                        quickening.setChecked(false);
                        spells.get(4).setQuickening(false);
                    }
                    spells.get(4).setPrism(true);
                } else {
                    spells.get(4).setPrism(false);
                }
        }
    }

    public void onOctarineClicked(View view){
        switch (currHero){
            case 0:
                if(((CheckBox) view).isChecked()){
                    spells.get(0).setOctarine(true);
                }else{
                    spells.get(0).setOctarine(false);
                }
                break;
            case 1:
                if(((CheckBox) view).isChecked()){
                    spells.get(1).setOctarine(true);
                }else{
                    spells.get(1).setOctarine(false);
                }
                break;
            case 2:
                if(((CheckBox) view).isChecked()){
                    spells.get(2).setOctarine(true);
                }else{
                    spells.get(2).setOctarine(false);
                }
                break;
            case 3:
                if(((CheckBox) view).isChecked()){
                    spells.get(3).setOctarine(true);
                }else{
                    spells.get(3).setOctarine(false);
                }
                break;
            case 4:
                if(((CheckBox) view).isChecked()){
                    spells.get(4).setOctarine(true);
                }else{
                    spells.get(4).setOctarine(false);
                }
                break;
        }
    }


    public void onTalentClicked(View view){
        switch (view.getId()){
            case R.id.talent1:
                if(((CheckBox) view).isChecked()){
                    spells.get(0).setCurr_talent(true);
                }else{
                    spells.get(0).setCurr_talent(false);
                }
                break;
            case R.id.talent2:
                if(((CheckBox) view).isChecked()){
                    spells.get(1).setCurr_talent(true);
                }else{
                    spells.get(1).setCurr_talent(false);
                }
                break;
            case R.id.talent3:
                if(((CheckBox) view).isChecked()){
                    spells.get(2).setCurr_talent(true);
                }else{
                    spells.get(2).setCurr_talent(false);
                }
                break;
            case R.id.talent4:
                if(((CheckBox) view).isChecked()){
                    spells.get(3).setCurr_talent(true);
                }else{
                    spells.get(3).setCurr_talent(false);
                }
                break;
            case R.id.talent5:
                if(((CheckBox) view).isChecked()){
                    spells.get(4).setCurr_talent(true);
                }else{
                    spells.get(4).setCurr_talent(false);
                }
        }
    }

    public void onResetClicked(View view) {
        if (hasStarted[currHero]) {
            cdTimer[currHero].cancel();
            switch (currHero) {
                case 0:
                    ((TextView) findViewById(R.id.first_spell_cd)).setText(getString(R.string.spell_ready));
                    ((ImageView) findViewById(R.id.first_indicator)).setImageResource(R.drawable.ic_spell_ready);
                    break;
                case 1:
                    ((TextView) findViewById(R.id.second_spell_cd)).setText(getString(R.string.spell_ready));
                    ((ImageView) findViewById(R.id.second_indicator)).setImageResource(R.drawable.ic_spell_ready);
                    break;
                case 2:
                    ((TextView) findViewById(R.id.third_spell_cd)).setText(getString(R.string.spell_ready));
                    ((ImageView) findViewById(R.id.third_indicator)).setImageResource(R.drawable.ic_spell_ready);
                    break;
                case 3:
                    ((TextView) findViewById(R.id.fourth_spell_cd)).setText(getString(R.string.spell_ready));
                    ((ImageView) findViewById(R.id.fourth_indicator)).setImageResource(R.drawable.ic_spell_ready);
                    break;
                case 4:
                    ((TextView) findViewById(R.id.fifth_spell_cd)).setText(getString(R.string.spell_ready));
                    ((ImageView) findViewById(R.id.fifth_indicator)).setImageResource(R.drawable.ic_spell_ready);
                    break;
            }
        }
    }
    public void onLvlClickedDialog(View view){
        Button btn = (Button) view;
        switch (btn.getText().toString()) {
            case "lvl1":
                btn.setText(getString(R.string.ult_lvl2));
                spells.get(currHero).setCurr_lvl(1);
                break;
            case "lvl2":
                btn.setText(getString(R.string.ult_lvl3));
                spells.get(currHero).setCurr_lvl(2);
                break;
            case "lvl3":
                btn.setText(getString(R.string.ult_lvl1));
                spells.get(currHero).setCurr_lvl(0);
        }
    }

    public void onLvlClicked(View view){
        Button btn = (Button) view;
        switch (view.getId()){
            case R.id.lvl1:
                switch (btn.getText().toString()){
                    case "lvl1":
                        btn.setText(getString(R.string.ult_lvl2));
                        spells.get(0).setCurr_lvl(1);
                        break;
                    case "lvl2":
                        btn.setText(getString(R.string.ult_lvl3));
                        spells.get(0).setCurr_lvl(2);
                        break;
                    case "lvl3":
                        btn.setText(getString(R.string.ult_lvl1));
                        spells.get(0).setCurr_lvl(0);
                }
                break;
            case R.id.lvl2:
                switch (btn.getText().toString()){
                    case "lvl1":
                        btn.setText(getString(R.string.ult_lvl2));
                        spells.get(1).setCurr_lvl(1);
                        break;
                    case "lvl2":
                        btn.setText(getString(R.string.ult_lvl3));
                        spells.get(1).setCurr_lvl(2);
                        break;
                    case "lvl3":
                        btn.setText(getString(R.string.ult_lvl1));
                        spells.get(1).setCurr_lvl(0);
                }
                break;
            case R.id.lvl3:
                switch (btn.getText().toString()){
                    case "lvl1":
                        btn.setText(getString(R.string.ult_lvl2));
                        spells.get(2).setCurr_lvl(1);
                        break;
                    case "lvl2":
                        btn.setText(getString(R.string.ult_lvl3));
                        spells.get(2).setCurr_lvl(2);
                        break;
                    case "lvl3":
                        btn.setText(getString(R.string.ult_lvl1));
                        spells.get(2).setCurr_lvl(0);
                }
                break;
            case R.id.lvl4:
                switch (btn.getText().toString()){
                    case "lvl1":
                        btn.setText(getString(R.string.ult_lvl2));
                        spells.get(3).setCurr_lvl(1);
                        break;
                    case "lvl2":
                        btn.setText(getString(R.string.ult_lvl3));
                        spells.get(3).setCurr_lvl(2);
                        break;
                    case "lvl3":
                        btn.setText(getString(R.string.ult_lvl1));
                        spells.get(3).setCurr_lvl(0);
                }
                break;
            case R.id.lvl5:
                switch (btn.getText().toString()){
                    case "lvl1":
                        btn.setText(getString(R.string.ult_lvl2));
                        spells.get(4).setCurr_lvl(1);
                        break;
                    case "lvl2":
                        btn.setText(getString(R.string.ult_lvl3));
                        spells.get(4).setCurr_lvl(2);
                        break;
                    case "lvl3":
                        btn.setText(getString(R.string.ult_lvl1));
                        spells.get(4).setCurr_lvl(0);
                }
        }
    }

    /**
     * Method to start the cooldown timer of spells. Will extract the needed information from the
     * Hero object, pass them to cooldownCalc and start the timer based on the result.
     * @param field TextView of the cooldown for the spell
     * @param indicator ImageView of the indicator for the spell
     * @param spell_number current Spell/Hero number
     */
    public void startSpellCountDown(final TextView field, final ImageView indicator,
                                    final int spell_number, long retro_active){
        final Spell spell = spells.get(spell_number);

        float cooldown = spell.getCooldown();
        float lvl_red = 0;
        if(spell.getCurr_lvl() != 0) lvl_red = spell.getLvl_reduction(spell.getCurr_lvl()-1);
        float talent_red = 0;
        float aghs_red = 0;
        boolean hasOctarine = spell.hasOctarine();
        boolean hasQuickening = spell.hasQuickening();
        boolean hasPrism = spell.hasPrism();

        if(spell.hasAghs()) aghs_red = spell.getAghs_reduction();
        if(spell.hasTalent()) talent_red = spell.getTalent_reduction();

        float octarine = 0;
        float neutral = 0;
        if(hasQuickening) neutral = 0.13f;      //13% for quickening
        if(hasPrism) neutral = 0.2f;            //20% for prism
        if(hasOctarine) octarine = 0.25f;       //25% for octarine
        cooldown = cooldown - lvl_red - aghs_red;          //subtract lvl based cd

        // calculate the actual cooldown after bonus and reductions and subtract if it was a
        // retroactive call
        long calc_cooldown = (long) cooldownCalc(cooldown, talent_red, octarine, neutral);
        calc_cooldown = calc_cooldown - retro_active;

        //check if a retroactive cd would be <= 0, if so, abort the cd and display spell ready
        if(calc_cooldown <= 0){
            field.setText(getString(R.string.spell_ready));
            indicator.setImageResource(R.drawable.ic_spell_ready);
            return;
        }else{
            calc_cooldown = calc_cooldown*1000;
        }

        // Color the indicator red and start the CountDownTimer to start the cooldown
        indicator.setImageResource(R.drawable.ic_spell_not_ready);
        hasStarted[spell_number] = true;
        final long finalCalc_cooldown = calc_cooldown;
        cdTimer[spell_number] = new CountDownTimer(finalCalc_cooldown, 995) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                field.setText(""+(int)(millisUntilFinished/1000));
                timeLeft[spell_number] = finalCalc_cooldown - millisUntilFinished;
            }
            @Override
            public void onFinish() {
                field.setText(getString(R.string.spell_ready));
                hasStarted[spell_number] = false;
                indicator.setImageResource(R.drawable.ic_spell_ready);
            }
        }.start();
    }

    /**
     * Method to calculate the cooldown of the given spell of a hero. Will check for cooldown
     * reductions from the objects parameters.
     * @param cooldown basic cooldown of the ability (includes flat reduction from aghs/talent
     * @param talent if hero has percent based talent (or flat reduction)
     * @param octarine if hero has octarine equipped
     * @param neutral if hero has a neutral item equipped
     * @return the calculated actual cooldown after reduction
     */
    public float cooldownCalc(float cooldown, float talent, float octarine, float neutral){
        float talent_flat = 0;
        float talent_percent = 0;

        if(talent < 1){
            talent_percent = talent;
        }else{
            talent_flat = talent;
        }
        cooldown = cooldown - talent_flat;
        return (cooldown * (1- talent_percent) * (1-octarine) * (1-neutral));
    }

    /**
     * Realize the layout, which means disabling and greying out or removing views which hold
     * no heroes. Will check through each hero and disable Lvl or CheckBoxes when they
     * don't have the corresponding attribute.
     */
    public void initializeLayout(){
        int curr_hero = 0;
        Resources res = getResources();

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

        CheckBox [] aghs_btn = {findViewById(R.id.aghs1), findViewById(R.id.aghs2), findViewById(R.id.aghs3),
        findViewById(R.id.aghs4), findViewById(R.id.aghs5)};
        CheckBox [] talent_btn = {findViewById(R.id.talent1), findViewById(R.id.talent2),
        findViewById(R.id.talent3), findViewById(R.id.talent4), findViewById(R.id.talent5)};
        Button [] lvl_btn = {findViewById(R.id.lvl1), findViewById(R.id.lvl2), findViewById(R.id.lvl3),
        findViewById(R.id.lvl4), findViewById(R.id.lvl5)};
        Button [] more_btn = {findViewById(R.id.options1), findViewById(R.id.options2),
        findViewById(R.id.options3), findViewById(R.id.options4), findViewById(R.id.options5)};

        for(Hero hero : heroes){
            String mDrawableName = heroes.get(curr_hero).getIcon_name();
            int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
            spell_img1[curr_hero].setImageResource(resID);
            spell_img2[curr_hero].setImageResource(resID);

            if(!hero.hasLvl()){
                lvl_btn[curr_hero].setEnabled(false);
            }
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
