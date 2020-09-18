package com.example.dotacdtracker;

public class HeroMap {

    private static final Hero beast = new Hero("Beastmaster", "Primal Roar", 90,
            false, false, true, 0, 0,
            new float[]{10, 10}, "primal_roar_icon");

    private static final Hero brew = new Hero("Brewmaster", "Primal Split", 140,
            true, false, true, 65, 0,
            new float[]{10, 10}, "primal_split_icon");

    private static final Hero doom = new Hero("Doom", "Doom", 145,
            false, false, false, 0, 0,
            new float[]{0, 0}, "doooom_icon");

    private static final Hero shaker = new Hero("Earthshaker", "Echo Slam", 150,
            false, false, true, 0, 0,
            new float[]{20, 20}, "echo_slam_icon");

    private static final Hero lycan = new Hero("Lycan", "Shapeshift", 125,
            true, false, true, 0.1f, 0,
            new float[]{15, 15}, "shapeshift_icon");

    private static final Hero magnus = new Hero("Magnus", "Reverse Polarity", 130,
            false, false, false, 0, 0,
            new float[]{0, 0}, "reverse_polarity_icon");

    private static final Hero balanar = new Hero("Night Stalker", "Dark Ascension", 140,
            true, false, true, 60, 0,
            new float[]{10, 10}, "dark_ascension_item");

    private static final Hero omni = new Hero("Omniknight", "Guardian Angel", 160,
            true, false, true, 60, 0,
            new float[]{10, 10}, "guardian_angel_icon");

    private static final Hero phoenix = new Hero("Phoenix", "Supernova", 110,
            false, false, false, 0, 0,
            new float[]{0, 0}, "supernova_icon");

    private static final Hero sand_king = new Hero("Sand King", "Epicenter", 120,
            false, false, true, 0, 0,
            new float[]{10, 10}, "epicenter_icon");

    private static final Hero snapfire = new Hero("Snapfire", "Mortimer Kisses", 110,
            false, false, false, 0, 0,
            new float[]{0, 0}, "mortimer_kisses_icon");

    private static final Hero sven = new Hero("Sven", "God's Strength", 110,
            false, false, false, 0, 0,
            new float[]{0, 0}, "gods_strength_icon");

    private static final Hero tide = new Hero("Tidehunter", "Ravage", 150,
            true, false, false, 0.2f, 0,
            new float[]{0, 0}, "ravage_icon");

    private static final Hero treant = new Hero("Treant Protector", "Overgrowth", 100,
            true, false, false, 0.12f, 0,
            new float[]{0, 0}, "overgrowth_icon");

    private static final Hero underlord = new Hero("Underlord", "Dark Rift", 130,
            false, false, true, 0, 0,
            new float[]{15, 15}, "dark_rift_icon");

    private static final Hero undying = new Hero("Undying", "Tombstone", 85,
            false, false, true, 0, 0,
            new float[]{5, 5, 5}, "tombstone_icon");

    private static final Hero wk = new Hero("Wraith King", "Reincarnation", 200,
            false, false, true, 0, 0,
            new float[]{80, 80}, "reincarnation_icon");

    private static final Hero faceless_void = new Hero("Faceless Void", "Chronosphere", 160,
            false, false, false, 0, 0,
            new float[]{0, 0}, "chronosphere_icon");

    private static final Hero jugg = new Hero("Juggernaut", "Omnislash", 140,
            false, false, false, 0, 0,
            new float[]{0, 0}, "omnislash_icon");

    private static final Hero luna = new Hero("Luna", "Eclipse", 140,
            false, false, false, 0, 0,
            new float[]{0, 0}, "eclipse_icon");

    private static final Hero dusa = new Hero("Medusa", "Stone Gaze", 90,
            false, false, false, 0, 0,
            new float[]{0, 0}, "stone_gaze_icon");

    private static final Hero monkey = new Hero("Monkey King", "Wukong's Command", 130,
            false, false, true, 0, 0,
            new float[]{20, 20}, "wukongs_command_icon");

    private static final Hero naga = new Hero("Naga Siren", "Song of the Siren", 160,
            false, true, true, 0, 20,
            new float[]{40, 40}, "song_of_the_siren_icon");

    private static final Hero pango = new Hero("Pangolier", "Rolling Thunder", 70,
            true, false, false, 28, 0,
            new float[]{0, 0}, "rolling_thunder_icon");

    private static final Hero sf = new Hero("Shadow Fiend", "Requiem of Souls", 120,
            true, false, true, 0.3f, 0,
            new float[]{10, 10}, "requiem_of_souls_icon");

    private static final Hero slark = new Hero("Slark", "Shadow Dance", 80,
            false, false, true, 0, 0,
            new float[]{10, 10}, "shadow_dance_icon");

    private static final Hero spec = new Hero("Spectre", "Haunt", 180,
            false, false, true, 0, 0,
            new float[]{20, 20}, "haunt_icon");

    private static final Hero tb = new Hero("Terrorblade", "Metamorphosis", 155,
            false, false, false, 0, 0,
            new float[]{0, 0, 0}, "metamorphosis_icon");

    private static final Hero troll = new Hero("Troll Warlord", "Battle Trance", 90,
            false, true, false, 0, 55,
            new float[]{0, 0}, "battle_trance_icon");

    private static final Hero veno = new Hero("Venomancer", "Poison Nova", 140,
            false, true, true, 0, 40,
            new float[]{20, 20}, "poison_nova_icon");

    private static final Hero weaver = new Hero("Weaver", "Time Lapse", 70,
            false, true, true, 0, 20,
            new float[]{15, 15}, "time_lapse_icon");

    private static final Hero bane = new Hero("Bane", "Fiend's Grip", 120,
            false, false, true, 0, 0,
            new float[]{10, 10}, "fiends_grip_icon");

    private static final Hero bat = new Hero("Batrider", "Flaming Lasso", 100,
            true, false, true, 0.12f, 0,
            new float[]{10, 10}, "flaming_lasso_icon");

    private static final Hero cm = new Hero("Crystal Maiden", "Freezing Field", 110,
            false, false, true, 0, 0,
            new float[]{10, 10}, "freezing_field_icon");

    private static final Hero dark_seer = new Hero("Dark Seer", "Wall of Replica", 100,
            false, false, false, 0, 0,
            new float[]{0, 0}, "wall_of_replica_icon");

    private static final Hero dp = new Hero("Death Prophet", "Exorcism", 145,
            false, false, false, 0, 0,
            new float[]{0, 0}, "exorcism_icon");

    private static final Hero disruptor = new Hero("Disruptor", "Static Storm", 90,
            false, false, true, 0, 0,
            new float[]{10, 10}, "static_storm_icon");

    private static final Hero enigma = new Hero("Enigma", "Black Hole", 200,
            true, false, true, 0.12f, 0,
            new float[]{20, 20}, "black_hole_icon");

    private static final Hero invo = new Hero("Invoker", "Cataclysm", 90,
            false, false, false, 0, 0,
            new float[]{0}, "sun_strike_icon");

    private static final Hero kotl = new Hero("Keeper of the Light", "Will-o-Wisp", 130,
            false, false, false, 0, 0,
            new float[]{0, 0}, "will_o_wisp_icon");

    private static final Hero lich = new Hero("Lich", "Chain Frost", 100,
            false, false, true, 0, 0,
            new float[]{20, 20}, "chain_frost_icon");

    private static final Hero lion = new Hero("Lion", "Finger of Death", 160,
            false, true, true, 0, 20,
            new float[]{60, 60}, "finger_of_death_icon");

    private static final Hero necro = new Hero("Necrophos", "Reaper's Scythe", 120,
            false, false, false, 0, 0,
            new float[]{0, 0}, "reapers_scythe_icon");

    private static final Hero od = new Hero("Outworld Devourer", "Sanity's Eclipse", 160,
            false, false, false, 0, 0,
            new float[]{0, 0}, "sanitys_eclipse_icon");

    private static final Hero qop = new Hero("Queen of Pain", "Sonic Wave", 125,
            true, true, false, 0.1f, 85,
            new float[]{0, 0}, "sonic_wave_icon");

    private static final Hero shaman = new Hero("Shadow Shaman", "Mass Serpent Ward", 120,
            false, false, false, 0, 0,
            new float[]{0, 0}, "mass_serpent_ward_icon");

    private static final Hero silencer = new Hero("Silencer", "Global Silence", 130,
            true, false, true, 0.3f, 0,
            new float[]{15, 15}, "global_silence_icon");

    private static final Hero warlock = new Hero("Warlock", "Chaotic Offering", 170,
            false, false, false, 0, 0,
            new float[]{0, 0}, "chaotic_offering_icon");

    private static final Hero wyvern = new Hero("Winter Wyvern", "Winter's Curse", 90,
            false, false, false, 0, 0,
            new float[]{0, 0}, "winters_curse_icon");

    private static final Hero witch_doctor = new Hero("Witch Doctor", "Death Ward", 80,
            false, false, false, 0, 0,
            new float[]{0, 0}, "death_ward_icon");

    private static final Hero zeus = new Hero("Zeus", "Thundergod's Wrath", 120,
            true, false, false, 0.12f, 0,
            new float[]{0, 0}, "thundergods_wrath_icon");

    public static Hero getHero(String name){
        switch (name){
            case "beast":
                return beast;
            case "brew":
                return brew;
            case "doom":
                return doom;
            case "shaker":
                return shaker;
            case "lycan":
                return lycan;
            case "magnus":
                return magnus;
            case "balanar":
                return balanar;
            case "omni":
                return omni;
            case "phoenix":
                return phoenix;
            case "sand_king":
                return sand_king;
            case "snapfire":
                return snapfire;
            case "sven":
                return sven;
            case "tide":
                return tide;
            case "treant":
                return treant;
            case "undying":
                return undying;
            case "underlord":
                return underlord;
            case "wraith_king":
                return wk;
            case "faceless_void":
                return faceless_void;
            case "jugg":
                return jugg;
            case "luna":
                return luna;
            case "dusa":
                return dusa;
            case "monkey":
                return monkey;
            case "naga":
                return naga;
            case "pango":
                return pango;
            case "sf":
                return sf;
            case "slark":
                return slark;
            case "spec":
                return spec;
            case "tb":
                return tb;
            case "troll":
                return troll;
            case "veno":
                return veno;
            case "weaver":
                return weaver;
            case "bane":
                return bane;
            case "bat":
                return bat;
            case "cm":
                return cm;
            case "dark_seer":
                return dark_seer;
            case "dp":
                return dp;
            case "disruptor":
                return disruptor;
            case "enigma":
                return enigma;
            case "invo":
                return invo;
            case "kotl":
                return kotl;
            case "lich":
                return lich;
            case "lion":
                return lion;
            case "necro":
                return necro;
            case "od":
                return od;
            case "qop":
                return qop;
            case "shaman":
                return shaman;
            case "silencer":
                return silencer;
            case "warlock":
                return warlock;
            case "wyvern":
                return wyvern;
            case "witch_doctor":
                return witch_doctor;
            case "zeus":
                return zeus;
        }
        return brew;
    }
}


