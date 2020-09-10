package io.github.franiscoder.mostructures;

import io.github.franiscoder.mostructures.feature.SmallAirFeature;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ConfiguredFeatures {
    //this class was used to be called "fuck" because why do I need this
    public static ConfiguredFeature<?, ?> AIR_FEATURES_BEACH = MoStructures.AIR_FEATURES
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.air_feature_chance / (SmallAirFeature.AIR_FEATURES.length + 2))));

    public static ConfiguredFeature<?, ?> AIR_FEATURES = MoStructures.AIR_FEATURES
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.air_feature_chance / (SmallAirFeature.AIR_FEATURES.length))));
    public static ConfiguredFeature<?, ?> FALLEN_TREE = MoStructures.FALLEN_TREE
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.fallen_trees_chance)));
    public static ConfiguredFeature<?, ?> SMALL_DESERT_FEATURES = MoStructures.SMALL_DESERT_FEATURES
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.desert_features_chance)));
    public static ConfiguredFeature<?, ?> RUINS = MoStructures.RUINS
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.ruins_chance)));
    public static ConfiguredFeature<?, ?> BOULDER = MoStructures.BOULDER
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.boulder_chance)));
    public static ConfiguredFeature<?, ?> VOLCANIC_VENT = MoStructures.VOLCANIC_VENT
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.volcanic_vent_chance)));
    public static ConfiguredFeature<?, ?> SMALL_BEACH_FEATURES = MoStructures.SMALL_BEACH_FEATURES
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.beach_features_chance)));
    public static ConfiguredFeature<?, ?> BOAT = MoStructures.BOAT
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.boats_chance)));
    public static ConfiguredFeature<?, ?> LAMPPOST = MoStructures.LAMPPOST
            .configure(FeatureConfig.DEFAULT)
            .decorate(MoStructures.CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(MoStructures.getConfig().feature_chances.lamppost_chance)));
}
