package com.example.dotacdtracker.Data;

public class Hero {

    String name;
    String spell;
    float cooldown;
    boolean has_cd_talent;
    boolean has_aghs_reduction;
    boolean has_lvl_reduction;
    boolean talent_Active;
    boolean aghs_Active;
    float talent_reduction;
    float aghs_reduction;
    float [] lvl_reduction;
    String icon_name;
    String avatar_name;
    String id;

    public Hero(String name, String spell, float cooldown, boolean talent_red,
                boolean aghs_red, boolean lvl_red, float talent_reduction,
                float aghs_reduction, float [] lvl_reduction, String icon_name, String avatar_name,
                String id){
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
        this.avatar_name = avatar_name;
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public String getSpell(){
        return spell;
    }
    public float getCooldown(){
        return cooldown;
    }
    public boolean hasTalent(){
        return has_cd_talent;
    }
    public boolean hasLvl(){
        return has_lvl_reduction;
    }
    public boolean hasAghs(){
        return has_aghs_reduction;
    }
    public float getTalent_reduction(){
        return talent_reduction;
    }
    public float getAghs_reduction(){
        return aghs_reduction;
    }
    public float [] getLvl_reduction(){
        return lvl_reduction;
    }
    public String getIcon_name(){
        return icon_name;
    }
    public String getAvatar_name(){
        return avatar_name;
    }
    public String getId(){
        return id;
    }
    public boolean isAghs_Active() {
        return aghs_Active;
    }
    public boolean isTalent_Active() {
        return talent_Active;
    }
    public void setHas_cd_talent(boolean has_cd_talent) {
        this.has_cd_talent = has_cd_talent;
    }
    public void setHas_lvl_reduction(boolean has_lvl_reduction) {
        this.has_lvl_reduction = has_lvl_reduction;
    }
    public void setHas_aghs_reduction(boolean has_aghs_reduction) {
        this.has_aghs_reduction = has_aghs_reduction;
    }
    public void setAghs_Active(boolean aghs_Active) {
        this.aghs_Active = aghs_Active;
    }
    public void setTalent_Active(boolean talent_Active) {
        this.talent_Active = talent_Active;
    }
}
