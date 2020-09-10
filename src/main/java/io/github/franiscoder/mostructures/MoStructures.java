package io.github.franiscoder.mostructures;

import com.google.common.collect.ImmutableList;
import io.github.franiscoder.mostructures.config.MoStructuresConfig;
import io.github.franiscoder.mostructures.decorator.ChanceHeightmapDecorator;
import io.github.franiscoder.mostructures.feature.*;
import io.github.franiscoder.mostructures.generator.*;
import io.github.franiscoder.mostructures.structure.*;
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
import java.util.List;
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

    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> BARN_HOUSE = new BarnHouseStructure().configure(new StructurePoolFeatureConfig(pool(BarnHouseGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> BIG_PYRAMID = new BigPyramidStructure().configure(new StructurePoolFeatureConfig(pool(BigPyramidGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> JUNGLE_PYRAMID = new JunglePyramidStructure().configure(new StructurePoolFeatureConfig(pool(JunglePyramidGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> THE_CASTLE_IN_THE_SKY = new TheCastleInTheSkyStructure().configure(new StructurePoolFeatureConfig(pool(TheCastleInTheSkyGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGER_TOWER = new VillagerTowerStructure().configure(new StructurePoolFeatureConfig(pool(VillagerTowerGenerator.STARTING_POOL), 2));
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGER_MARKET = new VillagerMarketStructure().configure(new StructurePoolFeatureConfig(pool(VillagerMarketGenerator.STARTING_POOL), 2));

    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new AbandonedChurchStructure();

    public static final Decorator<ChanceDecoratorConfig> CHANCE_OCEAN_FLOOR_WG = Registry.register(Registry.DECORATOR, id("chance_heightmap_legacy"), new ChanceHeightmapDecorator());
    //Because Auto Config is a shithole, we need to ban biomes that no one will really need to activate biomes
    public static final String[] always_banned_biomes = {"hotm:thinking_forest"};
    public static final ArrayList<String> full_list_of_banned_biomes = new ArrayList<>(1);
    //fuck????????????
    private static final List<Biome> checkedBiomes = new ArrayList<>();
    public static Biome.Category category;
    private static MoStructuresConfig config;

    private static void registerStructures() {
        FabricStructureBuilder.create(MoStructures.id("barn_house"), BARN_HOUSE.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165755306)
                .superflatFeature(BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(MoStructures.id("big_pyramid"), BIG_PYRAMID.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 130284294)
                .superflatFeature(BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(MoStructures.id("jungle_pyramid"), JUNGLE_PYRAMID.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 112178942)
                .superflatFeature(JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(MoStructures.id("the_castle_in_the_sky"), THE_CASTLE_IN_THE_SKY.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 123474938)
                .superflatFeature(THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(MoStructures.id("villager_tower"), VILLAGER_TOWER.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_tower_spacing, config.structureChances.villager_tower_seperation, 150288492)
                .superflatFeature(VILLAGER_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(MoStructures.id("abandoned_church"), ABANDONED_CHURCH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.abandoned_church_spacing, config.structureChances.abandoned_church_seperation, 160468400)
                .superflatFeature(ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.PLAINS_STARTING_POOL), 2)))
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(MoStructures.id("villager_market"), VILLAGER_MARKET.feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 284039542)
                .superflatFeature(VILLAGER_MARKET)
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
        if (checkedBiomes.contains(biome)) {
            //Just to be sure we dont add the stuff twice to the same biome
            return;
        }
        checkedBiomes.add(biome);
        for (String string : full_list_of_banned_biomes) {
            Optional<Identifier> id = Optional.ofNullable(BuiltinRegistries.BIOME.getId(biome));
            if (id.isPresent()) {
                if (id.toString().equals(string)) {
                    return;
                }
            }
        }
        category = biome.getCategory();
        if (category == Biome.Category.NONE) {
            return;
        }
        //Overworld features
        if (category != Biome.Category.NETHER && category != Biome.Category.THEEND) {
            //get air features to spawn more in beaches

            if (config.features.air_features) {
                if (category == Biome.Category.BEACH) {
                    addFeature(biome, ConfiguredFeatures.AIR_FEATURES_BEACH);
                } else {
                    addFeature(biome, ConfiguredFeatures.AIR_FEATURES);
                }
            }


            if (config.features.fallen_trees) {
                addFeature(biome, ConfiguredFeatures.FALLEN_TREE);
            }
            if (config.features.desert_features && category == Biome.Category.DESERT) {
                addFeature(biome, ConfiguredFeatures.SMALL_DESERT_FEATURES);
            }
            if (config.features.ruins) {
                addFeature(biome, ConfiguredFeatures.RUINS);
            }
            if (config.features.boulder) {
                addFeature(biome, ConfiguredFeatures.BOULDER);
            }
            if (config.features.volcanic_vent && category == Biome.Category.OCEAN) {
                addFeature(biome, ConfiguredFeatures.VOLCANIC_VENT);
            }
            if (config.features.beach_features && category == Biome.Category.BEACH) {
                addFeature(biome, ConfiguredFeatures.SMALL_BEACH_FEATURES);
            }
            if (config.features.boats && category == Biome.Category.OCEAN) {
                addFeature(biome, ConfiguredFeatures.BOAT);
            }


        }
        if (category != Biome.Category.THEEND) {
            if (config.features.lamppost) {
                addFeature(biome, ConfiguredFeatures.LAMPPOST);
            }

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

        if (config.structures.barn_house && (category == Biome.Category.PLAINS || category == Biome.Category.SAVANNA)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> BARN_HOUSE);
        }

        if (config.structures.big_pyramid && category == Biome.Category.DESERT) {
            addStructureToBiome(BIG_PYRAMID, biome);
        }

        if (config.structures.jungle_pyramid && category == Biome.Category.JUNGLE) {
            addStructureToBiome(JUNGLE_PYRAMID, biome);
        }

        if (config.structures.the_castle_in_the_sky && category == Biome.Category.BEACH) {
            addStructureToBiome(THE_CASTLE_IN_THE_SKY, biome);
        }
        if (category == Biome.Category.PLAINS || category == Biome.Category.SAVANNA || category == Biome.Category.FOREST) {
            if (config.structures.villager_tower) {
                addStructureToBiome(VILLAGER_TOWER, biome);
            }
            if (config.structures.villager_market) {
                addStructureToBiome(VILLAGER_MARKET, biome);
            }
        }


        if (config.structures.abandoned_churches) {
            if (category == Biome.Category.PLAINS) {
                addStructureToBiome(ABANDONED_CHURCH
                                .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.PLAINS_STARTING_POOL), 2)),
                        biome
                );
            } else if (category == Biome.Category.SAVANNA) {
                addStructureToBiome(ABANDONED_CHURCH
                                .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.SAVANNA_STARTING_POOL), 2)),
                        biome
                );
            } else if (category == Biome.Category.DESERT) {
                addStructureToBiome(ABANDONED_CHURCH
                                .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.DESERT_STARTING_POOL), 2)),
                        biome
                );
            } else if (category == Biome.Category.ICY) {
                addStructureToBiome(ABANDONED_CHURCH
                                .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.SNOWY_STARTING_POOL), 2)),
                        biome
                );
            } else if (category == Biome.Category.TAIGA) {
                addStructureToBiome(ABANDONED_CHURCH
                                .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.TAIGA_STARTING_POOL), 2)),
                        biome
                );
            }
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
        AutoConfig.register(MoStructuresConfig.class, JanksonConfigSerializer::new);
        config = MoStructures.getConfig();

        registerStructures();
        registerFeatures();

        full_list_of_banned_biomes.addAll(Arrays.asList(always_banned_biomes));
        full_list_of_banned_biomes.addAll(Arrays.asList(config.biome_id_blacklist));
        //puts all the structures and features in each biome already registered.

    }
}
