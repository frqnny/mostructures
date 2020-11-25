package io.github.frqnny.mostructures;

import io.github.frqnny.mostructures.feature.SmallAirFeature;
import io.github.frqnny.mostructures.generator.*;
import io.github.frqnny.mostructures.structure.*;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ConfiguredFeatures {
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> BARN_HOUSE = new BarnHouseStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(BarnHouseGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> BIG_PYRAMID = new BigPyramidStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(BigPyramidGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> JUNGLE_PYRAMID = new JunglePyramidStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(JunglePyramidGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> THE_CASTLE_IN_THE_SKY = new TheCastleInTheSkyStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(TheCastleInTheSkyGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> VILLAGER_TOWER = new VillagerTowerStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(VillagerTowerGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> VILLAGER_MARKET = new VillagerMarketStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(VillagerMarketGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> PILLAGER_FACTORY = new PillagerFactoryStructure().configure(new StructurePoolFeatureConfig(MoStructures.pool(PillagerFactoryGenerator.STARTING_POOL), 2));

    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> PLAINS_ABANDONED_CHURCH = MoStructures.ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(MoStructures.pool(AbandonedChurchGenerator.PLAINS_STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> SAVANNA_ABANDONED_CHURCH = MoStructures.ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(MoStructures.pool(AbandonedChurchGenerator.SAVANNA_STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> DESERT_ABANDONED_CHURCH = MoStructures.ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(MoStructures.pool(AbandonedChurchGenerator.DESERT_STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> SNOWY_ABANDONED_CHURCH = MoStructures.ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(MoStructures.pool(AbandonedChurchGenerator.SNOWY_STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> TAIGA_ABANDONED_CHURCH = MoStructures.ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(MoStructures.pool(AbandonedChurchGenerator.TAIGA_STARTING_POOL), 2));
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

    public static void registerConfiguredFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_air_feature_beach"), AIR_FEATURES_BEACH);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_air_feature"), AIR_FEATURES);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_fallen_tree"), FALLEN_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_small_desert_feature"), SMALL_DESERT_FEATURES);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_ruins"), RUINS);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_boulder"), BOULDER);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_volcanic_vent"), VOLCANIC_VENT);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_small_beach_feature"), SMALL_BEACH_FEATURES);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_boat"), BOAT);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_lamppost"), LAMPPOST);

        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, BarnHouseStructure.ID, BARN_HOUSE);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, BigPyramidStructure.ID, BIG_PYRAMID);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, JunglePyramidStructure.ID, JUNGLE_PYRAMID);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, TheCastleInTheSkyStructure.ID, THE_CASTLE_IN_THE_SKY);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, VillagerTowerStructure.ID, VILLAGER_TOWER);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, VillagerMarketStructure.ID, VILLAGER_MARKET);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, PillagerFactoryStructure.ID, PILLAGER_FACTORY);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("plains_abandoned_church"), PLAINS_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("savanna_abandoned_church"), SAVANNA_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("desert_abandoned_church"), DESERT_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("snowy_abandoned_church"), SNOWY_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("taiga_abandoned_church"), TAIGA_ABANDONED_CHURCH);
    }

}
