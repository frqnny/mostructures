package io.github.franiscoder.mostructures.init;

import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.config.MoStructuresConfig;
import io.github.franiscoder.mostructures.feature.*;
import io.github.franiscoder.mostructures.generator.BarnHouseGenerator;
import io.github.franiscoder.mostructures.generator.BigPyramidGenerator;
import io.github.franiscoder.mostructures.generator.JunglePyramidGenerator;
import io.github.franiscoder.mostructures.generator.TheCastleInTheSkyGenerator;
import io.github.franiscoder.mostructures.structure.BarnHouseFeature;
import io.github.franiscoder.mostructures.structure.BigPyramidFeature;
import io.github.franiscoder.mostructures.structure.JunglePyramidFeature;
import io.github.franiscoder.mostructures.structure.TheCastleInTheSkyFeature;
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

    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("airballoon"), new SmallAirFeature());
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = Registry.register(Registry.FEATURE, MoStructures.id("fallen_tree"), new FallenTreeFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("dead_tree"), new SmallDryFeature());
    public static final Feature<DefaultFeatureConfig> RUINS = Registry.register(Registry.FEATURE, MoStructures.id("ruins"), new RuinsFeature());
    public static final Feature<DefaultFeatureConfig> LAMPPOST = Registry.register(Registry.FEATURE, MoStructures.id("lamppost"), new LamppostFeature());
    public static final Feature<DefaultFeatureConfig> BOULDER = Registry.register(Registry.FEATURE, MoStructures.id("boulder"), new BoulderFeature());
    public static final Feature<DefaultFeatureConfig> VOLCANIC_VENT = Registry.register(Registry.FEATURE, MoStructures.id("volcanic_vent"), new VolcanicVentFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_BEACH_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("beach_features"), new SmallBeachFeatures());
    public static final Feature<DefaultFeatureConfig> BOAT = Registry.register(Registry.FEATURE, MoStructures.id("boat"), new BoatFeature());


    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> BARN_HOUSE = register("barn_house_feature", new BarnHouseFeature(), 165755306, true);
    public static final StructurePieceType BARN_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("barn_house_piece"), BarnHouseGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> PYRAMID = register("big_pyramid_feature", new BigPyramidFeature(), 130284294, true);
    public static final StructurePieceType PYRAMID_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("big_pyramid_piece"), BigPyramidGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> JUNGLE_PYRAMID = register("jungle_pyramid_feature", new JunglePyramidFeature(), 112178942, true);
    public static final StructurePieceType JUNGLE_PYRAMID_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("jungle_pyramid_feature"), JunglePyramidGenerator.Piece::new);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> THE_CASTLE_IN_THE_SKY = register("the_castle_in_the_sky", new TheCastleInTheSkyFeature(), 123474938, false);
    public static final StructurePieceType THE_CASTLE_IN_THE_SKY_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("the_castle_in_the_sky_piece"), TheCastleInTheSkyGenerator.Piece::new);
    public static MoStructuresConfig config;

    private static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> register(String name, StructureFeature<DefaultFeatureConfig> structure, int salt, boolean surfaceAdjusting) {
        ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> configuredStructure = structure.configure(FeatureConfig.DEFAULT);
        if (surfaceAdjusting) {
            LibStructure.registerSurfaceAdjustingStructure(MoStructures.id(name), structure, GenerationStep.Feature.UNDERGROUND_DECORATION, new StructureConfig(32, 8, salt), structure.configure(FeatureConfig.DEFAULT));

        } else {
            LibStructure.registerStructure(MoStructures.id(name), structure, GenerationStep.Feature.UNDERGROUND_DECORATION, new StructureConfig(32, 8, salt), structure.configure(FeatureConfig.DEFAULT));
        }
        return configuredStructure;
    }

    public static void init() {
        config = MoStructures.getConfig();
        Registry.BIOME.forEach(StructureInit::handleBiome);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));

    }

    public static void handleBiome(Biome biome) {
        Biome.Category category = biome.getCategory();
        //Overworld features
        if (category != Biome.Category.NETHER && category != Biome.Category.THEEND) {
            if (config.air_features) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(6500 / SmallAirFeature.AIR_FEATURES.length)))
                );
            }
            if (config.fallen_trees) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, FALLEN_TREE
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(11)))
                );
            }
            if (config.desert_features && category == Biome.Category.DESERT) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, SMALL_DESERT_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(500 / SmallDryFeature.IDENTIFIERS.length)))
                );
            }
            if (config.ruins) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, RUINS
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(2500)))
                );
            }
            if (config.boulder) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, BOULDER
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(400)))
                );
            }
            if (config.volcanic_vent && category == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.RAW_GENERATION, VOLCANIC_VENT
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(90)))
                );
            }
            if (config.beach_features && category == Biome.Category.BEACH) {
                biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, SMALL_BEACH_FEATURES
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(20)))
                );
            }
            if (config.boats && category == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.RAW_GENERATION, BOAT
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(1000)))
                );
            }
            if (config.barn_house && (category == Biome.Category.PLAINS || category == Biome.Category.SAVANNA)) {
                biome.addStructureFeature(BARN_HOUSE);
            }
            if (config.jungle_pyramid && category == Biome.Category.JUNGLE) {
                biome.addStructureFeature(JUNGLE_PYRAMID);
            }
            if (config.big_pyramid && category == Biome.Category.DESERT) {
                biome.addStructureFeature(PYRAMID);
            }
            if (config.the_castle_in_the_sky && category == Biome.Category.BEACH) {
                biome.addStructureFeature(THE_CASTLE_IN_THE_SKY);
            }
        }
        //Lamppost & other non-end features
        if (category != Biome.Category.THEEND) {
            if (config.lamppost) {
                biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, LAMPPOST
                        .configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(85)))
                );
            }

        }
    }
}
