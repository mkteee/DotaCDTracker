package com.example.dotacdtracker.Data;

public class Spell {

    String spell;
    float cooldown;
    float [] lvl_reduction;
    int curr_lvl = 0;
    float talent_reduction;
    float aghs_reduction;
    String icon_name;
    boolean octarine = false;
    boolean quickening = false;
    boolean prism = false;

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
    public float[] getLvl_reduction() {
        return lvl_reduction;
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
    public boolean hasOctarine(){
        return octarine;
    }
    public boolean hasQuickening(){
        return quickening;
    }
    public boolean hasPrism(){
        return prism;
    }
    public void setOctarine(boolean octarine) {
        this.octarine = octarine;
    }
    public void setQuickening(boolean quickening) {
        this.quickening = quickening;
    }
    public void setPrism(boolean prism) {
        this.prism = prism;
    }
}
