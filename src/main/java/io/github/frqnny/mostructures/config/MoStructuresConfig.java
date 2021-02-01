package io.github.frqnny.mostructures.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "mostructures-config-v1")
public class MoStructuresConfig implements ConfigData {
    @Comment("Mo' Structures feature toggles.")
    @ConfigEntry.Gui.CollapsibleObject
    public Features features = new Features();

    @Comment("Chances are once per the number of chunks. A Chance 500 makes a feature spawn every 500 chunks. Affected by biomes it spawns in and the chunk, so not all are proportional.")
    @ConfigEntry.Gui.CollapsibleObject
    public FeatureChances feature_chances = new FeatureChances();

    @Comment("Structure toggles. These do not have chances, but in the future they may. ")
    @ConfigEntry.Gui.CollapsibleObject
    public Structures structures = new Structures();

    @Comment("Structure chances. Seperation is the least amount of chunks that a structure will spawn from itself. Spacing is the maximum amount of chunks, and both are used to randomly spawn structures and can be tweaked.")
    @ConfigEntry.Gui.CollapsibleObject
    public StructureChances structureChances = new StructureChances();

    public static class Features {
        @Comment("Airplanes & Air Balloons")
        public boolean air_features = true;
        @Comment("Fallen Trees")
        public boolean fallen_trees = true;
        @Comment("Desert Features")
        public boolean desert_features = true;
        @Comment("Ruins")
        public boolean ruins = true;
        @Comment("Lamppost")
        public boolean lamppost = true;
        @Comment("Boulder")
        public boolean boulder = true;
        @Comment("Volcanic Vent")
        public boolean volcanic_vent = true;
        @Comment("Beach Features")
        public boolean beach_features = true;
        @Comment("Boats")
        public boolean boats = true;
    }

    public static class FeatureChances {
        @Comment("Airplanes & Air Balloons")
        public int air_feature_chance = 4600;
        @Comment("Fallen Trees")
        public int fallen_trees_chance = 17;
        @Comment("Desert Features")
        public int desert_features_chance = 555;
        @Comment("Ruins")
        public int ruins_chance = 3500;
        @Comment("Lamppost")
        public int lamppost_chance = 85;
        @Comment("Boulder")
        public int boulder_chance = 4000;
        @Comment("Volcanic Vent")
        public int volcanic_vent_chance = 85;
        @Comment("Beach Features")
        public int beach_features_chance = 75;
        @Comment("Boats")
        public int boats_chance = 4000;
    }

    public static class Structures {
        @Comment("Barnhouse")
        public boolean barn_house = true;
        @Comment("Jungle Pyramid")
        public boolean jungle_pyramid = true;
        @Comment("Big Pyramid")
        public boolean big_pyramid = true;
        @Comment("The Castle In The Sky")
        public boolean the_castle_in_the_sky = true;
        @Comment("Villager Tower")
        public boolean villager_tower = true;
        @Comment("Abandoned Churches")
        public boolean abandoned_churches = true;
        @Comment("Villager Market")
        public boolean villager_market = true;
        @Comment("Pillager Factory")
        public boolean pillager_factory = true;
        @Comment("Ice Tower")
        public boolean ice_tower = true;
        @Comment("Boar Hat Tavern")
        public boolean tavern = true;
    }

    public static class StructureChances {
        @Comment("Barn House")
        public int barn_house_seperation = 16;
        public int barn_house_spacing = 40;
        @Comment("Big Pyramid")
        public int big_pyramid_seperation = 5;
        public int big_pyramid_spacing = 25;
        @Comment("Jungle Pyramid")
        public int jungle_pyramid_seperation = 5;
        public int jungle_pyramid_spacing = 25;
        @Comment("The Castle In The Sky")
        public int the_castle_in_the_sky_seperation = 8;
        public int the_castle_in_the_sky_spacing = 32;
        @Comment("Villager Tower")
        public int villager_tower_seperation = 16;
        public int villager_tower_spacing = 40;
        @Comment("Abandoned Church")
        public int abandoned_church_seperation = 16;
        public int abandoned_church_spacing = 40;
        @Comment("Villager Market")
        public int villager_market_seperation = 27;
        public int villager_market_spacing = 50;
        @Comment("Pillager Factory")
        public int pillager_factory_seperation = 27;
        public int pillager_factory_spacing = 50;
        @Comment("Ice Tower")
        public int ice_tower_seperation = 8;
        public int ice_tower_spacing = 32;
        @Comment("Boar Hat Tavern")
        public int tavern_seperation = 8;
        public int tavern_spacing = 32;
    }
}
