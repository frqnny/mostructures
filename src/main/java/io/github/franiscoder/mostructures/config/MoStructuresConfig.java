package io.github.franiscoder.mostructures.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "mostructures")
public class MoStructuresConfig implements ConfigData {
    public boolean generateAirFeatures = true;
    public boolean generateOverworldStructures = true;
    public boolean generateLandFeatures = true;
    public boolean generateMiscellaneousStructures = true;
    public boolean generateNetherStructures = true;
}
