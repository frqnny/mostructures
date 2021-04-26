package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.decorator.ChanceHeightmapDecorator;
import io.github.frqnny.mostructures.feature.*;
import io.github.frqnny.mostructures.feature.config.ArmorStandFeatureConfig;
import io.github.frqnny.mostructures.feature.entity.ArmorStandFeature;
import io.github.frqnny.mostructures.feature.entity.VillagerEntityFeature;
import io.github.frqnny.mostructures.processor.DataBlockStructureProcessor;
import io.github.frqnny.mostructures.processor.SimpleCobblestoneProcessor;
import io.github.frqnny.mostructures.processor.SimpleStoneStructureProcessor;
import io.github.frqnny.mostructures.structure.*;
import io.github.frqnny.mostructures.util.RegistrationHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Random;
import java.util.function.Predicate;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";
    public static final Random random = new Random();
    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = new SmallAirFeature();
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = new FallenTreeFeature();
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = new SmallDryFeature();
    public static final Feature<DefaultFeatureConfig> LAMPPOST = new LamppostFeature();
    public static final Feature<DefaultFeatureConfig> VOLCANIC_VENT = new VolcanicVentFeature();
    public static final Feature<DefaultFeatureConfig> SMALL_BEACH_FEATURES = new SmallBeachFeatures();
    public static final Feature<ArmorStandFeatureConfig> ARMOR_STAND_SPAWN = new ArmorStandFeature();
    public static final Feature<DefaultFeatureConfig> VILLAGER_SPAWN = new VillagerEntityFeature();

    public static final StructureFeature<StructurePoolFeatureConfig> BARN_HOUSE = new BarnHouseStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> BIG_PYRAMID = new BigPyramidStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> JUNGLE_PYRAMID = new JunglePyramidStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> THE_CASTLE_IN_THE_SKY = new TheCastleInTheSkyStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_TOWER = new VillagerTowerStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_MARKET = new VillagerMarketStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> PILLAGER_FACTORY = new PillagerFactoryStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new AbandonedChurchStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ICE_TOWER = new IceTowerStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> TAVERN = new TavernStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> KILLER_BUNNY_CASTLE = new KillerBunnyCastleStructure();

    public static final Decorator<ChanceDecoratorConfig> CHANCE_OCEAN_FLOOR_WG = Registry.register(Registry.DECORATOR, id("chance_heightmap_legacy"), new ChanceHeightmapDecorator());
    public static StructureProcessorType<SimpleStoneStructureProcessor> SIMPLE_STONE;
    public static StructureProcessorType<SimpleCobblestoneProcessor> SIMPLE_COBBLESTONE;
    public static StructureProcessorType<DataBlockStructureProcessor> DATA_BLOCK_STRUCTURE_PROCESSOR;
    public static StructureProcessorList JUNGLE_ROT_LIST;
    public static StructureProcessorList ICE_TOWER_LIST;
    public static StructureProcessorList VILLAGER_TOWER_LIST;

    public static MoStructuresConfig config;

    private static void registerStructures() {
        FabricStructureBuilder.create(BarnHouseStructure.ID, BARN_HOUSE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165757306)
                .superflatFeature(ConfiguredFeatures.BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(BigPyramidStructure.ID, BIG_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 239284294)
                .superflatFeature(ConfiguredFeatures.BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(JunglePyramidStructure.ID, JUNGLE_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 312178642)
                .superflatFeature(ConfiguredFeatures.JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TheCastleInTheSkyStructure.ID, THE_CASTLE_IN_THE_SKY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 423494938)
                .superflatFeature(ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(VillagerTowerStructure.ID, VILLAGER_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_tower_spacing, config.structureChances.villager_tower_seperation, 550292492)
                .superflatFeature(ConfiguredFeatures.VILLAGER_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(AbandonedChurchStructure.ID, ABANDONED_CHURCH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.abandoned_church_spacing, config.structureChances.abandoned_church_seperation, 669968400)
                .superflatFeature(ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(VillagerMarketStructure.ID, VILLAGER_MARKET)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 784939542)
                .superflatFeature(ConfiguredFeatures.VILLAGER_MARKET)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(PillagerFactoryStructure.ID, PILLAGER_FACTORY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.pillager_factory_spacing, config.structureChances.pillager_factory_seperation, 839204924)
                .superflatFeature(ConfiguredFeatures.PILLAGER_FACTORY)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(IceTowerStructure.ID, ICE_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.ice_tower_spacing, config.structureChances.ice_tower_seperation, 964058305)
                .superflatFeature(ConfiguredFeatures.ICE_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TavernStructure.ID, TAVERN)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.tavern_spacing, config.structureChances.tavern_seperation, 19296726)
                .superflatFeature(ConfiguredFeatures.TAVERN)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(KillerBunnyCastleStructure.ID, KILLER_BUNNY_CASTLE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.killer_bunny_castle_spacing, config.structureChances.killer_bunny_castle_seperation, 29573969)
                .superflatFeature(ConfiguredFeatures.KILLER_BUNNY_CASTLE)
                .adjustsSurface()
                .register();

    }

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, SmallAirFeature.ID, AIR_FEATURES);
        Registry.register(Registry.FEATURE, FallenTreeFeature.ID, FALLEN_TREE);
        Registry.register(Registry.FEATURE, SmallDryFeature.ID, SMALL_DESERT_FEATURES);
        Registry.register(Registry.FEATURE, LamppostFeature.ID, LAMPPOST);
        Registry.register(Registry.FEATURE, VolcanicVentFeature.ID, VOLCANIC_VENT);
        Registry.register(Registry.FEATURE, SmallBeachFeatures.ID, SMALL_BEACH_FEATURES);
        Registry.register(Registry.FEATURE, ArmorStandFeature.ID, ARMOR_STAND_SPAWN);
        Registry.register(Registry.FEATURE, VillagerEntityFeature.ID, VILLAGER_SPAWN);
    }

    public static void putFeatures() {
        RegistrationHelper.addToBiome(
                SmallAirFeature.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.ICY, Biome.Category.DESERT, Biome.Category.OCEAN).and(RegistrationHelper.booleanToPredicate(config.features.air_features)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.AIR_FEATURES)
        );

        RegistrationHelper.addToBiome(
                SmallAirFeature.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegistrationHelper.booleanToPredicate(config.features.air_features)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.AIR_FEATURES_BEACH)
        );

        RegistrationHelper.addToBiome(
                FallenTreeFeature.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.features.fallen_trees)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.FALLEN_TREE)
        );

        RegistrationHelper.addToBiome(
                SmallDryFeature.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegistrationHelper.booleanToPredicate(config.features.desert_features)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.SMALL_DESERT_FEATURES)
        );

        RegistrationHelper.addToBiome(
                LamppostFeature.ID,
                BiomeSelectors.categories(Biome.Category.FOREST, Biome.Category.NETHER).and(RegistrationHelper.booleanToPredicate(config.features.lamppost)).and(MoStructures.vanilla()).and(BiomeSelectors.foundInOverworld().or(BiomeSelectors.foundInTheNether())).and(BiomeSelectors.excludeByKey(BiomeKeys.BASALT_DELTAS)),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.LAMPPOST)
        );

        RegistrationHelper.addToBiome(
                VolcanicVentFeature.ID,
                BiomeSelectors.categories(Biome.Category.OCEAN).and(RegistrationHelper.booleanToPredicate(config.features.volcanic_vent)).and(BiomeSelectors.excludeByKey(BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.VOLCANIC_VENT)
        );

        RegistrationHelper.addToBiome(
                SmallBeachFeatures.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegistrationHelper.booleanToPredicate(config.features.beach_features)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addFeature(context, ConfiguredFeatures.SMALL_BEACH_FEATURES)
        );
    }

    public static void putStructures() {
        RegistrationHelper.addToBiome(
                BarnHouseStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.structures.barn_house)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.BARN_HOUSE)

        );

        RegistrationHelper.addToBiome(
                BigPyramidStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegistrationHelper.booleanToPredicate(config.structures.big_pyramid)).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()).and((context) -> !context.getBiomeKey().getValue().getPath().contains("lakes")),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.BIG_PYRAMID)

        );

        RegistrationHelper.addToBiome(
                JunglePyramidStructure.ID,
                BiomeSelectors.categories(Biome.Category.JUNGLE).and(RegistrationHelper.booleanToPredicate(config.structures.jungle_pyramid)).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.JUNGLE_PYRAMID)
        );

        RegistrationHelper.addToBiome(
                TheCastleInTheSkyStructure.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegistrationHelper.booleanToPredicate(config.structures.the_castle_in_the_sky)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)

        );

        RegistrationHelper.addToBiome(
                VillagerTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegistrationHelper.booleanToPredicate(config.structures.villager_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.VILLAGER_TOWER)

        );
        RegistrationHelper.addToBiome(
                VillagerTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.structures.villager_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.SAVANNA_VILLAGER_TOWER)

        );
        RegistrationHelper.addToBiome(
                VillagerTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegistrationHelper.booleanToPredicate(config.structures.villager_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.DESERT_VILLAGER_TOWER)

        );

        RegistrationHelper.addToBiome(
                VillagerMarketStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.FOREST).and(RegistrationHelper.booleanToPredicate(config.structures.villager_market)).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.VILLAGER_MARKET)
        );

        RegistrationHelper.addToBiome(
                PillagerFactoryStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.structures.pillager_factory)).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.PILLAGER_FACTORY)
        );

        RegistrationHelper.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS).and(RegistrationHelper.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegistrationHelper.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.DESERT_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.TAIGA).and(RegistrationHelper.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.TAIGA_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.SNOWY_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                IceTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.structures.ice_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.ICE_TOWER)
        );
        RegistrationHelper.addToBiome(
                TavernStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegistrationHelper.booleanToPredicate(config.structures.tavern)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.TAVERN)
        );
        RegistrationHelper.addToBiome(
                KillerBunnyCastleStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.structures.killer_bunny_castle)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.KILLER_BUNNY_CASTLE)
        );
    }

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    public static void registerConfig() {
        config = OmegaConfig.register(MoStructuresConfig.class);
    }

    public static void registerStructureProcessors() {
        SIMPLE_STONE = StructureProcessorType.register("jungle_rot_processor", SimpleStoneStructureProcessor.CODEC);
        SIMPLE_COBBLESTONE = StructureProcessorType.register("simple_cobblestone", SimpleCobblestoneProcessor.CODEC);
        DATA_BLOCK_STRUCTURE_PROCESSOR = StructureProcessorType.register("data_block_structure_processor", DataBlockStructureProcessor.CODEC);
        JUNGLE_ROT_LIST = RegistrationHelper.registerStructureProcessor("jungle_rot", ImmutableList.of(new SimpleStoneStructureProcessor(0.15F)));
        ICE_TOWER_LIST = RegistrationHelper.registerStructureProcessor("ice_tower_rot", ImmutableList.of(new SimpleStoneStructureProcessor(0)));
        VILLAGER_TOWER_LIST = RegistrationHelper.registerStructureProcessor("villager_tower_rot", ImmutableList.of(new SimpleCobblestoneProcessor(0.15F), new DataBlockStructureProcessor()));
    }

    //vanilla check that doesn't crash on servers
    public static Predicate<BiomeSelectionContext> vanilla() {
        return context -> {
            // No data pack check bc it crash
            return context.getBiomeKey().getValue().getNamespace().equals("minecraft");
        };
    }

    @Override
    public void onInitialize() {
        registerConfig();

        registerStructureProcessors();
        registerStructures();
        registerFeatures();
        ConfiguredFeatures.registerConfiguredFeatures();

        putFeatures();
        putStructures();
    }
}