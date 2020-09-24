package com.example.dotacdtracker.Data;

public class Spell {

    String spell;
    float cooldown;
    float [] lvl_reduction;
    float talent_reduction;
    float aghs_reduction;
    int curr_lvl = 0;
    boolean curr_aghs = false;
    boolean curr_talent = false;
    boolean octarine = false;
    boolean quickening = false;
    boolean prism = false;
    String icon_name;

    public Spell(String spell, float cooldown, float[] lvl_reduction, float talent_reduction,
                 float aghs_reduction, String icon_name){
        this.spell = spell;
        this.cooldown = cooldown;
        this.lvl_reduction = lvl_reduction;
        this.talent_reduction = talent_reduction;
        this.aghs_reduction = aghs_reduction;
        this.icon_name = icon_name;
    }

    public String getSpell() {
        return spell;
    }
    public float getCooldown() {
        return cooldown;
    }
    public float getLvl_reduction(int index) {
        return lvl_reduction[index];
    }
    public float getTalent_reduction() {
        return talent_reduction;
    }
    public float getAghs_reduction() {
        return aghs_reduction;
    }
    public String getIcon_name() {
        return icon_name;
    }
    public void setCurr_lvl(int curr_lvl) {
        this.curr_lvl = curr_lvl;
    }
    public int getCurr_lvl() {
        return curr_lvl;
    }
    public boolean hasOctarine(){ return octarine; }
    public boolean hasQuickening(){ return quickening; }
    public boolean hasPrism(){ return prism; }
    public void setOctarine(boolean octarine) {
        this.octarine = octarine;
    }
    public void setQuickening(boolean quickening) {
        this.quickening = quickening;
    }
    public void setPrism(boolean prism) {
        this.prism = prism;
    }
    public void setCurr_aghs(boolean curr_aghs) {
        this.curr_aghs = curr_aghs;
    }
    public void setCurr_talent(boolean curr_talent) {
        this.curr_talent = curr_talent;
    }
    public boolean hasAghs(){ return curr_aghs; }
    public boolean hasTalent(){ return curr_talent; }

}
