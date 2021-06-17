package io.github.frqnny.mostructures.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

public class MoStructuresConfig implements Config {
    @Comment("Structure toggles. These do not have chances, but in the future they may. ")
    public Structures structures = new Structures();

    @Comment("Structure chances. Seperation is the minimum amount of chunks that a structure will spawn from itself. Spacing is the average amount of chunks, and both are used to randomly spawn structures and can be tweaked.")
    public StructureChances structureChances = new StructureChances();

    @Override
    public String getName() {
        return "mostructures-config-v1";
    }

    @Override
    public String getExtension() {
        return "json5";
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
        @Comment("Killer Bunny Castle")
        public boolean killer_bunny_castle = true;
        @Comment("Moai")
        public boolean moai = true;
        @Comment("Volcanic Vent")
        public boolean volcanic_vent = true;
        @Comment("Air Balloons")
        public boolean air_balloons = true;
        @Comment("Pirate Ship")
        public boolean pirate_ship = true;
        @Comment("Lighthouse")
        public boolean lighthouse = true;
        @Comment("Village Bazaar")
        public boolean bazaar = false;
    }

    public static class StructureChances {
        @Comment("Barn House")
        public int barn_house_seperation = 8;
        public int barn_house_spacing = 38;
        @Comment("Big Pyramid")
        public int big_pyramid_seperation = 5;
        public int big_pyramid_spacing = 25;
        @Comment("Jungle Pyramid")
        public int jungle_pyramid_seperation = 5;
        public int jungle_pyramid_spacing = 25;
        @Comment("The Castle In The Sky")
        public int the_castle_in_the_sky_seperation = 8;
        public int the_castle_in_the_sky_spacing = 30;
        @Comment("Villager Tower")
        public int villager_tower_seperation = 16;
        public int villager_tower_spacing = 34;
        @Comment("Abandoned Church")
        public int abandoned_church_seperation = 14;
        public int abandoned_church_spacing = 38;
        @Comment("Villager Market")
        public int villager_market_seperation = 16;
        public int villager_market_spacing = 36;
        @Comment("Pillager Factory")
        public int pillager_factory_seperation = 16;
        public int pillager_factory_spacing = 36;
        @Comment("Ice Tower")
        public int ice_tower_seperation = 8;
        public int ice_tower_spacing = 28;
        @Comment("Boar Hat Tavern")
        public int tavern_seperation = 12;
        public int tavern_spacing = 32;
        @Comment("Killer Bunny Castle")
        public int killer_bunny_castle_seperation = 25;
        public int killer_bunny_castle_spacing = 45;
        @Comment("Pirate Ship")
        public int pirate_ship_seperation = 16;
        public int pirate_ship_spacing = 40;
        @Comment("Lighthouse")
        public int lighthouse_seperation = 16;
        public int lighthouse_spacing = 32;
        @Comment("Volcanic Vent")
        public int volcanic_vent_seperation = 4;
        public int volcanic_vent_spacing = 8;
        @Comment("Moai")
        public int moai_seperation = 2;
        public int moai_spacing = 4;
        @Comment("Air Balloon")
        public int air_balloon_seperation = 1;
        public int air_balloon_spacing = 6;
        @Comment("Village Bazaar (Be sure to turn it on in the other settings for it to generate!")
        public int village_bazaar_seperation = 16;
        public int village_bazaar_spacing = 32;
    }


}