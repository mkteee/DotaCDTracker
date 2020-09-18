package com.example.dotacdtracker;

public class Hero {

    String name;
    String spell;
    int cooldown;
    boolean has_cd_talent;
    boolean has_aghs_reduction;
    boolean has_lvl_reduction;
    int talent_reduction;
    int aghs_reduction;
    int [] lvl_reduction;
    String icon_name;

    public Hero(String name, String spell, int cooldown, boolean talent_red,
                boolean aghs_red, boolean lvl_red, int talent_reduction,
                int aghs_reduction, int [] lvl_reduction, String icon_name){
        this.name = name;
        this.spell = spell;
        this.cooldown = cooldown;
        this.has_cd_talent = talent_red;
        this.has_aghs_reduction = aghs_red;
        this.has_lvl_reduction = lvl_red;
        this.talent_reduction = talent_reduction;
        this.aghs_reduction = aghs_reduction;
        this.lvl_reduction = lvl_reduction;
        this.icon_name = icon_name;
    }

    public String getName(){
        return name;
    }
    public String getSpell(){
        return spell;
    }
    public int getCooldown(){
        return cooldown;
    }
    public boolean getTalent(){
        return has_cd_talent;
    }
    public boolean getLvl(){
        return has_lvl_reduction;
    }
    public boolean getAghs(){
        return has_aghs_reduction;
    }
    public int getTalent_reduction(){
        return talent_reduction;
    }
    public int getAghs_reduction(){
        return aghs_reduction;
    }
    public int [] getLvl_reduction(){
        return lvl_reduction;
    }
    public String getIcon_name(){
        return icon_name;
    }
}
