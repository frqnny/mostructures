package io.github.frqnny.mostructures.config;

import io.github.frqnny.omegaconfig.api.Comment;
import io.github.frqnny.omegaconfig.api.Config;

import java.util.HashMap;
import java.util.Map;

public class MoStructuresConfig implements Config {
    @Comment("""
             Welcome to Mo'Structures Config!
                        
               Take a break, rest a while, enjoy the scenery! :)
                        
               Using this config, you can:
               - Prevent structures from generating by de-activating them.
               - Modify values used in calculating structure locations to modify their chance to generate.
                        
               You can modify the biomes these structures spawn in through datapacks.
                        
               Structures use two numbers to determine structure locations:
               - Separation is the minimum chunk distance between structures of that type.
               - Spacing is the average chunk distance between structures of that type.
               Modifying these values can shift the chances a structure generates.
                        
               If you have any questions, please join my discord available in our CurseForge page.
                        
            """)
    protected final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>();

    @Override
    public String getName() {
        return "mostructures-config-v5";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    public StructureConfigEntry get(String configKey) {
        return structureConfigEntries.get(configKey);
    }

    @Override
    public void save() {
        //places all our structures's entries in case they do not exist, then saves the config
        ensureInConfig("abandoned_church", 14, 26);
        ensureInConfig("air_balloon", 10, 20);
        ensureInConfig("barn_house", 16, 28);
        ensureInConfig("big_pyramid", 25, 32);
        ensureInConfig("ice_tower", 14, 22);
        ensureInConfig("jungle_pyramid", 14, 16);
        ensureInConfig("killer_bunny_castle", 22, 30);
        ensureInConfig("lighthouse", 14, 24);
        ensureInConfig("moai", 15, 17);
        ensureInConfig("pillager_factory", 14, 30);
        ensureInConfig("pillager_mines", 27, 40);
        ensureInConfig("pirate_ship", 14, 32);
        ensureInConfig("tavern", 13, 30);
        ensureInConfig("the_castle_in_the_sky", 15, 23);
        ensureInConfig("villager_market", 16, 30);
        ensureInConfig("villager_tower", 16, 29);
        Config.super.save();
    }

    private void ensureInConfig(String configKey, int separation, int spacing) {
        structureConfigEntries.putIfAbsent(configKey, StructureConfigEntry.of(separation, spacing));
    }
}