package io.github.franiscoder.mostructures.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "mostructures")
public class MoStructuresConfig implements ConfigData {
    @Comment("Airplanes & Air Balloons")
    public boolean air_features = true;
    @Comment("Fallen Trees")
    public boolean fallen_trees = true;
    @Comment("Desert Features")
    public boolean desert_features = true;
    @Comment("Ruins")
    public boolean ruins = true;
    @Comment("Lamppost")
    public boolean lamppost = true;
    @Comment("Boulder")
    public boolean boulder = true;
    @Comment("Volcanic Vent")
    public boolean volcanic_vent = true;
    @Comment("Beach Features")
    public boolean beach_features = true;
    @Comment("Boats")
    public boolean boats = true;

    @Comment("Barnhouse")
    public boolean barn_house = true;
    @Comment("Jungle Pyramid")
    public boolean jungle_pyramid = true;
    @Comment("Big Pyramid")
    public boolean big_pyramid = true;
    @Comment("The Castle In The Sky")
    public boolean the_castle_in_the_sky = true;
    @Comment("Piglin Outpost")
    public boolean piglin_outpost = true;
}
