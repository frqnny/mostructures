package io.github.franiscoder.mostructures.common.init;

import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.common.feature.*;
import io.github.franiscoder.mostructures.common.generator.BarnHouseGenerator;
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
    public static final Feature<DefaultFeatureConfig> AIR_BALLOON = Registry.register(Registry.FEATURE, MoStructures.id("airballoon"), new SmallAirFeature());
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = Registry.register(Registry.FEATURE, MoStructures.id("fallen_tree"), new FallenTreeFeature());
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("dead_tree"), new SmallDryFeature());
    public static final Feature<DefaultFeatureConfig> RUINS = Registry.register(Registry.FEATURE, MoStructures.id("ruins"), new RuinsFeature());

    public static final StructureFeature<DefaultFeatureConfig> BARN_HOUSE = Registry.register(Registry.FEATURE, MoStructures.id("barn_house_feature"), new BarnHouseFeature());
    public static final StructurePieceType BARN_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("barn_house_piece"), BarnHouseGenerator.Piece::new);

    public static void init() {
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("barn_house_structure"), BARN_HOUSE);

        Registry.BIOME.forEach((Biome biome) -> {

            //Features
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_BALLOON
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(2500)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, FALLEN_TREE
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(11)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, SMALL_DESERT_FEATURES
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(500)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, RUINS
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(900)))
            );

            //Structure Features
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, BARN_HOUSE
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.NOPE.configure(new NopeDecoratorConfig()))
            );
            if (biome.getCategory() == Biome.Category.PLAINS || biome == Biomes.SAVANNA) {
                biome.addStructureFeature(BARN_HOUSE.configure(FeatureConfig.DEFAULT));
            }

        });

        //Locate Command
        Feature.STRUCTURES.put(MoStructures.MODID + ":barn_house", BARN_HOUSE);

    }
}
