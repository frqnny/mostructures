package io.github.franiscoder.mostructures.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "mostructures")
public class MoStructuresConfig implements ConfigData {
    //Disables all features that spawn in the air, but does not include air structures such as the floating castle.
    public final boolean generateAirFeatures = true;
    //Generates all structures on the overworld, such as the barnhouse or the floating castle.
    public final boolean generateOverworldStructures = true;
    //Generates land features in the overworld, which are not structures.
    public final boolean generateLandFeatures = true;
    //Generates structures in the nether, such as the nether lamppost or the Piglin Outpost.
    public final boolean generateNetherStructures = true;
}
