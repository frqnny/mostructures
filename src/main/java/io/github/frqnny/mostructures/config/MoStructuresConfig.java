package io.github.frqnny.mostructures.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class MoStructuresConfig implements Config {
    @Comment("""
             Welcome to Mo'Structures Config!
             
               Take a break, rest a while, enjoy the scenery! No? Oh, well.
              
               Here, you can turn off structures and change their generation pattern.
               Since 1.4.1+1.18.2, you can also edit these values in datapacks.
               However, this Config was brought back so you can easily edit these values.
               It is important to note you can edit what biomes these structures spawn in
               using datapacks if you want to have that configuration option.
              
               To turn off a structure, simply go to the corresponding entry and set `activated` to false.
              
               Mo' Structures uses the vanilla structure spawning system. That is-
               - Separation is the minimum chunks between structures
               - Spacing is the average chunks between structures
                                    
            """)
    public final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>(17);

    @Override
    public String getName() {
        return "mostructures-config-v5";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    @Nullable
    public StructureConfigEntry get(Identifier id) {
        for (Map.Entry<String, StructureConfigEntry> entry : structureConfigEntries.entrySet()) {
            if (entry.getKey().equals(id.getPath())) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public void save() {
        structureConfigEntries.putIfAbsent("abandoned_church", StructureConfigEntry.of(16, 32));
        structureConfigEntries.putIfAbsent("air_balloon", StructureConfigEntry.of(6, 12));
        structureConfigEntries.putIfAbsent("barn_house", StructureConfigEntry.of(16, 32));
        structureConfigEntries.putIfAbsent("big_pyramid", StructureConfigEntry.of(16, 24));
        structureConfigEntries.putIfAbsent("ice_tower", StructureConfigEntry.of(16, 24));
        structureConfigEntries.putIfAbsent("jungle_pyramid", StructureConfigEntry.of(16, 20));
        structureConfigEntries.putIfAbsent("killer_bunny_castle", StructureConfigEntry.of(25, 35));
        structureConfigEntries.putIfAbsent("lighthouse", StructureConfigEntry.of(16, 25));
        structureConfigEntries.putIfAbsent("moai", StructureConfigEntry.of(8, 10));
        structureConfigEntries.putIfAbsent("pillager_factory", StructureConfigEntry.of(16, 36));
        structureConfigEntries.putIfAbsent("pirate_ship", StructureConfigEntry.of(16, 40));
        structureConfigEntries.putIfAbsent("tavern", StructureConfigEntry.of(16, 32));
        structureConfigEntries.putIfAbsent("the_castle_in_the_sky", StructureConfigEntry.of(16, 29));
        structureConfigEntries.putIfAbsent("villager_tower", StructureConfigEntry.of(16, 32));
        structureConfigEntries.putIfAbsent("villager_market", StructureConfigEntry.of(16, 34));
        structureConfigEntries.putIfAbsent("volcanic_vent", StructureConfigEntry.of(16, 32));
        Config.super.save();
    }
}