package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.structure.AirBalloonStructure;
import io.github.frqnny.mostructures.structure.MoaiStructure;
import io.github.frqnny.mostructures.structure.ModStructure;
import io.github.frqnny.mostructures.structure.VolcanicVentStructure;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class Structures {
    public static StructurePieceType VOLCANIC_VENT_TYPE;

    public static final StructureFeature<StructurePoolFeatureConfig> GENERIC = new ModStructure();

    public static final StructureFeature<StructurePoolFeatureConfig> PIRATE_SHIP = new ModStructure(58, false, false);
    public static final StructureFeature<StructurePoolFeatureConfig> LIGHTHOUSE = new ModStructure(65, false, false);
    public static final StructureFeature<DefaultFeatureConfig> VOLCANIC_VENT = new VolcanicVentStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> MOAI = new MoaiStructure(-3);
    public static final StructureFeature<StructurePoolFeatureConfig> AIR_BALLOON = new AirBalloonStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> THE_CASTLE_IN_THE_SKY = new ModStructure(35);

    public static void init() {
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("generic"), GENERIC);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("pirate_ship"), PIRATE_SHIP);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("volcanic_vent"), VOLCANIC_VENT);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("lighthouse"), LIGHTHOUSE);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("moai"), MOAI);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("air_balloon"), AIR_BALLOON);
        Registry.register(Registry.STRUCTURE_FEATURE, MoStructures.id("the_castle_in_the_sky"), THE_CASTLE_IN_THE_SKY);

        VOLCANIC_VENT_TYPE = Registry.register(Registry.STRUCTURE_PIECE, MoStructures.id("volcanic_vent"), VolcanicVentStructure.Piece::new);
    }
}
