package io.github.frqnny.mostructures;

import io.github.frqnny.mostructures.generator.*;
import io.github.frqnny.mostructures.util.RegUtils;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

public class ConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> VILLAGER_SPAWN = MoStructures.VILLAGER_SPAWN.configure(FeatureConfig.DEFAULT);

    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> BARN_HOUSE = RegUtils.config(MoStructures.BARN_HOUSE, BarnHouseGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> BIG_PYRAMID = RegUtils.config(MoStructures.BIG_PYRAMID, BigPyramidGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> JUNGLE_PYRAMID = RegUtils.config(MoStructures.JUNGLE_PYRAMID, JunglePyramidGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> THE_CASTLE_IN_THE_SKY = RegUtils.config(MoStructures.THE_CASTLE_IN_THE_SKY, TheCastleInTheSkyGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> VILLAGER_MARKET = RegUtils.config(MoStructures.VILLAGER_MARKET, VillagerMarketGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> PILLAGER_FACTORY = RegUtils.config(MoStructures.PILLAGER_FACTORY, PillagerFactoryGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> ICE_TOWER = RegUtils.config(MoStructures.ICE_TOWER, IceTowerGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> TAVERN = RegUtils.config(MoStructures.TAVERN, TavernGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> KILLER_BUNNY_CASTLE = RegUtils.config(MoStructures.KILLER_BUNNY_CASTLE, KillerBunnyCastleGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> VILLAGER_TOWER = RegUtils.config(MoStructures.VILLAGER_TOWER, VillagerTowerGenerator.DEFAULT_STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> SAVANNA_VILLAGER_TOWER = RegUtils.config(MoStructures.VILLAGER_TOWER, VillagerTowerGenerator.SAVANNA_STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> PIRATE_SHIP = RegUtils.config(MoStructures.PIRATE_SHIP, PirateShipGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> LIGHTHOUSE = RegUtils.config(MoStructures.LIGHTHOUSE, LighthouseGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> MOAI = RegUtils.config(MoStructures.MOAI, MoaiGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> AIR_BALLOON = RegUtils.config(MoStructures.AIR_BALLOON, AirBalloonGenerator.STARTING_POOL);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ?> VOLCANIC_VENT = MoStructures.VOLCANIC_VENT.configure(FeatureConfig.DEFAULT);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> VILLAGER_BAZAAR = RegUtils.config(MoStructures.VILLAGER_BAZAAR, VillageBazaarGenerator.STARTING_POOL);

    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> PLAINS_ABANDONED_CHURCH = RegUtils.config(MoStructures.ABANDONED_CHURCH, AbandonedChurchGenerator.PLAINS_STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> SAVANNA_ABANDONED_CHURCH = RegUtils.config(MoStructures.ABANDONED_CHURCH, AbandonedChurchGenerator.SAVANNA_STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> DESERT_ABANDONED_CHURCH = RegUtils.config(MoStructures.ABANDONED_CHURCH, AbandonedChurchGenerator.DESERT_STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> SNOWY_ABANDONED_CHURCH = RegUtils.config(MoStructures.ABANDONED_CHURCH, AbandonedChurchGenerator.SNOWY_STARTING_POOL);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> TAIGA_ABANDONED_CHURCH = RegUtils.config(MoStructures.ABANDONED_CHURCH, AbandonedChurchGenerator.TAIGA_STARTING_POOL);


    public static void registerConfiguredFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MoStructures.id("configured_villager_feature"), VILLAGER_SPAWN);

        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_barnhouse"), BARN_HOUSE);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_pyramid"), BIG_PYRAMID);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_jungle_pyramid"), JUNGLE_PYRAMID);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_tcity"), THE_CASTLE_IN_THE_SKY);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_villager_tower"), VILLAGER_TOWER);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_savanna_village_tower"), SAVANNA_VILLAGER_TOWER);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_villager_market"), VILLAGER_MARKET);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_pillager_factory"), PILLAGER_FACTORY);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_plains_abandoned_church"), PLAINS_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_savanna_abandoned_church"), SAVANNA_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_desert_abandoned_church"), DESERT_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_snowy_abandoned_church"), SNOWY_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_taiga_abandoned_church"), TAIGA_ABANDONED_CHURCH);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_ice_tower"), ICE_TOWER);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_boar_hat_tavern"), TAVERN);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_killer_bunny_castle"), KILLER_BUNNY_CASTLE);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_pirate_ship"), PIRATE_SHIP);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_light_house"), LIGHTHOUSE);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_volcanic_vent"), VOLCANIC_VENT);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_moai"), MOAI);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_air_balloon"), AIR_BALLOON);
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, MoStructures.id("configured_villager_bazaar"), VILLAGER_BAZAAR);
    }

}
