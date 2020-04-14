package io.github.franiscoder.mostructures.common.init;

import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.common.feature.FallenTreeFeature;
import io.github.franiscoder.mostructures.common.feature.HouseStructureFeature;
import io.github.franiscoder.mostructures.common.feature.SmallAirFeature;
import io.github.franiscoder.mostructures.common.feature.SmallDryFeature;
import io.github.franiscoder.mostructures.common.generator.HouseGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModStructures {
    public static final Feature<DefaultFeatureConfig> AIR_BALLOON = Registry.register(Registry.FEATURE, MoStructures.id("airballoon"), new SmallAirFeature(DefaultFeatureConfig::deserialize));
    public static final Feature<DefaultFeatureConfig> FALLEN_TREE = Registry.register(Registry.FEATURE, MoStructures.id("fallen_tree"), new FallenTreeFeature(DefaultFeatureConfig::deserialize));
    public static final Feature<DefaultFeatureConfig> SMALL_DESERT_FEATURES = Registry.register(Registry.FEATURE, MoStructures.id("dead_tree"), new SmallDryFeature(DefaultFeatureConfig::deserialize));


    public static StructureFeature<DefaultFeatureConfig> HOUSE = Registry.register(Registry.FEATURE, MoStructures.id("house"), new HouseStructureFeature(DefaultFeatureConfig::deserialize));
    public static final StructureFeature<?> myStructure = Registry.register(Registry.STRUCTURE_FEATURE, "house", HOUSE);

    public static StructurePieceType HOUSE_STRUCTURES = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("house_structures"), HouseGenerator.Piece::new);

    public static void init() {
        Registry.BIOME.forEach((Biome biome) -> {
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, AIR_BALLOON
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(2500)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, FALLEN_TREE
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(10)))
            );
            biome.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, SMALL_DESERT_FEATURES
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(500 / SmallDryFeature.IDENTIFIERS.length)))
            );


            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, HOUSE
                    .configure(FeatureConfig.DEFAULT)
                    .createDecoratedFeature(Decorator.NOPE.configure(NopeDecoratorConfig.DEFAULT))
            );


            biome.addStructureFeature(HOUSE.configure(FeatureConfig.DEFAULT));
        });

        Feature.STRUCTURES.put("housestructure", HOUSE);
    }
}
