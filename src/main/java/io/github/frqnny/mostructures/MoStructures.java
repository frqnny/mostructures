package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.config.StructureConfigEntry;
import io.github.frqnny.mostructures.feature.VillagerEntityFeature;
import io.github.frqnny.mostructures.processor.*;
import io.github.frqnny.mostructures.structure.AirBalloonStructure;
import io.github.frqnny.mostructures.structure.MoaiStructure;
import io.github.frqnny.mostructures.structure.ModStructure;
import io.github.frqnny.mostructures.structure.VolcanicVentStructure;
import io.github.frqnny.mostructures.util.RegUtils;
import io.github.frqnny.mostructures.util.StructureUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Locale;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";
    public static final MoStructuresConfig config = OmegaConfig.register(MoStructuresConfig.class);

    public static final Feature<DefaultFeatureConfig> VILLAGER_SPAWN = new VillagerEntityFeature();

    public static final StructureFeature<StructurePoolFeatureConfig> BARN_HOUSE = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> BIG_PYRAMID = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> JUNGLE_PYRAMID = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> THE_CASTLE_IN_THE_SKY = new ModStructure(60);
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_TOWER = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_MARKET = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> PILLAGER_FACTORY = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ICE_TOWER = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> TAVERN = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> KILLER_BUNNY_CASTLE = new ModStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> PIRATE_SHIP = new ModStructure(58, false, false);
    public static final StructureFeature<StructurePoolFeatureConfig> LIGHTHOUSE = new ModStructure(65, false, false);
    public static final StructureFeature<DefaultFeatureConfig> VOLCANIC_VENT = new VolcanicVentStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> MOAI = new MoaiStructure(-3);
    public static final StructureFeature<StructurePoolFeatureConfig> AIR_BALLOON = new AirBalloonStructure();

    public static final StructurePieceType VOLCANIC_VENT_TYPE = Registry.register(Registry.STRUCTURE_PIECE, id("volcanic_vent"), VolcanicVentStructure.Piece::new);
    public static final StructureProcessorType<SimpleStoneStructureProcessor> SIMPLE_STONE = StructureProcessorType.register("jungle_rot_processor", SimpleStoneStructureProcessor.CODEC);
    public static final StructureProcessorType<SimpleCobblestoneProcessor> SIMPLE_COBBLESTONE = StructureProcessorType.register("simple_cobblestone", SimpleCobblestoneProcessor.CODEC);
    public static final StructureProcessorType<DataBlockStructureProcessor> DATA_BLOCK_STRUCTURE_PROCESSOR = StructureProcessorType.register("data_block_structure_processor", DataBlockStructureProcessor.CODEC);
    public static final StructureProcessorType<AirStructureProcessor> AIR_STRUCTURE_PROCESSOR = StructureProcessorType.register("air_structure_processor", AirStructureProcessor.CODEC);
    public static final StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = StructureProcessorType.register("remove_waterlog_processor", RemoveWaterloggedProcessor.CODEC);
    public static final StructureProcessorList JUNGLE_ROT_LIST = RegUtils.registerStructureProcessorList("jungle_rot", ImmutableList.of(
            new SimpleStoneStructureProcessor(0.15F)
    ));
    public static final StructureProcessorList ICE_TOWER_LIST = RegUtils.registerStructureProcessorList("ice_tower_rot", ImmutableList.of(
            new SimpleStoneStructureProcessor(0)
    ));
    public static final StructureProcessorList VILLAGER_TOWER_LIST = RegUtils.registerStructureProcessorList("villager_tower_rot", ImmutableList.of(
            new SimpleCobblestoneProcessor(0.15F)
    ));
    public static final StructureProcessorList PIRATE_SHIP_LIST = RegUtils.registerStructureProcessorList("simple_air_keep_list", ImmutableList.of(
            new AirStructureProcessor(),
            new RemoveWaterloggedProcessor()
    ));

    private static void registerStructures() {
        RegUtils.registerStructure(StructureUtils.BIG_PYRAMID, BARN_HOUSE, ConfiguredFeatures.BARN_HOUSE);
        RegUtils.registerStructure(StructureUtils.BIG_PYRAMID, BIG_PYRAMID, ConfiguredFeatures.BIG_PYRAMID);
        RegUtils.registerStructure(StructureUtils.JUNGLE_PYRAMID, JUNGLE_PYRAMID, ConfiguredFeatures.JUNGLE_PYRAMID);
        RegUtils.registerStructure(StructureUtils.THE_CASTLE_IN_THE_SKY, THE_CASTLE_IN_THE_SKY, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY, false);
        RegUtils.registerStructure(StructureUtils.VILLAGER_TOWER, VILLAGER_TOWER, ConfiguredFeatures.VILLAGER_TOWER);
        RegUtils.registerStructure(StructureUtils.ABANDONED_CHURCH, ABANDONED_CHURCH, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH);
        RegUtils.registerStructure(StructureUtils.VILLAGER_MARKET, VILLAGER_MARKET, ConfiguredFeatures.VILLAGER_MARKET);
        RegUtils.registerStructure(StructureUtils.PILLAGER_FACTORY, PILLAGER_FACTORY, ConfiguredFeatures.PILLAGER_FACTORY);
        RegUtils.registerStructure(StructureUtils.ICE_TOWER, ICE_TOWER, ConfiguredFeatures.ICE_TOWER);
        RegUtils.registerStructure(StructureUtils.TAVERN, TAVERN, ConfiguredFeatures.TAVERN);
        RegUtils.registerStructure(StructureUtils.KILLER_BUNNY_CASTLE, KILLER_BUNNY_CASTLE, ConfiguredFeatures.KILLER_BUNNY_CASTLE);
        RegUtils.registerStructure(StructureUtils.PIRATE_SHIP, PIRATE_SHIP, ConfiguredFeatures.PIRATE_SHIP, false);
        RegUtils.registerStructure(StructureUtils.LIGHTHOUSE, LIGHTHOUSE, ConfiguredFeatures.LIGHTHOUSE, false);
        RegUtils.registerStructure(StructureUtils.VOLCANIC_VENT, VOLCANIC_VENT, ConfiguredFeatures.VOLCANIC_VENT, false);
        RegUtils.registerStructure(StructureUtils.MOAI, MOAI, ConfiguredFeatures.MOAI, false);
        RegUtils.registerStructure(StructureUtils.AIR_BALLOON, AIR_BALLOON, ConfiguredFeatures.AIR_BALLOON, false);

    }

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, VillagerEntityFeature.ID, VILLAGER_SPAWN);
    }


    public static void putStructures() {
        RegUtils.addToBiome(
                StructureUtils.BARN_HOUSE,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.BARN_HOUSE)

        );

        RegUtils.addToBiome(
                StructureUtils.BIG_PYRAMID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.BIG_PYRAMID))).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()).and((context) -> !context.getBiomeKey().getValue().getPath().contains("lakes")),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.BIG_PYRAMID)

        );

        RegUtils.addToBiome(
                StructureUtils.JUNGLE_PYRAMID,
                BiomeSelectors.categories(Biome.Category.JUNGLE).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.JUNGLE_PYRAMID))).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.JUNGLE_PYRAMID)
        );

        RegUtils.addToBiome(
                StructureUtils.THE_CASTLE_IN_THE_SKY,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.THE_CASTLE_IN_THE_SKY))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)

        );

        RegUtils.addToBiome(
                StructureUtils.VILLAGER_TOWER,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.VILLAGER_TOWER))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VILLAGER_TOWER)

        );
        RegUtils.addToBiome(
                StructureUtils.VILLAGER_TOWER,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.VILLAGER_TOWER))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.SAVANNA_VILLAGER_TOWER)

        );

        RegUtils.addToBiome(
                StructureUtils.VILLAGER_MARKET,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.FOREST).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.VILLAGER_MARKET))).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VILLAGER_MARKET)
        );

        RegUtils.addToBiome(
                StructureUtils.PILLAGER_FACTORY,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.ICY).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.PILLAGER_FACTORY))).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.PILLAGER_FACTORY)
        );
        RegUtils.addToBiome(
                StructureUtils.PIRATE_SHIP,
                BiomeSelectors.categories(Biome.Category.OCEAN).and((context) -> {
                    String string = context.getBiomeKey().getValue().toString();
                    return string.contains("deep") && !string.contains("frozen");
                }).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.PIRATE_SHIP))),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.PIRATE_SHIP)
        );

        RegUtils.addToBiome(
                StructureUtils.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.PLAINS).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.ABANDONED_CHURCH))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                StructureUtils.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.ABANDONED_CHURCH))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.DESERT_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                StructureUtils.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.ABANDONED_CHURCH))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                StructureUtils.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.TAIGA).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.ABANDONED_CHURCH))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.TAIGA_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                StructureUtils.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.ABANDONED_CHURCH))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.SNOWY_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                StructureUtils.ICE_TOWER,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.ICE_TOWER))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.ICE_TOWER)
        );
        RegUtils.addToBiome(
                StructureUtils.TAVERN,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.TAVERN))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.TAVERN)
        );
        RegUtils.addToBiome(
                StructureUtils.KILLER_BUNNY_CASTLE,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.KILLER_BUNNY_CASTLE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.KILLER_BUNNY_CASTLE)
        );
        RegUtils.addToBiome(
                StructureUtils.LIGHTHOUSE,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.LIGHTHOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.LIGHTHOUSE)
        );
        RegUtils.addToBiome(
                StructureUtils.VOLCANIC_VENT,
                BiomeSelectors.categories(Biome.Category.OCEAN).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.VOLCANIC_VENT))).and(BiomeSelectors.excludeByKey(BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VOLCANIC_VENT)
        );
        RegUtils.addToBiome(
                StructureUtils.MOAI,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.MOAI))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.MOAI)
        );
        RegUtils.addToBiome(
                StructureUtils.AIR_BALLOON,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.activated(StructureUtils.AIR_BALLOON))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.AIR_BALLOON)
        );
    }

    public static Identifier id(String name) {
        return new Identifier(MODID, name.toLowerCase(Locale.ROOT));
    }

    @Override
    public void onInitialize() {
        //Compute missing structure config entries
        StructureConfigEntry.computeConfigMap(config.structureConfigEntries);
        //Registering
        registerStructures();
        registerFeatures();
        ConfiguredFeatures.registerConfiguredFeatures();
        //Adding to biomes
        putStructures();
    }
}