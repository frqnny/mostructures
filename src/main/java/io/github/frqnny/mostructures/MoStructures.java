package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.decorator.ChanceHeightmapDecorator;
import io.github.frqnny.mostructures.feature.*;
import io.github.frqnny.mostructures.structure.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
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
    //Because Auto Config is a shithole, we need to ban biomes that no one will really need to activate biomes
    public static final String[] always_banned_biomes = {"hotm:thinking_forest"};
    public static final ArrayList<String> full_list_of_banned_biomes = new ArrayList<>(1);
    public static Biome.Category category;
    private static MoStructuresConfig config;

    private static void registerStructures() {
        FabricStructureBuilder.create(BarnHouseStructure.ID, io.github.frqnny.mostructures.ConfiguredFeatures.BARN_HOUSE.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165755306)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(BigPyramidStructure.ID, io.github.frqnny.mostructures.ConfiguredFeatures.BIG_PYRAMID.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 130284294)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(JunglePyramidStructure.ID, io.github.frqnny.mostructures.ConfiguredFeatures.JUNGLE_PYRAMID.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 112178942)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TheCastleInTheSkyStructure.ID, io.github.frqnny.mostructures.ConfiguredFeatures.THE_CASTLE_IN_THE_SKY.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 123474938)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(VillagerTowerStructure.ID, io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_TOWER.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_tower_spacing, config.structureChances.villager_tower_seperation, 150288492)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(AbandonedChurchStructure.ID, ABANDONED_CHURCH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.abandoned_church_spacing, config.structureChances.abandoned_church_seperation, 160468400)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(VillagerMarketStructure.ID, io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_MARKET.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 284039542)
                .superflatFeature(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_MARKET)
                .adjustsSurface()
                .register();
    }

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, SmallAirFeature.ID, new SmallAirFeature());
        Registry.register(Registry.FEATURE, FallenTreeFeature.ID, new FallenTreeFeature());
        Registry.register(Registry.FEATURE, SmallDryFeature.ID, new SmallDryFeature());
        Registry.register(Registry.FEATURE, RuinsFeature.ID, new RuinsFeature());
        Registry.register(Registry.FEATURE, LamppostFeature.ID, new LamppostFeature());
        Registry.register(Registry.FEATURE, BoulderFeature.ID, new BoulderFeature());
        Registry.register(Registry.FEATURE, VolcanicVentFeature.ID, new VolcanicVentFeature());
        Registry.register(Registry.FEATURE, SmallBeachFeatures.ID, new SmallBeachFeatures());
        Registry.register(Registry.FEATURE, BoatFeature.ID, new BoatFeature());

    }

    public static void putFeatures(Biome biome) {
        for (String string : full_list_of_banned_biomes) {
            Optional<Identifier> id = Optional.ofNullable(BuiltinRegistries.BIOME.getId(biome));
            if (id.isPresent()) {
                if (id.toString().equals(string)) {
                    return;
                }
            }
        }
        category = biome.getCategory();

        //some of them have the same features so just made them the same
        switch (category) {
            case PLAINS:
            case SWAMP:
            case SAVANNA:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.fallen_trees) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.FALLEN_TREE);
                }
                if (config.features.ruins) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.RUINS);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }

                break;
            case FOREST:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.fallen_trees) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.FALLEN_TREE);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                if (config.features.lamppost) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.LAMPPOST);
                }
                break;
            case TAIGA:
            case ICY:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.fallen_trees) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.FALLEN_TREE);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                break;
            case DESERT:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.desert_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.SMALL_DESERT_FEATURES);
                }
                if (config.features.ruins) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.RUINS);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                break;
            case OCEAN:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                if (config.features.volcanic_vent) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.VOLCANIC_VENT);
                }
                if (config.features.boats) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOAT);
                }
                break;
            case BEACH:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES_BEACH);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                if (config.features.beach_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.SMALL_BEACH_FEATURES);
                }
                break;
            case JUNGLE:
            case EXTREME_HILLS:
            case RIVER:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                break;
            case MUSHROOM:
            case MESA:
                if (config.features.air_features) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.AIR_FEATURES);
                }
                if (config.features.ruins) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.RUINS);
                }
                if (config.features.boulder) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.BOULDER);
                }
                break;
            case NETHER:
                if (config.features.lamppost) {
                    addFeature(biome, io.github.frqnny.mostructures.ConfiguredFeatures.LAMPPOST);
                }
                break;
            default:
                //not included are THEEND and NONE because we don't need to be adding to them
                break;

        }
    }

    public static void putStructures(Biome biome) {
        Optional<Identifier> id = Optional.ofNullable(BuiltinRegistries.BIOME.getId(biome));
        if (id.isPresent()) {
            for (String string : full_list_of_banned_biomes) {
                if (id.toString().equals(string)) {
                    return;
                }
            }
        }

        category = biome.getCategory();
        switch (category) {
            case PLAINS:
                if (config.structures.barn_house) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.BARN_HOUSE, biome);
                }
                if (config.structures.villager_tower) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_TOWER, biome);
                }
                if (config.structures.villager_market) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_MARKET, biome);
                }
                if (config.structures.abandoned_churches) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.PLAINS_ABANDONED_CHURCH, biome);
                }
                break;
            case SAVANNA:
                if (config.structures.barn_house) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.BARN_HOUSE, biome);
                }
                if (config.structures.villager_tower) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_TOWER, biome);
                }
                if (config.structures.villager_market) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_MARKET, biome);
                }
                if (config.structures.abandoned_churches) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH, biome);
                }
                break;
            case DESERT:
                if (config.structures.big_pyramid) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.BIG_PYRAMID, biome);
                }
                if (config.structures.abandoned_churches) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.DESERT_ABANDONED_CHURCH, biome);
                }
                break;
            case JUNGLE:
                if (config.structures.jungle_pyramid) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.JUNGLE_PYRAMID, biome);
                }
                break;
            case BEACH:
                if (config.structures.the_castle_in_the_sky) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.THE_CASTLE_IN_THE_SKY, biome);
                }
                break;
            case FOREST:
                if (config.structures.villager_tower) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_TOWER, biome);
                }
                if (config.structures.villager_market) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.VILLAGER_MARKET, biome);
                }
                break;
            case ICY:
                if (config.structures.abandoned_churches) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.SNOWY_ABANDONED_CHURCH, biome);
                }
                break;
            case TAIGA:
                if (config.structures.abandoned_churches) {
                    addStructureToBiome(io.github.frqnny.mostructures.ConfiguredFeatures.TAIGA_ABANDONED_CHURCH, biome);
                }
                break;
            default:
                break;

        }
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

    private static void addFeature(Biome biome, ConfiguredFeature<?, ?> configuredFeature) {
        biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                .add(() -> configuredFeature);
    }

    private static void addStructureToBiome(ConfiguredStructureFeature<?, ?> feature, Biome biome) {
        biome.getGenerationSettings().getStructureFeatures().add(() -> feature);
    }

    public static StructureProcessorList register(String id, ImmutableList<StructureProcessor> processorList) {
        Identifier identifier = new Identifier(id);
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, identifier, structureProcessorList);
    }

    public static void addFeaturesAndStructuresToBiomes(Biome biome) {
        putFeatures(biome);
        putStructures(biome);
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

        //prepare the banned biomes list for structure addition later on
        //our biomes are added by the MinecraftServerMixin, which calls addFeaturesAndStructuresToBiome
        full_list_of_banned_biomes.addAll(Arrays.asList(always_banned_biomes));
        full_list_of_banned_biomes.addAll(Arrays.asList(config.biome_id_blacklist));
    }
}