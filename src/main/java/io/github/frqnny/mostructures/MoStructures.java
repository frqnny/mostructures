package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.decorator.ChanceHeightmapDecorator;
import io.github.frqnny.mostructures.feature.*;
import io.github.frqnny.mostructures.structure.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
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


    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new AbandonedChurchStructure();

    public static final Decorator<ChanceDecoratorConfig> CHANCE_OCEAN_FLOOR_WG = Registry.register(Registry.DECORATOR, id("chance_heightmap_legacy"), new ChanceHeightmapDecorator());
    private static MoStructuresConfig config;

    private static void registerStructures() {
        FabricStructureBuilder.create(BarnHouseStructure.ID, ConfiguredFeatures.BARN_HOUSE.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165757306)
                .superflatFeature(ConfiguredFeatures.BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(BigPyramidStructure.ID, ConfiguredFeatures.BIG_PYRAMID.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 139284294)
                .superflatFeature(ConfiguredFeatures.BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(JunglePyramidStructure.ID, ConfiguredFeatures.JUNGLE_PYRAMID.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 112178642)
                .superflatFeature(ConfiguredFeatures.JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TheCastleInTheSkyStructure.ID, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 123494938)
                .superflatFeature(ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(VillagerTowerStructure.ID, ConfiguredFeatures.VILLAGER_TOWER.feature)
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
        FabricStructureBuilder.create(VillagerMarketStructure.ID, ConfiguredFeatures.VILLAGER_MARKET.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 284939542)
                .superflatFeature(ConfiguredFeatures.VILLAGER_MARKET)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(PillagerFactoryStructure.ID, ConfiguredFeatures.PILLAGER_FACTORY.feature)
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
        addToBiome(SmallAirFeature.ID.getPath(),
                (context) ->
                        config.features.air_features &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SWAMP ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.FOREST ||
                                        context.getBiome().getCategory() == Biome.Category.TAIGA ||
                                        context.getBiome().getCategory() == Biome.Category.ICY ||
                                        context.getBiome().getCategory() == Biome.Category.DESERT ||
                                        context.getBiome().getCategory() == Biome.Category.OCEAN ||
                                        !(context.getBiome().getCategory() == Biome.Category.BEACH)),
                (context) ->
                        addFeature(context, ConfiguredFeatures.AIR_FEATURES)
        );

        addToBiome(SmallAirFeature.ID.getPath(),
                (context) ->
                        config.features.air_features &&
                                context.getBiome().getCategory() == Biome.Category.BEACH,
                (context) ->
                        addFeature(context, ConfiguredFeatures.AIR_FEATURES_BEACH)
        );

        addToBiome(FallenTreeFeature.ID.getPath(),
                (context) ->
                        config.features.fallen_trees &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SWAMP ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.FOREST ||
                                        context.getBiome().getCategory() == Biome.Category.TAIGA ||
                                        context.getBiome().getCategory() == Biome.Category.ICY),
                (context) ->
                        addFeature(context, ConfiguredFeatures.FALLEN_TREE)
        );

        addToBiome(SmallDryFeature.ID.getPath(),
                (context) ->
                        config.features.desert_features &&
                                context.getBiome().getCategory() == Biome.Category.DESERT,
                (context) ->
                        addFeature(context, ConfiguredFeatures.SMALL_DESERT_FEATURES)
        );

        addToBiome(RuinsFeature.ID.getPath(),
                (context) ->
                        config.features.ruins &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SWAMP ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.DESERT ||
                                        context.getBiome().getCategory() == Biome.Category.MESA),
                (context) ->
                        addFeature(context, ConfiguredFeatures.RUINS)
        );

        addToBiome(LamppostFeature.ID.getPath(),
                (context) ->
                        config.features.lamppost &&
                                (context.getBiome().getCategory() == Biome.Category.FOREST ||
                                        context.getBiome().getCategory() == Biome.Category.NETHER),
                (context) ->
                        addFeature(context, ConfiguredFeatures.LAMPPOST)
        );

        addToBiome(BoulderFeature.ID.getPath(),
                (context) ->
                        config.features.boulder &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SWAMP ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.FOREST ||
                                        context.getBiome().getCategory() == Biome.Category.TAIGA ||
                                        context.getBiome().getCategory() == Biome.Category.ICY ||
                                        context.getBiome().getCategory() == Biome.Category.DESERT ||
                                        context.getBiome().getCategory() == Biome.Category.OCEAN ||
                                        context.getBiome().getCategory() == Biome.Category.BEACH),
                (context) ->
                        addFeature(context, ConfiguredFeatures.BOULDER)
        );

        addToBiome(VolcanicVentFeature.ID.getPath(),
                (context) ->
                        config.features.volcanic_vent && !context.getBiomeKey().getValue().getPath().contains("frozen") &&
                                context.getBiome().getCategory() == Biome.Category.OCEAN,
                (context) ->
                        addFeature(context, ConfiguredFeatures.VOLCANIC_VENT)
        );

        addToBiome(SmallBeachFeatures.ID.getPath(),
                (context) ->
                        config.features.beach_features &&
                                context.getBiome().getCategory() == Biome.Category.BEACH,
                (context) ->
                        addFeature(context, ConfiguredFeatures.SMALL_BEACH_FEATURES)
        );

        addToBiome(BoatFeature.ID.getPath(),
                (context) ->
                        config.features.boats &&
                                context.getBiome().getCategory() == Biome.Category.OCEAN,
                (context) ->
                        addFeature(context, ConfiguredFeatures.BOAT)
        );
    }

    public static void putStructures() {
        addToBiome(BarnHouseStructure.ID.getPath(),
                (context) ->
                        config.structures.barn_house &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA),
                (context) ->
                        addStructure(context, ConfiguredFeatures.BARN_HOUSE)

        );

        addToBiome(BigPyramidStructure.ID.getPath(),
                (context) ->
                        config.structures.big_pyramid && !context.getBiomeKey().getValue().getPath().contains("hill") &&
                                context.getBiome().getCategory() == Biome.Category.DESERT,
                (context) ->
                        addStructure(context, ConfiguredFeatures.BIG_PYRAMID)

        );

        addToBiome(JunglePyramidStructure.ID.getPath(),
                (context) ->
                        config.structures.jungle_pyramid && !context.getBiomeKey().getValue().getPath().contains("hill") &&
                                context.getBiome().getCategory() == Biome.Category.JUNGLE,
                (context) ->
                        addStructure(context, ConfiguredFeatures.JUNGLE_PYRAMID)

        );

        addToBiome(TheCastleInTheSkyStructure.ID.getPath(),
                (context) ->
                        config.structures.the_castle_in_the_sky &&
                                context.getBiome().getCategory() == Biome.Category.BEACH,
                (context) ->
                        addStructure(context, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)

        );

        addToBiome(VillagerTowerStructure.ID.getPath(),
                (context) ->
                        config.structures.villager_tower &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.FOREST),
                (context) ->
                        addStructure(context, ConfiguredFeatures.VILLAGER_TOWER)

        );

        addToBiome(VillagerMarketStructure.ID.getPath(),
                (context) ->
                        config.structures.villager_market && !context.getBiomeKey().getValue().getPath().contains("hill") &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.FOREST),
                (context) ->
                        addStructure(context, ConfiguredFeatures.VILLAGER_MARKET)
        );
        addToBiome(PillagerFactoryStructure.ID.getPath(),
                (context) ->
                        config.structures.pillager_factory && !context.getBiomeKey().getValue().getPath().contains("hill") &&
                                (context.getBiome().getCategory() == Biome.Category.PLAINS ||
                                        context.getBiome().getCategory() == Biome.Category.SAVANNA ||
                                        context.getBiome().getCategory() == Biome.Category.TAIGA ||
                                        context.getBiome().getCategory() == Biome.Category.ICY),
                (context) ->
                        addStructure(context, ConfiguredFeatures.PILLAGER_FACTORY)
        );

        addToBiome(AbandonedChurchStructure.ID.getPath(),
                (context) ->
                        config.structures.abandoned_churches && context.getBiome().getCategory() == Biome.Category.PLAINS,
                (context) ->
                        addStructure(context, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
        );
        addToBiome(AbandonedChurchStructure.ID.getPath(),
                (context) ->
                        config.structures.abandoned_churches && context.getBiome().getCategory() == Biome.Category.DESERT,
                (context) ->
                        addStructure(context, ConfiguredFeatures.DESERT_ABANDONED_CHURCH)
        );
        addToBiome(AbandonedChurchStructure.ID.getPath(),
                (context) ->
                        config.structures.abandoned_churches && context.getBiome().getCategory() == Biome.Category.SAVANNA,
                (context) ->
                        addStructure(context, ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH)
        );
        addToBiome(AbandonedChurchStructure.ID.getPath(),
                (context) ->
                        config.structures.abandoned_churches && context.getBiome().getCategory() == Biome.Category.TAIGA,
                (context) ->
                        addStructure(context, ConfiguredFeatures.TAIGA_ABANDONED_CHURCH)
        );
        addToBiome(AbandonedChurchStructure.ID.getPath(),
                (context) ->
                        config.structures.abandoned_churches && context.getBiome().getCategory() == Biome.Category.ICY,
                (context) ->
                        addStructure(context, ConfiguredFeatures.SNOWY_ABANDONED_CHURCH)
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

    private static void addToBiome(String modificationName, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(id(modificationName)).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }

    public static void addFeature(BiomeModificationContext context, ConfiguredFeature<?, ?> feature) {
        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, feature);
    }

    public static void addStructure(BiomeModificationContext context, ConfiguredStructureFeature<?, ?> feature) {
        context.getGenerationSettings().addBuiltInStructure(feature);
    }

    public static StructureProcessorList register(String id, ImmutableList<StructureProcessor> processorList) {
        Identifier identifier = new Identifier(id);
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, identifier, structureProcessorList);
    }

    @Override
    public void onInitialize() {
        //We first create the config.
        AutoConfig.register(MoStructuresConfig.class, JanksonConfigSerializer::new);
        config = MoStructures.getConfig();

        //Have to register the features, structures, and configured structure/feature
        registerStructures();
        registerFeatures();
        ConfiguredFeatures.registerConfiguredFeatures();

        putFeatures();
        putStructures();
    }
}