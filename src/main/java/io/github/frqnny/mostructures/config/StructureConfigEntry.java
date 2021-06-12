package io.github.frqnny.mostructures.config;

import java.util.Map;

public class StructureConfigEntry {

    public final boolean activated;
    public final int seperation;
    public final int spacing;
    public final int salt;

    private StructureConfigEntry(int seperation, int spacing, int salt) {
        this.activated = true;
        this.spacing = spacing;
        this.seperation = seperation;
        this.salt = salt;
    }

    public static StructureConfigEntry of(int seperation, int spacing, int salt) {
        return new StructureConfigEntry(seperation, spacing, salt);
    }


    public static void computeConfigMap(Map<String, StructureConfigEntry> map) {
        map.computeIfAbsent("abandoned_church", (id) -> of(14, 38, 66996840));
        map.computeIfAbsent("barn_house", (id) -> of(8, 38, 165757306));
        map.computeIfAbsent("big_pyramid", (id) -> of(5, 25, 239284294));
        map.computeIfAbsent("jungle_pyramid", (id) -> of(5, 25, 312178642));
        map.computeIfAbsent("the_castle_in_the_sky", (id) -> of(8, 30, 423494938));
        map.computeIfAbsent("killer_bunny_castle", (id) -> of(25, 45, 48123900));
        map.computeIfAbsent("villager_tower", (id) -> of(16, 34, 550292492));
        map.computeIfAbsent("villager_market", (id) -> of(16, 36, 784939542));
        map.computeIfAbsent("pillager_factory", (id) -> of(16, 36, 839204924));
        map.computeIfAbsent("ice_tower", (id) -> of(8, 28, 964058305));
        map.computeIfAbsent("tavern", (id) -> of(12, 32, 19296726));
        map.computeIfAbsent("pirate_ship", (id) -> of(16, 40, 583957395));
        map.computeIfAbsent("lighthouse", (id) -> of(16, 32, 29502322));
        map.computeIfAbsent("volcanic_vent", (id) -> of(4, 8, 84981094));
        map.computeIfAbsent("moai", (id) -> of(4, 8, 12994829));
        map.computeIfAbsent("air_balloon", (id) -> of(1, 6, 29483148));
    }
}
