package io.github.frqnny.mostructures.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class MoStructuresConfig implements Config {
    @Comment("""
            Welcome to Mo'Structures Config!
            
            This config used to allow you to modify our mod's structures.
            However, Mojang changed the way structures were done. 
            Now, most of our structures are fully in code.
            
            The purpose of this config file is to lead users to find 
            the necessary resources needed to modify our structures.
            
            And, this is a good change.
            
            Not only can you now modify the generation of a structure, but you will also get 
            to modify the biomes they spawn in and as much other stuff.
            
            All you need to know is that you will have to find
            how to make structure datapacks and use that knowledge
            to modify Mo' Structures. 
            
            To modify the chance of a structure spawning, you will have to work with 
            datapack structure_sets.
            
            To modify biomes, you will only have to use tags, a convenient way to modify the mod.
            
            Below is a lilboolean which will get printed in the log.        
            """)
    public boolean lilboolean = true;

    @Override
    public String getName() {
        return "mostructures-config-v4";
    }

}
