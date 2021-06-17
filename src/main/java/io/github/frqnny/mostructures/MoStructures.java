package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.feature.VillagerEntityFeature;
import io.github.frqnny.mostructures.processor.*;
import io.github.frqnny.mostructures.structure.*;
import io.github.frqnny.mostructures.util.RegUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Random;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";
    public static final Random random = new Random();
    public static final Feature<DefaultFeatureConfig> VILLAGER_SPAWN = new VillagerEntityFeature();

    public static final StructureFeature<StructurePoolFeatureConfig> BARN_HOUSE = new BarnHouseStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> BIG_PYRAMID = new BigPyramidStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> JUNGLE_PYRAMID = new JunglePyramidStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> THE_CASTLE_IN_THE_SKY = new TheCastleInTheSkyStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_TOWER = new VillagerTowerStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_MARKET = new VillagerMarketStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> PILLAGER_FACTORY = new PillagerFactoryStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ABANDONED_CHURCH = new AbandonedChurchStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> ICE_TOWER = new IceTowerStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> TAVERN = new TavernStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> KILLER_BUNNY_CASTLE = new KillerBunnyCastleStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> PIRATE_SHIP = new PirateShipStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> LIGHTHOUSE = new LighthouseStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> MOAI = new MoaiStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> AIR_BALLOON = new AirBalloonStructure();
    public static final StructureFeature<StructurePoolFeatureConfig> VILLAGER_BAZAAR = new VillagerBazaarStructure();
    public static final StructureFeature<DefaultFeatureConfig> VOLCANIC_VENT = new VolcanicVentStructure();

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

    public static MoStructuresConfig config;

    private static void registerStructures() {
        FabricStructureBuilder.create(BarnHouseStructure.ID, BARN_HOUSE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.barn_house_spacing, config.structureChances.barn_house_seperation, 165757306)
                .superflatFeature(ConfiguredFeatures.BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(BigPyramidStructure.ID, BIG_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.big_pyramid_spacing, config.structureChances.big_pyramid_seperation, 239284294)
                .superflatFeature(ConfiguredFeatures.BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(JunglePyramidStructure.ID, JUNGLE_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.jungle_pyramid_spacing, config.structureChances.jungle_pyramid_seperation, 312178642)
                .superflatFeature(ConfiguredFeatures.JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TheCastleInTheSkyStructure.ID, THE_CASTLE_IN_THE_SKY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.the_castle_in_the_sky_spacing, config.structureChances.the_castle_in_the_sky_seperation, 423494938)
                .superflatFeature(ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(VillagerTowerStructure.ID, VILLAGER_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_tower_spacing, config.structureChances.villager_tower_seperation, 550292492)
                .superflatFeature(ConfiguredFeatures.VILLAGER_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(AbandonedChurchStructure.ID, ABANDONED_CHURCH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.abandoned_church_spacing, config.structureChances.abandoned_church_seperation, 669968400)
                .superflatFeature(ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(VillagerMarketStructure.ID, VILLAGER_MARKET)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.villager_market_spacing, config.structureChances.villager_market_seperation, 784939542)
                .superflatFeature(ConfiguredFeatures.VILLAGER_MARKET)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(PillagerFactoryStructure.ID, PILLAGER_FACTORY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.pillager_factory_spacing, config.structureChances.pillager_factory_seperation, 839204924)
                .superflatFeature(ConfiguredFeatures.PILLAGER_FACTORY)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(IceTowerStructure.ID, ICE_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.ice_tower_spacing, config.structureChances.ice_tower_seperation, 964058305)
                .superflatFeature(ConfiguredFeatures.ICE_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(TavernStructure.ID, TAVERN)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.tavern_spacing, config.structureChances.tavern_seperation, 19296726)
                .superflatFeature(ConfiguredFeatures.TAVERN)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(KillerBunnyCastleStructure.ID, KILLER_BUNNY_CASTLE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.killer_bunny_castle_spacing, config.structureChances.killer_bunny_castle_seperation, 29573969)
                .superflatFeature(ConfiguredFeatures.KILLER_BUNNY_CASTLE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(PirateShipStructure.ID, PIRATE_SHIP)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.pirate_ship_spacing, config.structureChances.pirate_ship_seperation, 583957395)
                .superflatFeature(ConfiguredFeatures.PIRATE_SHIP)
                .register();
        FabricStructureBuilder.create(LighthouseStructure.ID, LIGHTHOUSE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.lighthouse_spacing, config.structureChances.lighthouse_seperation, 29502322)
                .superflatFeature(ConfiguredFeatures.LIGHTHOUSE)
                .register();
        FabricStructureBuilder.create(AirBalloonStructure.ID, AIR_BALLOON)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.air_balloon_spacing, config.structureChances.air_balloon_seperation, 12994829)
                .superflatFeature(ConfiguredFeatures.AIR_BALLOON)
                .register();
        FabricStructureBuilder.create(VillagerBazaarStructure.ID, VILLAGER_BAZAAR)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.village_bazaar_spacing, config.structureChances.village_bazaar_seperation, 34842291)
                .superflatFeature(ConfiguredFeatures.VILLAGE_BAZAAR)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(MoaiStructure.ID, MOAI)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.moai_spacing, config.structureChances.moai_seperation, 12994829)
                .superflatFeature(ConfiguredFeatures.MOAI)
                .register();
        FabricStructureBuilder.create(VolcanicVentStructure.ID, VOLCANIC_VENT)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.structureChances.volcanic_vent_spacing, config.structureChances.volcanic_vent_seperation, 84981094)
                .superflatFeature(ConfiguredFeatures.VOLCANIC_VENT)
                .adjustsSurface()
                .register();


    }

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, VillagerEntityFeature.ID, VILLAGER_SPAWN);
    }

    public static void putStructures() {
        RegUtils.addToBiome(
                BarnHouseStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.structures.barn_house)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.BARN_HOUSE)
        );
        RegUtils.addToBiome(
                BigPyramidStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegUtils.booleanToPredicate(config.structures.big_pyramid)).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()).and((context) -> !context.getBiomeKey().getValue().getPath().contains("lakes")),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.BIG_PYRAMID)
        );
        RegUtils.addToBiome(
                JunglePyramidStructure.ID,
                BiomeSelectors.categories(Biome.Category.JUNGLE).and(RegUtils.booleanToPredicate(config.structures.jungle_pyramid)).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.JUNGLE_PYRAMID)
        );
        RegUtils.addToBiome(
                TheCastleInTheSkyStructure.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.structures.the_castle_in_the_sky)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
        );
        RegUtils.addToBiome(
                VillagerTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegUtils.booleanToPredicate(config.structures.villager_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VILLAGER_TOWER)
        );
        RegUtils.addToBiome(
                VillagerTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.structures.villager_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.SAVANNA_VILLAGER_TOWER)
        );
        RegUtils.addToBiome(
                VillagerMarketStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.FOREST).and(RegUtils.booleanToPredicate(config.structures.villager_market)).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VILLAGER_MARKET)
        );
        RegUtils.addToBiome(
                PillagerFactoryStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.ICY).and(RegUtils.booleanToPredicate(config.structures.pillager_factory)).and(RegUtils.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.PILLAGER_FACTORY)
        );
        RegUtils.addToBiome(
                PirateShipStructure.ID,
                BiomeSelectors.categories(Biome.Category.OCEAN).and((context) -> {
                    String string = context.getBiomeKey().getValue().toString();
                    return string.contains("deep") && !string.contains("frozen");
                }).and(RegUtils.booleanToPredicate(config.structures.pirate_ship)),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.PIRATE_SHIP)
        );
        RegUtils.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS).and(RegUtils.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                AbandonedChurchStructure.ID,

                BiomeSelectors.categories(Biome.Category.DESERT).and(RegUtils.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.DESERT_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.TAIGA).and(RegUtils.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.TAIGA_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                AbandonedChurchStructure.ID,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegUtils.booleanToPredicate(config.structures.abandoned_churches)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.SNOWY_ABANDONED_CHURCH)
        );
        RegUtils.addToBiome(
                IceTowerStructure.ID,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegUtils.booleanToPredicate(config.structures.ice_tower)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.ICE_TOWER)
        );
        RegUtils.addToBiome(
                TavernStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegUtils.booleanToPredicate(config.structures.tavern)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.TAVERN)
        );
        RegUtils.addToBiome(
                KillerBunnyCastleStructure.ID,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.SAVANNA).and(RegUtils.booleanToPredicate(config.structures.the_castle_in_the_sky)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.KILLER_BUNNY_CASTLE)
        );
        RegUtils.addToBiome(
                LighthouseStructure.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.structures.lighthouse)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.LIGHTHOUSE)
        );
        RegUtils.addToBiome(
                VolcanicVentStructure.ID,
                BiomeSelectors.categories(Biome.Category.OCEAN).and(RegUtils.booleanToPredicate(config.structures.volcanic_vent)).and(BiomeSelectors.excludeByKey(BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VOLCANIC_VENT)
        );
        RegUtils.addToBiome(
                MoaiStructure.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.structures.moai)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.MOAI)
        );
        RegUtils.addToBiome(
                AirBalloonStructure.ID,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegUtils.booleanToPredicate(config.structures.air_balloons)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.AIR_BALLOON)
        );
        RegUtils.addToBiome(
                VillagerBazaarStructure.ID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegUtils.booleanToPredicate(config.structures.bazaar)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegUtils.addStructure(context, ConfiguredFeatures.VILLAGE_BAZAAR)
        );
    }

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    public static void registerConfig() {
        config = OmegaConfig.register(MoStructuresConfig.class);
    }


    @Override
    public void onInitialize() {
        registerConfig();

        registerStructures();
        registerFeatures();
        ConfiguredFeatures.registerConfiguredFeatures();

        putStructures();
    }
}