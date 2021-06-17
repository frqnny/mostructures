package io.github.frqnny.mostructures.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class MoStructuresConfig implements Config {
    @Comment("""
             Welcome to Mo'Structures Config!
              //
              // Here, you can turn off structures, change their chance, and also change their salt.
              //
              // To turn off a structure, simply go to the corresponding entry and set `activated` to false.
              //
              // Mo' Structures uses the vanilla structure spawning system. That is-
              // - Seperation is the minimum chunks between structures
              // - Spacing is the average chunks between structures
              //
              // Salt is a special field that gives structures unique spawning positions. DO NOT TOUCH IT, ONLY ADVANCED TROUBLESHOOTING!
                        
            """)
    public final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>(17);

    public static void computeConfigMap(Map<String, StructureConfigEntry> map) {
        map.computeIfAbsent("abandoned_church", (id) -> StructureConfigEntry.of(14, 38, 66996840));
        map.computeIfAbsent("barn_house", (id) -> StructureConfigEntry.of(8, 38, 165757306));
        map.computeIfAbsent("big_pyramid", (id) -> StructureConfigEntry.of(5, 25, 239284294));
        map.computeIfAbsent("jungle_pyramid", (id) -> StructureConfigEntry.of(5, 25, 312178642));
        map.computeIfAbsent("the_castle_in_the_sky", (id) -> StructureConfigEntry.of(8, 30, 423494938));
        map.computeIfAbsent("killer_bunny_castle", (id) -> StructureConfigEntry.of(25, 45, 48123900));
        map.computeIfAbsent("villager_tower", (id) -> StructureConfigEntry.of(16, 34, 550292492));
        map.computeIfAbsent("villager_market", (id) -> StructureConfigEntry.of(16, 36, 784939542));
        map.computeIfAbsent("pillager_factory", (id) -> StructureConfigEntry.of(16, 36, 839204924));
        map.computeIfAbsent("ice_tower", (id) -> StructureConfigEntry.of(8, 28, 964058305));
        map.computeIfAbsent("tavern", (id) -> StructureConfigEntry.of(12, 32, 19296726));
        map.computeIfAbsent("pirate_ship", (id) -> StructureConfigEntry.of(16, 40, 583957395));
        map.computeIfAbsent("lighthouse", (id) -> StructureConfigEntry.of(16, 32, 29502322));
        map.computeIfAbsent("volcanic_vent", (id) -> StructureConfigEntry.of(4, 8, 84981094));
        map.computeIfAbsent("moai", (id) -> StructureConfigEntry.of(2, 4, 12994829));
        map.computeIfAbsent("air_balloon", (id) -> StructureConfigEntry.of(1, 6, 29483148));
        map.computeIfAbsent("village_bazaar", (id) -> StructureConfigEntry.ofExperimental(16, 32, 34842291));
    }

    @Override
    public String getName() {
        return "mostructures-config-v2";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    public boolean activated(Identifier id) {
        return get(id).activated;
    }

    public StructureConfigEntry get(Identifier id) {
        for (Map.Entry<String, StructureConfigEntry> entry : structureConfigEntries.entrySet()) {
            if (entry.getKey().equals(id.getPath())) {
                return entry.getValue();
            }
        }

        throw new NullPointerException("Tried StructureConfigEntry with id: " + id + ", but it was null!");
    }

    @Override
    public void save() {
        computeConfigMap(structureConfigEntries);
        Config.super.save();
    }
}
