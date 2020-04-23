package io.github.franiscoder.mostructures.common.init;

import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.common.feature.*;
import io.github.franiscoder.mostructures.common.generator.BarnHouseGenerator;
import io.github.franiscoder.mostructures.common.generator.PiglinOutpostGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModStructures {
    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("airballoon"), new SmallAirFeature());
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = Registry.register(Registry.FEATURE, MoStructures.id("fallen_tree"), new FallenTreeFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("dead_tree"), new SmallDryFeature());
    public static final Feature<DefaultFeatureConfig> RUINS = Registry.register(Registry.FEATURE, MoStructures.id("ruins"), new RuinsFeature());
    public static final Feature<DefaultFeatureConfig> LAMPPOST = Registry.register(Registry.FEATURE, MoStructures.id("lamppost"), new LamppostFeature());

    public static final StructureFeature<DefaultFeatureConfig> BARN_HOUSE = Registry.register(Registry.FEATURE, MoStructures.id("barn_house_feature"), new BarnHouseFeature());
    public static final StructurePieceType BARN_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("barn_house_piece"), BarnHouseGenerator.Piece::new);
    public static final StructureFeature<DefaultFeatureConfig> PIGLIN_OUTPOST = Registry.register(Registry.FEATURE, MoStructures.id("piglin_outpost_feature"), new PiglinOutpostFeature());
    public static final StructurePieceType PIGLIN_OUTPOST_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("piglin_outpost_piece"), PiglinOutpostGenerator.Piece::new);

    public static void init() {
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("barn_house_structure"), BARN_HOUSE);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("piglin_outpost_structure"), PIGLIN_OUTPOST);

        Registry.BIOME.forEach((Biome biome) -> {

            //Features
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_FEATURES
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(7500 / SmallAirFeature.AIR_FEATURES.length)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, FALLEN_TREE
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(11)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, SMALL_DESERT_FEATURES
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(500 / SmallDryFeature.IDENTIFIERS.length)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, RUINS
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(1000)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, LAMPPOST
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(100)))
            );

            //Structure Features
            //Needs to add to all biomes so it doesn't get cut off.
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, BARN_HOUSE
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.NOPE.configure(new NopeDecoratorConfig()))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, PIGLIN_OUTPOST
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.NOPE.configure(new NopeDecoratorConfig()))
            );
            //Register structures so it only spawns in said biomes.
            if (biome.getCategory() == Biome.Category.PLAINS || biome == Biomes.SAVANNA) {
                biome.addStructureFeature(BARN_HOUSE.configure(FeatureConfig.DEFAULT));
            }
            if (biome.getCategory() == Biome.Category.NETHER) {
                biome.addStructureFeature(PIGLIN_OUTPOST.configure(FeatureConfig.DEFAULT));
            }

        });

        //Locate Command
        Feature.STRUCTURES.put(MoStructures.MODID + ":barn_house", BARN_HOUSE);
        Feature.STRUCTURES.put(MoStructures.MODID + ":piglin_outpost", PIGLIN_OUTPOST);

    }
}
