package io.github.franiscoder.mostructures.init;

import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.config.MoStructuresConfig;
import io.github.franiscoder.mostructures.feature.*;
import io.github.franiscoder.mostructures.generator.*;
import io.github.franiscoder.mostructures.structure.*;
import net.earthcomputer.libstructure.LibStructure;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;

public class StructureInit {

    //Features
    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("airballoon"), new SmallAirFeature());
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = Registry.register(Registry.FEATURE, MoStructures.id("fallen_tree"), new FallenTreeFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("dead_tree"), new SmallDryFeature());
    public static final Feature<DefaultFeatureConfig> RUINS = Registry.register(Registry.FEATURE, MoStructures.id("ruins"), new RuinsFeature());
    public static final Feature<DefaultFeatureConfig> LAMPPOST = Registry.register(Registry.FEATURE, MoStructures.id("lamppost"), new LamppostFeature());
    public static final Feature<DefaultFeatureConfig> BOULDER = Registry.register(Registry.FEATURE, MoStructures.id("boulder"), new BoulderFeature());
    public static final Feature<DefaultFeatureConfig> VOLCANIC_VENT = Registry.register(Registry.FEATURE, MoStructures.id("volcanic_vent"), new VolcanicVentFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_BEACH_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("beach_features"), new SmallBeachFeatures());
    public static final Feature<DefaultFeatureConfig> BOAT = Registry.register(Registry.FEATURE, MoStructures.id("boat"), new BoatFeature());

    //Default Structures
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> BARN_HOUSE = register("barn_house_feature", new BarnHouseStructure(), 165755306, true, false);
    public static final StructurePieceType BARN_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("barn_house_piece"), BarnHouseGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> PYRAMID = register("big_pyramid_feature", new BigPyramidStructure(), 130284294, true, true);
    public static final StructurePieceType PYRAMID_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("big_pyramid_piece"), BigPyramidGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> JUNGLE_PYRAMID = register("jungle_pyramid_feature", new JunglePyramidStructure(), 112178942, true, true);
    public static final StructurePieceType JUNGLE_PYRAMID_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("jungle_pyramid_feature"), JunglePyramidGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> THE_CASTLE_IN_THE_SKY = register("the_castle_in_the_sky", new TheCastleInTheSkyStructure(), 123474938, false, true);
    public static final StructurePieceType THE_CASTLE_IN_THE_SKY_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("the_castle_in_the_sky_piece"), TheCastleInTheSkyGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> VILLAGER_TOWER = register("villager_tower", new VillagerTowerStructure(), 150288492, true, false);
    public static final StructurePieceType VILLAGER_TOWER_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("villager_tower_piece"), VillagerTowerGenerator.Piece::new);

    //Structures (biome specific design)
    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new AbandonedChurchStructure();
    public static final StructurePieceType ABANDONED_CHURCH_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("abandoned_church_piece"), AbandonedChurchGenerator.Piece::new);

    public static MoStructuresConfig config;
    public static Biome.Category category;


    private static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> register(String name, StructureFeature<DefaultFeatureConfig> structure, int salt, boolean surfaceAdjusting, boolean singleBiome) {
        ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> configuredStructure = structure.configure(FeatureConfig.DEFAULT);
        if (surfaceAdjusting) {
            LibStructure.registerSurfaceAdjustingStructure(MoStructures.id(name), structure, GenerationStep.Feature.UNDERGROUND_DECORATION, singleBiome? new StructureConfig(32, 8, salt) : new StructureConfig(40, 16, salt), structure.configure(FeatureConfig.DEFAULT));

        } else {
            LibStructure.registerStructure(MoStructures.id(name), structure, GenerationStep.Feature.UNDERGROUND_DECORATION, new StructureConfig(32, 16, salt), structure.configure(FeatureConfig.DEFAULT));
        }
        return configuredStructure;
    }

    private static void registerSpecialStructures() {
        ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> configuredStructure = StructureInit.ABANDONED_CHURCH.configure(new StructurePoolFeatureConfig(AbandonedChurchGenerator.PLAINS_PLATE, 2));
        LibStructure.registerSurfaceAdjustingStructure(MoStructures.id("abandoned_church"), StructureInit.ABANDONED_CHURCH, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(32, 8, 160468400), configuredStructure);
    }

    public static void init() {
        //i'll fix this up before release
        config = MoStructures.getConfig();
        registerSpecialStructures();
        Registry.BIOME.forEach(StructureInit::putFeatures);
        Registry.BIOME.forEach(StructureInit::putStructures);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> putFeatures(biome));
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> putStructures(biome));

    }

    public static void putFeatures(Biome biome) {
        category = biome.getCategory();
        //Overworld features
        if (category != Biome.Category.NETHER && category != Biome.Category.THEEND) {
            if (config.features.air_features) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.air_feature_chance / SmallAirFeature.AIR_FEATURES.length)))
                );
            }
            if (config.features.fallen_trees) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, FALLEN_TREE
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.fallen_trees_chance)))
                );
            }
            if (config.features.desert_features && category == Biome.Category.DESERT) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, SMALL_DESERT_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.desert_features_chance / SmallDryFeature.IDENTIFIERS.length)))
                );
            }
            if (config.features.ruins) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, RUINS
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.ruins_chance)))
                );
            }
            if (config.features.boulder) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, BOULDER
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.boulder_chance)))
                );
            }
            if (config.features.volcanic_vent && category == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.RAW_GENERATION, VOLCANIC_VENT
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.volcanic_vent_chance)))
                );
            }
            if (config.features.beach_features && category == Biome.Category.BEACH) {
                biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, SMALL_BEACH_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.beach_features_chance)))
                );
            }
            if (config.features.boats && category == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.RAW_GENERATION, BOAT
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.boats_chance)))
                );
            }


        }
        //Lamppost & other non-end features
        if (category != Biome.Category.THEEND) {
            if (config.features.lamppost) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, LAMPPOST
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(config.feature_chances.lamppost_chance)))
                );
            }

        }
    }

    public static void putStructures(Biome biome) {
        category = biome.getCategory();

        if (config.structures.barn_house && (category == Biome.Category.PLAINS || category == Biome.Category.SAVANNA)) {
            biome.addStructureFeature(BARN_HOUSE);
        }
        if (config.structures.jungle_pyramid && category == Biome.Category.JUNGLE) {
            biome.addStructureFeature(JUNGLE_PYRAMID);
        }
        if (config.structures.big_pyramid && category == Biome.Category.DESERT) {
            biome.addStructureFeature(PYRAMID);
        }
        if (config.structures.the_castle_in_the_sky && category == Biome.Category.BEACH) {
            biome.addStructureFeature(THE_CASTLE_IN_THE_SKY);
        }
        if (config.structures.villager_tower && (category == Biome.Category.PLAINS || category == Biome.Category.SAVANNA || category == Biome.Category.FOREST)) {
            biome.addStructureFeature(VILLAGER_TOWER);
        }


        if (config.structures.abandoned_churches) {
            if (category == Biome.Category.PLAINS) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(AbandonedChurchGenerator.PLAINS_PLATE, 2))
                );
            } else if (category == Biome.Category.SAVANNA) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(AbandonedChurchGenerator.SAVANNA_PLATE, 2))
                );
            } else if (category == Biome.Category.DESERT) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(AbandonedChurchGenerator.DESERT_PLATE, 2))
                );
            } else if (category == Biome.Category.ICY) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(AbandonedChurchGenerator.SNOWY_PLATE, 2))
                );
            } else if (category == Biome.Category.TAIGA) {
                biome.addStructureFeature(ABANDONED_CHURCH
                        .configure(new StructurePoolFeatureConfig(AbandonedChurchGenerator.TAIGA_PLATE, 2))
                );
            }
        }
    }
}
