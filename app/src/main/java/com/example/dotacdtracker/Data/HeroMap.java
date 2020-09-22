package com.example.dotacdtracker.Data;

public class HeroMap {

    private static final Hero beast = new Hero("Beastmaster", "Primal Roar", 90,
            false, false, true, 0, 0,
            new float[]{10, 10}, "primal_roar_icon", "beastmaster_icon", "beast");

    private static final Hero brew = new Hero("Brewmaster", "Primal Split", 140,
            true, false, true, 65, 0,
            new float[]{10, 10}, "primal_split_icon", "brewmaster_icon", "brew");

    private static final Hero doom = new Hero("Doom", "Doom", 145,
            false, false, false, 0, 0,
            new float[]{0, 0}, "doooom_icon", "doom_icon", "doom");

    private static final Hero shaker = new Hero("Earthshaker", "Echo Slam", 150,
            false, false, true, 0, 0,
            new float[]{20, 20}, "echo_slam_icon", "earthshaker_icon", "shaker");

    private static final Hero lycan = new Hero("Lycan", "Shapeshift", 125,
            true, false, true, 0.1f, 0,
            new float[]{15, 15}, "shapeshift_icon", "lycan_icon", "lycan");

    private static final Hero magnus = new Hero("Magnus", "Reverse Polarity", 130,
            false, false, false, 0, 0,
            new float[]{0, 0}, "reverse_polarity_icon", "magnus_icon", "magnus");

    private static final Hero balanar = new Hero("Night Stalker", "Dark Ascension", 140,
            true, false, true, 60, 0,
            new float[]{10, 10}, "dark_ascension_icon", "night_stalker_icon", "balanar");

    private static final Hero omni = new Hero("Omniknight", "Guardian Angel", 160,
            true, false, true, 60, 0,
            new float[]{10, 10}, "guardian_angel_icon", "omniknight_icon", "omni");

    private static final Hero phoenix = new Hero("Phoenix", "Supernova", 110,
            false, false, false, 0, 0,
            new float[]{0, 0}, "supernova_icon", "phoenix_icon", "phoenix");

    private static final Hero sand_king = new Hero("Sand King", "Epicenter", 120,
            false, false, true, 0, 0,
            new float[]{10, 10}, "epicenter_icon", "sand_king_icon", "sand_king");

    private static final Hero snapfire = new Hero("Snapfire", "Mortimer Kisses", 110,
            false, false, false, 0, 0,
            new float[]{0, 0}, "mortimer_kisses_icon", "snapfire_icon", "snapfire");

    private static final Hero sven = new Hero("Sven", "God's Strength", 110,
            false, false, false, 0, 0,
            new float[]{0, 0}, "gods_strength_icon", "sven_icon", "sven");

    private static final Hero tide = new Hero("Tidehunter", "Ravage", 150,
            true, false, false, 0.2f, 0,
            new float[]{0, 0}, "ravage_icon", "tidehunter_icon", "tide");

    private static final Hero treant = new Hero("Treant Protector", "Overgrowth", 100,
            true, false, false, 0.12f, 0,
            new float[]{0, 0}, "overgrowth_icon", "treant_protector_icon", "treant");

    private static final Hero underlord = new Hero("Underlord", "Dark Rift", 130,
            false, false, true, 0, 0,
            new float[]{15, 15}, "dark_rift_icon", "underlord_icon", "underlord");

    private static final Hero undying = new Hero("Undying", "Tombstone", 85,
            false, false, true, 0, 0,
            new float[]{5, 5, 5}, "tombstone_icon", "undying_icon", "undying");

    private static final Hero wk = new Hero("Wraith King", "Reincarnation", 200,
            false, false, true, 0, 0,
            new float[]{80, 80}, "reincarnation_icon", "wraith_king_icon", "wk");

    private static final Hero faceless_void = new Hero("Faceless Void", "Chronosphere", 160,
            false, false, false, 0, 0,
            new float[]{0, 0}, "chronosphere_icon", "faceless_void_icon", "faceless_void");

    private static final Hero jugg = new Hero("Juggernaut", "Omnislash", 140,
            false, false, false, 0, 0,
            new float[]{0, 0}, "omnislash_icon", "juggernaut_icon", "jugg");

    private static final Hero luna = new Hero("Luna", "Eclipse", 140,
            false, false, false, 0, 0,
            new float[]{0, 0}, "eclipse_icon", "luna_icon", "luna");

    private static final Hero dusa = new Hero("Medusa", "Stone Gaze", 90,
            false, false, false, 0, 0,
            new float[]{0, 0}, "stone_gaze_icon", "medusa_icon", "dusa");

    private static final Hero monkey = new Hero("Monkey King", "Wukong's Command", 130,
            false, false, true, 0, 0,
            new float[]{20, 20}, "wukongs_command_icon", "monkey_king_icon", "monkey");

    private static final Hero naga = new Hero("Naga Siren", "Song of the Siren", 160,
            false, true, true, 0, 20,
            new float[]{40, 40}, "song_of_the_siren_icon", "naga_siren_icon", "naga");

    private static final Hero pango = new Hero("Pangolier", "Rolling Thunder", 70,
            true, false, false, 28, 0,
            new float[]{0, 0}, "rolling_thunder_icon", "pangolier_icon", "pango");

    private static final Hero sf = new Hero("Shadow Fiend", "Requiem of Souls", 120,
            true, false, true, 0.3f, 0,
            new float[]{10, 10}, "requiem_of_souls_icon", "shadow_fiend_icon", "sf");

    private static final Hero slark = new Hero("Slark", "Shadow Dance", 80,
            false, false, true, 0, 0,
            new float[]{10, 10}, "shadow_dance_icon", "slark_icon", "slark");

    private static final Hero spec = new Hero("Spectre", "Haunt", 180,
            false, false, true, 0, 0,
            new float[]{20, 20}, "haunt_icon", "spectre_icon", "spec");

    private static final Hero tb = new Hero("Terrorblade", "Metamorphosis", 155,
            false, false, false, 0, 0,
            new float[]{0, 0, 0}, "metamorphosis_icon", "terrorblade_icon", "tb");

    private static final Hero troll = new Hero("Troll Warlord", "Battle Trance", 90,
            false, true, false, 0, 55,
            new float[]{0, 0}, "battle_trance_icon", "troll_warlord_icon", "troll");

    private static final Hero veno = new Hero("Venomancer", "Poison Nova", 140,
            false, true, true, 0, 40,
            new float[]{20, 20}, "poison_nova_icon", "venomancer_icon", "veno");

    private static final Hero weaver = new Hero("Weaver", "Time Lapse", 70,
            false, true, true, 0, 20,
            new float[]{15, 15}, "time_lapse_icon", "weaver_icon", "weaver");

    private static final Hero bane = new Hero("Bane", "Fiend's Grip", 120,
            false, false, true, 0, 0,
            new float[]{10, 10}, "fiends_grip_icon", "bane_icon", "bane");

    private static final Hero bat = new Hero("Batrider", "Flaming Lasso", 100,
            true, false, true, 0.12f, 0,
            new float[]{10, 10}, "flaming_lasso_icon", "batrider_icon", "bat");

    private static final Hero cm = new Hero("Crystal Maiden", "Freezing Field", 110,
            false, false, true, 0, 0,
            new float[]{10, 10}, "freezing_field_icon", "crystal_maiden_icon", "cm");

    private static final Hero dark_seer = new Hero("Dark Seer", "Wall of Replica", 100,
            false, false, false, 0, 0,
            new float[]{0, 0}, "wall_of_replica_icon", "dark_seer_icon", "dark_seer");

    private static final Hero dp = new Hero("Death Prophet", "Exorcism", 145,
            false, false, false, 0, 0,
            new float[]{0, 0}, "exorcism_icon", "death_prophet_icon", "dp");

    private static final Hero disruptor = new Hero("Disruptor", "Static Storm", 90,
            false, false, true, 0, 0,
            new float[]{10, 10}, "static_storm_icon", "disruptor_icon", "disruptor");

    private static final Hero enigma = new Hero("Enigma", "Black Hole", 200,
            true, false, true, 0.12f, 0,
            new float[]{20, 20}, "black_hole_icon", "enigma_icon", "enigma");

    private static final Hero invo = new Hero("Invoker", "Cataclysm", 90,
            false, false, false, 0, 0,
            new float[]{0}, "sun_strike_icon", "invoker_icon", "invo");

    private static final Hero kotl = new Hero("Keeper of the Light", "Will-o-Wisp", 130,
            false, false, false, 0, 0,
            new float[]{0, 0}, "will_o_wisp_icon", "keeper_of_the_light_icon", "kotl");

    private static final Hero lich = new Hero("Lich", "Chain Frost", 100,
            false, false, true, 0, 0,
            new float[]{20, 20}, "chain_frost_icon", "lich_icon", "lich");

    private static final Hero lion = new Hero("Lion", "Finger of Death", 160,
            false, true, true, 0, 20,
            new float[]{60, 60}, "finger_of_death_icon", "lion_icon", "lion");

    private static final Hero necro = new Hero("Necrophos", "Reaper's Scythe", 120,
            false, false, false, 0, 0,
            new float[]{0, 0}, "reapers_scythe_icon", "necrophos_icon", "necro");

    private static final Hero od = new Hero("Outworld Devourer", "Sanity's Eclipse", 160,
            false, false, false, 0, 0,
            new float[]{0, 0}, "sanitys_eclipse_icon", "outworld_devourer_icon", "od");

    private static final Hero qop = new Hero("Queen of Pain", "Sonic Wave", 125,
            true, true, false, 0.1f, 85,
            new float[]{0, 0}, "sonic_wave_icon", "queen_of_pain_icon", "qop");

    private static final Hero shaman = new Hero("Shadow Shaman", "Mass Serpent Ward", 120,
            false, false, false, 0, 0,
            new float[]{0, 0}, "mass_serpent_ward_icon", "shadow_shaman_icon", "shaman");

    private static final Hero silencer = new Hero("Silencer", "Global Silence", 130,
            true, false, true, 0.3f, 0,
            new float[]{15, 15}, "global_silence_icon", "silencer_icon", "silencer");

    private static final Hero warlock = new Hero("Warlock", "Chaotic Offering", 170,
            false, false, false, 0, 0,
            new float[]{0, 0}, "chaotic_offering_icon", "warlock_icon", "warlock");

    private static final Hero wyvern = new Hero("Winter Wyvern", "Winter's Curse", 90,
            false, false, false, 0, 0,
            new float[]{0, 0}, "winters_curse_icon", "winter_wyvern_icon", "wyvern");

    private static final Hero witch_doctor = new Hero("Witch Doctor", "Death Ward", 80,
            false, false, false, 0, 0,
            new float[]{0, 0}, "death_ward_icon", "witch_doctor_icon", "witch_doctor");

    private static final Hero zeus = new Hero("Zeus", "Thundergod's Wrath", 120,
            true, false, false, 0.12f, 0,
            new float[]{0, 0}, "thundergods_wrath_icon", "zeus_icon", "zeus");

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


