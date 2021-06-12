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

    @Override
    public String getName() {
        return "mostructures-config-v2";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    public int spacing(Identifier id) {
        return get(id).spacing;
    }

    public int seperation(Identifier id) {
        return get(id).seperation;
    }

    public int salt(Identifier id) {
        return get(id).salt;
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
        StructureConfigEntry.computeConfigMap(structureConfigEntries);
        Config.super.save();
    }
}
