package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.decorator.ChanceHeightmapDecorator;
import io.github.frqnny.mostructures.feature.*;
import io.github.frqnny.mostructures.structure.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";
    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = new SmallAirFeature();
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = new FallenTreeFeature();
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = new SmallDryFeature();
    public static final Feature<DefaultFeatureConfig> RUINS = new RuinsFeature();
    public static final Feature<DefaultFeatureConfig> LAMPPOST = new LamppostFeature();
    public static final Feature<DefaultFeatureConfig> BOULDER = new BoulderFeature();
    public static final Feature<DefaultFeatureConfig> VOLCANIC_VENT = new VolcanicVentFeature();
    public static final Feature<DefaultFeatureConfig> SMALL_BEACH_FEATURES = new SmallBeachFeatures();
    public static final Feature<DefaultFeatureConfig> BOAT = new BoatFeature();

    public static final StructureFeature<StructurePoolFeatureConfig> BARN_HOUSE = new BarnHouseStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> BIG_PYRAMID = new BigPyramidStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> JUNGLE_PYRAMID = new JunglePyramidStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> THE_CASTLE_IN_THE_SKY = new TheCastleInTheSkyStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_TOWER = new VillagerTowerStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_MARKET = new VillagerMarketStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> PILLAGER_FACTORY = new PillagerFactoryStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new AbandonedChurchStructure();

    public static final Decorator<ChanceDecoratorConfig> CHANCE_OCEAN_FLOOR_WG = Registry.register(Registry.DECORATOR, id("chance_heightmap_legacy"), new ChanceHeightmapDecorator());
    private static MoStructuresConfig config;

    private static void registerStructures() {
        FabricStructureBuilder.create(BarnHouseStructure.ID, BARN_HOUSE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165757306)
                .superflatFeature(ConfiguredFeatures.BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(BigPyramidStructure.ID, BIG_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 139284294)
                .superflatFeature(ConfiguredFeatures.BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(JunglePyramidStructure.ID, JUNGLE_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 112178642)
                .superflatFeature(ConfiguredFeatures.JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TheCastleInTheSkyStructure.ID, THE_CASTLE_IN_THE_SKY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 123494938)
                .superflatFeature(ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(VillagerTowerStructure.ID, VILLAGER_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_tower_spacing, config.structureChances.villager_tower_seperation, 150292492)
                .superflatFeature(ConfiguredFeatures.VILLAGER_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(AbandonedChurchStructure.ID, ABANDONED_CHURCH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.abandoned_church_spacing, config.structureChances.abandoned_church_seperation, 169968400)
                .superflatFeature(ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(VillagerMarketStructure.ID, VILLAGER_MARKET)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 284939542)
                .superflatFeature(ConfiguredFeatures.VILLAGER_MARKET)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(PillagerFactoryStructure.ID, PILLAGER_FACTORY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.pillager_factory_spacing, config.structureChances.pillager_factory_seperation, 839204924)
                .superflatFeature(ConfiguredFeatures.PILLAGER_FACTORY)
                .adjustsSurface()
                .register();
    }

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, SmallAirFeature.ID, AIR_FEATURES);
        Registry.register(Registry.FEATURE, FallenTreeFeature.ID, FALLEN_TREE);
        Registry.register(Registry.FEATURE, SmallDryFeature.ID, SMALL_DESERT_FEATURES);
        Registry.register(Registry.FEATURE, RuinsFeature.ID, RUINS);
        Registry.register(Registry.FEATURE, LamppostFeature.ID, LAMPPOST);
        Registry.register(Registry.FEATURE, BoulderFeature.ID, BOULDER);
        Registry.register(Registry.FEATURE, VolcanicVentFeature.ID, VOLCANIC_VENT);
        Registry.register(Registry.FEATURE, SmallBeachFeatures.ID, SMALL_BEACH_FEATURES);
        Registry.register(Registry.FEATURE, BoatFeature.ID, BOAT);
    }

    public static void putFeatures() {
        addToBiome(
                SmallAirFeature.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.ICY, Biome.Category.DESERT, Biome.Category.OCEAN).and(booleanToPredicate(config.features.air_features)),
                (context) -> addFeature(context, ConfiguredFeatures.AIR_FEATURES)
        );

        addToBiome(
                SmallAirFeature.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(booleanToPredicate(config.features.air_features)),
                (context) -> addFeature(context, ConfiguredFeatures.AIR_FEATURES_BEACH)
        );

        addToBiome(
                FallenTreeFeature.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.ICY).and(booleanToPredicate(config.features.fallen_trees)),
                (context) -> addFeature(context, ConfiguredFeatures.FALLEN_TREE)
        );

        addToBiome(
                SmallDryFeature.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(booleanToPredicate(config.features.desert_features)),
                (context) -> addFeature(context, ConfiguredFeatures.SMALL_DESERT_FEATURES)
        );

        addToBiome(
                RuinsFeature.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.DESERT, Biome.Category.MESA).and(booleanToPredicate(config.features.ruins)),
                (context) -> addFeature(context, ConfiguredFeatures.RUINS)
        );

        addToBiome(
                LamppostFeature.ID,
                BiomeSelectors.categories(Biome.Category.FOREST, Biome.Category.NETHER).and(booleanToPredicate(config.features.lamppost)),
                (context) -> addFeature(context, ConfiguredFeatures.LAMPPOST)
        );

        addToBiome(
                BoulderFeature.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.ICY, Biome.Category.DESERT, Biome.Category.OCEAN, Biome.Category.BEACH).and(booleanToPredicate(config.features.boulder)),
                (context) -> addFeature(context, ConfiguredFeatures.BOULDER)
        );

        addToBiome(
                VolcanicVentFeature.ID,
                BiomeSelectors.categories(Biome.Category.OCEAN).and(booleanToPredicate(config.features.volcanic_vent)).and(BiomeSelectors.excludeByKey(BiomeKeys.FROZEN_OCEAN)),
                (context) -> addFeature(context, ConfiguredFeatures.VOLCANIC_VENT)
        );

        addToBiome(
                SmallBeachFeatures.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(booleanToPredicate(config.features.beach_features)),
                (context) -> addFeature(context, ConfiguredFeatures.SMALL_BEACH_FEATURES)
        );

        addToBiome(BoatFeature.ID,
                BiomeSelectors.categories(Biome.Category.OCEAN).and(booleanToPredicate(config.features.boats)),
                (context) -> addFeature(context, ConfiguredFeatures.BOAT)
        );
    }

    public static void putStructures() {
        addToBiome(
                BarnHouseStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA).and(booleanToPredicate(config.structures.barn_house)),
                (context) -> addStructure(context, ConfiguredFeatures.BARN_HOUSE)

        );

        addToBiome(
                BigPyramidStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(booleanToPredicate(config.structures.big_pyramid)).and((context) -> !context.getBiomeKey().getValue().getPath().contains("hill")),
                (context) -> addStructure(context, ConfiguredFeatures.BIG_PYRAMID)

        );

        addToBiome(
                JunglePyramidStructure.ID,
                BiomeSelectors.categories(Biome.Category.JUNGLE).and(booleanToPredicate(config.structures.jungle_pyramid)).and((context) -> !context.getBiomeKey().getValue().getPath().contains("hill")),
                (context) -> addStructure(context, ConfiguredFeatures.JUNGLE_PYRAMID)
        );

        addToBiome(
                TheCastleInTheSkyStructure.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(booleanToPredicate(config.structures.the_castle_in_the_sky)),
                (context) -> addStructure(context, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)

        );

        addToBiome(
                VillagerTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.FOREST).and(booleanToPredicate(config.structures.villager_tower)),
                (context) -> addStructure(context, ConfiguredFeatures.VILLAGER_TOWER)

        );

        addToBiome(
                VillagerMarketStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.FOREST).and(booleanToPredicate(config.structures.villager_market)).and((context) -> !context.getBiomeKey().getValue().getPath().contains("hill")),
                (context) -> addStructure(context, ConfiguredFeatures.VILLAGER_MARKET)
        );

        addToBiome(
                PillagerFactoryStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.TAIGA, Biome.Category.ICY).and(booleanToPredicate(config.structures.pillager_factory)).and((context) -> !context.getBiomeKey().getValue().getPath().contains("hill")),
                (context) -> addStructure(context, ConfiguredFeatures.PILLAGER_FACTORY)
        );

        addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS).and(booleanToPredicate(config.structures.abandoned_churches)),
                (context) -> addStructure(context, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
        );
        addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(booleanToPredicate(config.structures.abandoned_churches)),
                (context) -> addStructure(context, ConfiguredFeatures.DESERT_ABANDONED_CHURCH)
        );
        addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(booleanToPredicate(config.structures.abandoned_churches)),
                (context) -> addStructure(context, ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH)
        );
        addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.TAIGA).and(booleanToPredicate(config.structures.abandoned_churches)),
                (context) -> addStructure(context, ConfiguredFeatures.TAIGA_ABANDONED_CHURCH)
        );
        addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.ICY).and(booleanToPredicate(config.structures.abandoned_churches)),
                (context) -> addStructure(context, ConfiguredFeatures.SNOWY_ABANDONED_CHURCH)
        );
    }

    public static Supplier<StructurePool> pool(StructurePool pool) {
        return () -> pool;
    }

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    public static MoStructuresConfig getConfig() {
        config = AutoConfig.getConfigHolder(MoStructuresConfig.class).getConfig();
        return config;
    }

    private static void addToBiome(Identifier id, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }

    public static void addFeature(BiomeModificationContext context, ConfiguredFeature<?, ?> feature) {
        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, feature);
    }

    public static void addStructure(BiomeModificationContext context, ConfiguredStructureFeature<?, ?> feature) {
        context.getGenerationSettings().addBuiltInStructure(feature);
    }

    public static Predicate<BiomeSelectionContext> booleanToPredicate(boolean bol) {
        return (context) -> bol;
    }

    public static StructureProcessorList register(String id, ImmutableList<StructureProcessor> processorList) {
        Identifier identifier = new Identifier(id);
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, identifier, structureProcessorList);
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(MoStructuresConfig.class, JanksonConfigSerializer::new);
        config = MoStructures.getConfig();

        registerStructures();
        registerFeatures();
        ConfiguredFeatures.registerConfiguredFeatures();

        putFeatures();
        putStructures();
    }
}