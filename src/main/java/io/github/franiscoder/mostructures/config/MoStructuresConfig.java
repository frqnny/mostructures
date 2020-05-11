package io.github.franiscoder.mostructures.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "mostructures")
public class MoStructuresConfig implements ConfigData {
    public final boolean generateAirFeatures = true;
    public final boolean generateOverworldStructures = true;
    public final boolean generateLandFeatures = true;
    public final boolean generateMiscellaneousStructures = true;
    public final boolean generateNetherStructures = true;
}
