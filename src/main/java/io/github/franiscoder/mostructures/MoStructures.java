package io.github.franiscoder.mostructures;

import io.github.franiscoder.mostructures.config.MoStructuresConfig;
import io.github.franiscoder.mostructures.decorator.ChanceHeightmapDecorator;
import io.github.franiscoder.mostructures.feature.*;
import io.github.franiscoder.mostructures.generator.*;
import io.github.franiscoder.mostructures.structure.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.earthcomputer.libstructure.LibStructure;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";
    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("airballoon"), new SmallAirFeature());
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = Registry.register(Registry.FEATURE, MoStructures.id("fallen_tree"), new FallenTreeFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("dead_tree"), new SmallDryFeature());
    public static final Feature<DefaultFeatureConfig> RUINS = Registry.register(Registry.FEATURE, MoStructures.id("ruins"), new RuinsFeature());
    public static final Feature<DefaultFeatureConfig> LAMPPOST = Registry.register(Registry.FEATURE, MoStructures.id("lamppost"), new LamppostFeature());
    public static final Feature<DefaultFeatureConfig> BOULDER = Registry.register(Registry.FEATURE, MoStructures.id("boulder"), new BoulderFeature());
    public static final Feature<DefaultFeatureConfig> VOLCANIC_VENT = Registry.register(Registry.FEATURE, MoStructures.id("volcanic_vent"), new VolcanicVentFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_BEACH_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("beach_features"), new SmallBeachFeatures());
    public static final Feature<DefaultFeatureConfig> BOAT = Registry.register(Registry.FEATURE, MoStructures.id("boat"), new BoatFeature());

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
    public static Biome.Category category;
    private static MoStructuresConfig config;

    private static void registerStructures() {
        LibStructure.registerSurfaceAdjustingStructure(
                MoStructures.id("barn_house"),
                BARN_HOUSE.feature,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165755306),
                BARN_HOUSE
        );
        LibStructure.registerSurfaceAdjustingStructure(
                MoStructures.id("big_pyramid"),
                BIG_PYRAMID.feature,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 130284294),
                BIG_PYRAMID
        );
        LibStructure.registerSurfaceAdjustingStructure(
                MoStructures.id("jungle_pyramid"),
                JUNGLE_PYRAMID.feature,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 112178942),
                JUNGLE_PYRAMID
        );
        LibStructure.registerStructure(
                MoStructures.id("the_castle_in_the_sky"),
                THE_CASTLE_IN_THE_SKY.feature,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 123474938),
                THE_CASTLE_IN_THE_SKY
        );
        LibStructure.registerSurfaceAdjustingStructure(
                MoStructures.id("villager_tower"),
                VILLAGER_TOWER.feature,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.villager_tower_spacing, config.structureChances.villager_tower_seperation, 150288492),
                VILLAGER_TOWER
        );

        LibStructure.registerSurfaceAdjustingStructure(
                MoStructures.id("abandoned_church"),
                MoStructures.ABANDONED_CHURCH,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.abandoned_church_spacing, config.structureChances.abandoned_church_seperation, 160468400),
                MoStructures.ABANDONED_CHURCH.configure(
                        new StructurePoolFeatureConfig(
                                pool(AbandonedChurchGenerator.PLAINS_STARTING_POOL),
                                2)
                )
        );
        LibStructure.registerSurfaceAdjustingStructure(
                MoStructures.id("villager_market"),
                MoStructures.VILLAGER_MARKET.feature,
                GenerationStep.Feature.SURFACE_STRUCTURES,
                new StructureConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 284039542),
                VILLAGER_MARKET
        );
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
        if (category == Biome.Category.NONE) {
            return;
        }
        //Overworld features
        if (category != Biome.Category.NETHER && category != Biome.Category.THEEND) {
            //get air features to spawn more in beaches
            if (category == Biome.Category.BEACH) {
                if (config.features.air_features) {
                    biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_FEATURES
                            .configure(FeatureConfig.DEFAULT)
                            .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.air_feature_chance / (SmallAirFeature.AIR_FEATURES.length + 2))))
                    );
                }
            } else {
                if (config.features.air_features) {
                    biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_FEATURES
                            .configure(FeatureConfig.DEFAULT)
                            .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.air_feature_chance / SmallAirFeature.AIR_FEATURES.length)))
                    );
                }
            }

            if (config.features.fallen_trees) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, FALLEN_TREE
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.fallen_trees_chance)))
                );
            }
            if (config.features.desert_features && category == Biome.Category.DESERT) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, SMALL_DESERT_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.desert_features_chance)))
                );
            }
            if (config.features.ruins) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, RUINS
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.ruins_chance)))
                );
            }
            if (config.features.boulder) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, BOULDER
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.boulder_chance)))
                );
            }
            if (config.features.volcanic_vent && category == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.RAW_GENERATION, VOLCANIC_VENT
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.volcanic_vent_chance)))
                );
            }
            if (config.features.beach_features && category == Biome.Category.BEACH) {
                biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, SMALL_BEACH_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.beach_features_chance)))
                );
            }
            if (config.features.boats && category == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.RAW_GENERATION, BOAT
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.boats_chance)))
                );
            }


        }
        if (category != Biome.Category.THEEND) {
            if (config.features.lamppost) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, LAMPPOST
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(CHANCE_OCEAN_FLOOR_WG.configure(new ChanceDecoratorConfig(config.feature_chances.lamppost_chance)))
                );
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
            biome.addStructureFeature(BARN_HOUSE);
        }

        if (config.structures.big_pyramid && category == Biome.Category.DESERT) {
            biome.addStructureFeature(BIG_PYRAMID);
        }

        if (config.structures.jungle_pyramid && category == Biome.Category.JUNGLE) {
            biome.addStructureFeature(JUNGLE_PYRAMID);
        }

        if (config.structures.the_castle_in_the_sky && category == Biome.Category.BEACH) {
            biome.addStructureFeature(THE_CASTLE_IN_THE_SKY);
        }
        if (category == Biome.Category.PLAINS || category == Biome.Category.SAVANNA || category == Biome.Category.FOREST) {
            if (config.structures.villager_tower) {
                biome.addStructureFeature(VILLAGER_TOWER);
            }
            if (config.structures.villager_market) {
                biome.addStructureFeature(VILLAGER_MARKET);
            }
        }


        if (config.structures.abandoned_churches) {
            if (category == Biome.Category.PLAINS) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.PLAINS_STARTING_POOL), 2))
                );
            } else if (category == Biome.Category.SAVANNA) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.SAVANNA_STARTING_POOL), 2))
                );
            } else if (category == Biome.Category.DESERT) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.DESERT_STARTING_POOL), 2))
                );
            } else if (category == Biome.Category.ICY) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.SNOWY_STARTING_POOL), 2))
                );
            } else if (category == Biome.Category.TAIGA) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(pool(AbandonedChurchGenerator.TAIGA_STARTING_POOL), 2))
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

    @Override
    public void onInitialize() {
        AutoConfig.register(MoStructuresConfig.class, JanksonConfigSerializer::new);
        config = MoStructures.getConfig();

        registerStructures();

        full_list_of_banned_biomes.addAll(Arrays.asList(always_banned_biomes));
        full_list_of_banned_biomes.addAll(Arrays.asList(config.biome_id_blacklist));
        //puts all the structures and features in each biome already registered.

        for (Biome biome : BuiltinRegistries.BIOME) {
            putFeatures(biome);
            putStructures(biome);
        }

        //mods that add biomes later are also added here
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> putFeatures(biome));
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> putStructures(biome));
    }
}
