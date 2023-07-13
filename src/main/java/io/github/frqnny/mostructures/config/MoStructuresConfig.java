package io.github.frqnny.mostructures.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import net.minecraft.util.Identifier;

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
        structureConfigEntries.putIfAbsent("abandoned_church", StructureConfigEntry.of(14, 26));
        structureConfigEntries.putIfAbsent("air_balloon", StructureConfigEntry.of(10, 20));
        structureConfigEntries.putIfAbsent("barn_house", StructureConfigEntry.of(16, 28));
        structureConfigEntries.putIfAbsent("big_pyramid", StructureConfigEntry.of(25, 32));
        structureConfigEntries.putIfAbsent("ice_tower", StructureConfigEntry.of(14, 22));
        structureConfigEntries.putIfAbsent("jungle_pyramid", StructureConfigEntry.of(14, 16));
        structureConfigEntries.putIfAbsent("killer_bunny_castle", StructureConfigEntry.of(22, 30));
        structureConfigEntries.putIfAbsent("lighthouse", StructureConfigEntry.of(14, 24));
        structureConfigEntries.putIfAbsent("moai", StructureConfigEntry.of(15, 17));
        structureConfigEntries.putIfAbsent("pillager_factory", StructureConfigEntry.of(14, 30));
        structureConfigEntries.putIfAbsent("pirate_ship", StructureConfigEntry.of(14, 32));
        structureConfigEntries.putIfAbsent("tavern", StructureConfigEntry.of(13, 30));
        structureConfigEntries.putIfAbsent("the_castle_in_the_sky", StructureConfigEntry.of(15, 23));
        structureConfigEntries.putIfAbsent("villager_market", StructureConfigEntry.of(16, 30));
        structureConfigEntries.putIfAbsent("villager_tower", StructureConfigEntry.of(16, 29));
        structureConfigEntries.putIfAbsent("pillager_mines", StructureConfigEntry.of(27, 40));
        Config.super.save();
    }
}