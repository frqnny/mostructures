package io.github.frqnny.mostructures.config;

import java.util.HashMap;
import java.util.Map;

public class StructureConfigEntry {

    public boolean activated;
    public int seperation;
    public int spacing;
    public int salt;
    private StructureConfigEntry(boolean activated, int seperation, int spacing, int salt) {
        this.activated = activated;
        this.spacing = spacing;
        this.seperation = seperation;
        this.salt = salt;
    }

    public static StructureConfigEntry of(int seperation, int spacing, int salt) {
        return new StructureConfigEntry(true, seperation, spacing, salt);
    }


    public static Map<String, StructureConfigEntry> getConfigMap() {
        Map<String, StructureConfigEntry> map = new HashMap<>(17);
        map.put("abandoned_church", of(14, 38, 66996840));
        map.put("barn_house", of(8, 38, 165757306));
        map.put("big_pyramid", of(5, 25, 239284294));
        map.put("jungle_pyramid",of( 5, 25, 312178642));
        map.put("the_castle_in_the_sky", of(8, 30, 423494938));
        map.put("killer_bunny_castle", of( 25, 45, 48123900));
        map.put("villager_tower", of( 16, 34, 550292492));
        map.put("villager_market", of( 16, 36, 784939542));
        map.put("pillager_factory", of( 16, 36, 839204924));
        map.put("ice_tower", of( 8, 28, 964058305));
        map.put("tavern", of( 12, 32, 19296726));
        map.put("pirate_ship", of( 16, 40, 583957395));
        map.put("lighthouse", of(16, 32, 29502322));
        map.put("volcanic_vent", of(4, 8, 84981094));
        map.put("moai", of(4, 8, 12994829));
        map.put("air_balloon", of(1, 6, 29483148));
        return map;
    }
}
