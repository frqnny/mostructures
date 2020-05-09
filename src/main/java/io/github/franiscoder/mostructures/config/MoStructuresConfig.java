package io.github.franiscoder.mostructures.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "mostructures")
public class MoStructuresConfig implements ConfigData {
    @Comment("Disables all features that spawn in the air, but does not include air structures such as the floating castle.")
    public boolean generateAirFeatures = true;
    @Comment("Generates all structures on the overworld, such as the barnhouse or the floating castle.")
    public boolean generateOverworldStructures = true;
    @Comment("Generates land features in the overworld, which are not structures.")
    public boolean generateLandFeatures = true;
    @Comment("Generates structures in the nether, such as the nether lamppost or the Piglin Outpost.")
    public boolean generateNetherStructures = true;
    @Comment("Generates all ocean features")
    public boolean generateOceanFeatures = true;
    @Comment("Generates all beach features")
    public boolean generateBeachFeatures = true;
}
