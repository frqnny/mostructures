package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.mixin.StructureFeatureAccessor;
import io.github.frqnny.mostructures.structure.*;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class Structures {
    public static StructurePieceType VOLCANIC_VENT_TYPE;

    public static final StructureFeature<ConfigMS> GENERIC = new ModStructure();

    public static final StructureFeature<ConfigMS> PIRATE_SHIP = new ModStructure(58, false, false);
    public static final StructureFeature<ConfigMS> LIGHTHOUSE = new ModStructure(65, false, false);
    public static final StructureFeature<DefaultFeatureConfig> VOLCANIC_VENT = new VolcanicVentStructure();
    public static final StructureFeature<ConfigMS> MOAI = new MoaiStructure(-3);
    public static final StructureFeature<ConfigMS> AIR_BALLOON = new AirBalloonStructure();
    public static final StructureFeature<ConfigMS> THE_CASTLE_IN_THE_SKY = new ModStructure(35);

    public static final TagKey<ConfiguredStructureFeature<?,?>> NO_LAKES = TagKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, MoStructures.id("no_lakes"));

    public static void init() {
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("generic"), GENERIC);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("pirate_ship"), PIRATE_SHIP);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("volcanic_vent"), VOLCANIC_VENT);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("lighthouse"), LIGHTHOUSE);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("moai"), MOAI);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("air_balloon"), AIR_BALLOON);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("the_castle_in_the_sky"), THE_CASTLE_IN_THE_SKY);

        VOLCANIC_VENT_TYPE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("volcanic_vent"), VolcanicVentStructure.Piece::new);

        StructureFeatureAccessor.getStep().put(GENERIC, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getStep().put(PIRATE_SHIP, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getStep().put(LIGHTHOUSE, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getStep().put(VOLCANIC_VENT, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        StructureFeatureAccessor.getStep().put(MOAI, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getStep().put(AIR_BALLOON, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getStep().put(THE_CASTLE_IN_THE_SKY, GenerationStep.Feature.SURFACE_STRUCTURES);



    }
}
