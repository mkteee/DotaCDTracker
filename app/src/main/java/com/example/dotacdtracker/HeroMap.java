package com.example.dotacdtracker;

public class HeroMap {

    private static final Hero beast = new Hero("Beastmaster", "Primal Roar", 90,
            false, false, true, 0, 0,
            new int[]{10, 10}, "primal_roar_icon");

    private static final Hero brew = new Hero("Brewmaster", "Primal Split", 140,
            true, false, true, 65, 0,
            new int[]{10, 10}, "primal_split_icon");




    public static Hero getHero(String name){
        switch (name){
            case "beast":
                return beast;
            case "brew":
                return brew;

        }
        return brew;
    }
}


