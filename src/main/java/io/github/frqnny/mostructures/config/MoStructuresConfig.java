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
              // - Separation is the minimum chunks between structures
              // - Spacing is the average chunks between structures
              //
              // Salt is a special field that gives structures unique spawning positions. DO NOT TOUCH IT, ONLY ADVANCED TROUBLESHOOTING!
                        
            """)
    public final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>(17);

    @Override
    public String getName() {
        return "mostructures-config-v3";
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
        structureConfigEntries.putIfAbsent("abandoned_church", StructureConfigEntry.of(14, 38, 66996840));
        structureConfigEntries.putIfAbsent("barn_house", StructureConfigEntry.of(8, 38, 165757306));
        structureConfigEntries.putIfAbsent("big_pyramid", StructureConfigEntry.of(5, 25, 239284294));
        structureConfigEntries.putIfAbsent("jungle_pyramid", StructureConfigEntry.of(5, 25, 312178642));
        structureConfigEntries.putIfAbsent("the_castle_in_the_sky", StructureConfigEntry.of(8, 30, 423494938));
        structureConfigEntries.putIfAbsent("killer_bunny_castle", StructureConfigEntry.of(25, 45, 48123900));
        structureConfigEntries.putIfAbsent("villager_tower", StructureConfigEntry.of(16, 34, 550292492));
        structureConfigEntries.putIfAbsent("villager_market", StructureConfigEntry.of(16, 36, 784939542));
        structureConfigEntries.putIfAbsent("pillager_factory", StructureConfigEntry.of(16, 36, 839204924));
        structureConfigEntries.putIfAbsent("ice_tower", StructureConfigEntry.of(8, 28, 964058305));
        structureConfigEntries.putIfAbsent("tavern", StructureConfigEntry.of(12, 32, 19296726));
        structureConfigEntries.putIfAbsent("pirate_ship", StructureConfigEntry.of(16, 40, 583957395));
        structureConfigEntries.putIfAbsent("lighthouse", StructureConfigEntry.of(16, 25, 29502322));
        structureConfigEntries.putIfAbsent("volcanic_vent", StructureConfigEntry.of(4, 8, 84981094));
        structureConfigEntries.putIfAbsent("moai", StructureConfigEntry.of(2, 4, 12994829));
        structureConfigEntries.putIfAbsent("air_balloon", StructureConfigEntry.of(1, 6, 29483148));
        structureConfigEntries.putIfAbsent("village_bazaar", StructureConfigEntry.ofExperimental(16, 32, 34842291));
        Config.super.save();
    }
}
