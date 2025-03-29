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
        checkDefault("abandoned_church", 20, 33);
        checkDefault("air_balloon", 12, 22);
        checkDefault("barn_house", 16, 32);
        checkDefault("big_pyramid", 25, 32);
        checkDefault("ice_tower", 20, 28);
        checkDefault("jungle_pyramid", 16, 22);
        checkDefault("killer_bunny_castle", 22, 32);
        checkDefault("lighthouse", 14, 24);
        checkDefault("moai", 15, 17);
        checkDefault("pillager_factory", 16, 34);
        checkDefault("pillager_mines", 27, 40);
        checkDefault("pirate_ship", 16, 32);
        checkDefault("tavern", 16, 26);
        checkDefault("the_castle_in_the_sky", 22, 32);
        checkDefault("villager_market", 16, 32);
        checkDefault("villager_tower", 16, 26);
        Config.super.save();
    }

    private void checkDefault(String configKey, int defaultSeparation, int defaultSpacing) {
        structureConfigEntries.putIfAbsent(configKey, StructureConfigEntry.of(defaultSeparation, defaultSpacing));
    }
}