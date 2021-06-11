package io.github.frqnny.mostructures;

import com.google.common.collect.ImmutableList;
import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.feature.MoaiFeature;
import io.github.frqnny.mostructures.feature.SmallAirFeature;
import io.github.frqnny.mostructures.feature.entity.VillagerEntityFeature;
import io.github.frqnny.mostructures.processor.*;
import io.github.frqnny.mostructures.structure.AirBalloonStructure;
import io.github.frqnny.mostructures.structure.MoaiStructure;
import io.github.frqnny.mostructures.structure.ModStructure;
import io.github.frqnny.mostructures.structure.VolcanicVentStructure;
import io.github.frqnny.mostructures.util.RegistrationHelper;
import io.github.frqnny.mostructures.util.StructureHelper;
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

import java.util.Locale;

public class MoStructures implements ModInitializer {


    public static final String MODID = "mostructures";
    public static final MoStructuresConfig config = OmegaConfig.register(MoStructuresConfig.class);

    public static final Feature<DefaultFeatureConfig> AIR_FEATURES = new SmallAirFeature();
    public static final Feature<DefaultFeatureConfig> SMALL_BEACH_FEATURES = new MoaiFeature();

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
    public static final StructureFeature<DefaultFeatureConfig> MOAI = new MoaiStructure();
    public static final StructureFeature<DefaultFeatureConfig> AIR_BALLOON = new AirBalloonStructure();

    public static final StructurePieceType VOLCANIC_VENT_TYPE = register(VolcanicVentStructure.Piece::new, "volcanic_vent_piece");
    public static final StructurePieceType MOAI_TYPE = register(MoaiStructure.Piece::new, "moai_structure_piece");
    public static final StructurePieceType AIR_BALLOON_TYPE = register(AirBalloonStructure.Piece::new, "air_balloon_piece");
    public static StructureProcessorType<SimpleStoneStructureProcessor> SIMPLE_STONE = StructureProcessorType.register("jungle_rot_processor", SimpleStoneStructureProcessor.CODEC);
    public static StructureProcessorType<SimpleCobblestoneProcessor> SIMPLE_COBBLESTONE = StructureProcessorType.register("simple_cobblestone", SimpleCobblestoneProcessor.CODEC);
    public static StructureProcessorType<DataBlockStructureProcessor> DATA_BLOCK_STRUCTURE_PROCESSOR = StructureProcessorType.register("data_block_structure_processor", DataBlockStructureProcessor.CODEC);
    public static StructureProcessorType<AirStructureProcessor> AIR_STRUCTURE_PROCESSOR = StructureProcessorType.register("air_structure_processor", AirStructureProcessor.CODEC);
    public static StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = StructureProcessorType.register("remove_waterlog_processor", RemoveWaterloggedProcessor.CODEC);
    public static StructureProcessorList JUNGLE_ROT_LIST = RegistrationHelper.registerStructureProcessorList("jungle_rot", ImmutableList.of(
            new SimpleStoneStructureProcessor(0.15F)
    ));
    public static StructureProcessorList ICE_TOWER_LIST = RegistrationHelper.registerStructureProcessorList("ice_tower_rot", ImmutableList.of(
            new SimpleStoneStructureProcessor(0)
    ));
    public static StructureProcessorList VILLAGER_TOWER_LIST = RegistrationHelper.registerStructureProcessorList("villager_tower_rot", ImmutableList.of(
            new SimpleCobblestoneProcessor(0.15F)
    ));
    public static StructureProcessorList PIRATE_SHIP_LIST = RegistrationHelper.registerStructureProcessorList("simple_air_keep_list", ImmutableList.of(
            new AirStructureProcessor(),
            new RemoveWaterloggedProcessor()
    ));

    private static void registerStructures() {
        FabricStructureBuilder.create(StructureHelper.BARN_HOUSE, BARN_HOUSE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.BARN_HOUSE), config.seperation(StructureHelper.BARN_HOUSE), config.salt(StructureHelper.BARN_HOUSE))
                .superflatFeature(ConfiguredFeatures.BARN_HOUSE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.BIG_PYRAMID, BIG_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.BIG_PYRAMID), config.seperation(StructureHelper.BIG_PYRAMID), config.salt(StructureHelper.BIG_PYRAMID))
                .superflatFeature(ConfiguredFeatures.BIG_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.JUNGLE_PYRAMID, JUNGLE_PYRAMID)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.JUNGLE_PYRAMID), config.seperation(StructureHelper.JUNGLE_PYRAMID), config.salt(StructureHelper.JUNGLE_PYRAMID))
                .superflatFeature(ConfiguredFeatures.JUNGLE_PYRAMID)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.THE_CASTLE_IN_THE_SKY, THE_CASTLE_IN_THE_SKY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.THE_CASTLE_IN_THE_SKY), config.seperation(StructureHelper.THE_CASTLE_IN_THE_SKY), config.salt(StructureHelper.THE_CASTLE_IN_THE_SKY))
                .superflatFeature(ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)
                .register();
        FabricStructureBuilder.create(StructureHelper.VILLAGER_TOWER, VILLAGER_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.VILLAGER_TOWER), config.seperation(StructureHelper.VILLAGER_TOWER), config.salt(StructureHelper.VILLAGER_TOWER))
                .superflatFeature(ConfiguredFeatures.VILLAGER_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.ABANDONED_CHURCH, ABANDONED_CHURCH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.ABANDONED_CHURCH), config.seperation(StructureHelper.ABANDONED_CHURCH), config.salt(StructureHelper.ABANDONED_CHURCH))
                .superflatFeature(ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.VILLAGER_MARKET, VILLAGER_MARKET)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.VILLAGER_MARKET), config.seperation(StructureHelper.VILLAGER_MARKET), config.salt(StructureHelper.VILLAGER_MARKET))
                .superflatFeature(ConfiguredFeatures.VILLAGER_MARKET)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.PILLAGER_FACTORY, PILLAGER_FACTORY)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.PILLAGER_FACTORY), config.seperation(StructureHelper.PILLAGER_FACTORY), config.salt(StructureHelper.PILLAGER_FACTORY))
                .superflatFeature(ConfiguredFeatures.PILLAGER_FACTORY)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.ICE_TOWER, ICE_TOWER)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.ICE_TOWER), config.seperation(StructureHelper.ICE_TOWER), config.salt(StructureHelper.ICE_TOWER))
                .superflatFeature(ConfiguredFeatures.ICE_TOWER)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.TAVERN, TAVERN)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.TAVERN), config.seperation(StructureHelper.TAVERN), config.salt(StructureHelper.TAVERN))
                .superflatFeature(ConfiguredFeatures.TAVERN)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.KILLER_BUNNY_CASTLE, KILLER_BUNNY_CASTLE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.KILLER_BUNNY_CASTLE), config.seperation(StructureHelper.KILLER_BUNNY_CASTLE), config.salt(StructureHelper.KILLER_BUNNY_CASTLE))
                .superflatFeature(ConfiguredFeatures.KILLER_BUNNY_CASTLE)
                .adjustsSurface()
                .register();
        FabricStructureBuilder.create(StructureHelper.PIRATE_SHIP, PIRATE_SHIP)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.PIRATE_SHIP), config.seperation(StructureHelper.PIRATE_SHIP), config.salt(StructureHelper.PIRATE_SHIP))
                .superflatFeature(ConfiguredFeatures.PIRATE_SHIP)
                .register();
        FabricStructureBuilder.create(StructureHelper.LIGHTHOUSE, LIGHTHOUSE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.LIGHTHOUSE), config.seperation(StructureHelper.LIGHTHOUSE), config.salt(StructureHelper.LIGHTHOUSE))
                .superflatFeature(ConfiguredFeatures.LIGHTHOUSE)
                .register();
        FabricStructureBuilder.create(StructureHelper.VOLCANIC_VENT, VOLCANIC_VENT)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.VOLCANIC_VENT), config.seperation(StructureHelper.VOLCANIC_VENT), config.salt(StructureHelper.VOLCANIC_VENT))
                .superflatFeature(ConfiguredFeatures.VOLCANIC_VENT)
                .register();
        FabricStructureBuilder.create(StructureHelper.MOAI, MOAI)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.MOAI), config.seperation(StructureHelper.MOAI), config.salt(StructureHelper.MOAI))
                .superflatFeature(ConfiguredFeatures.MOAI)
                .register();
        FabricStructureBuilder.create(StructureHelper.AIR_BALLOON, AIR_BALLOON)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(config.spacing(StructureHelper.AIR_BALLOON), config.seperation(StructureHelper.AIR_BALLOON), config.salt(StructureHelper.AIR_BALLOON))
                .superflatFeature(ConfiguredFeatures.AIR_BALLOON)
                .register();
    }

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, SmallAirFeature.ID, AIR_FEATURES);
        Registry.register(Registry.FEATURE, MoaiFeature.ID, SMALL_BEACH_FEATURES);
        Registry.register(Registry.FEATURE, VillagerEntityFeature.ID, VILLAGER_SPAWN);
    }


    public static void putStructures() {
        RegistrationHelper.addToBiome(
                StructureHelper.BARN_HOUSE,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.BARN_HOUSE)

        );

        RegistrationHelper.addToBiome(
                StructureHelper.BIG_PYRAMID,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()).and((context) -> !context.getBiomeKey().getValue().getPath().contains("lakes")),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.BIG_PYRAMID)

        );

        RegistrationHelper.addToBiome(
                StructureHelper.JUNGLE_PYRAMID,
                BiomeSelectors.categories(Biome.Category.JUNGLE).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.JUNGLE_PYRAMID)
        );

        RegistrationHelper.addToBiome(
                StructureHelper.THE_CASTLE_IN_THE_SKY,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.THE_CASTLE_IN_THE_SKY)

        );

        RegistrationHelper.addToBiome(
                StructureHelper.VILLAGER_TOWER,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.VILLAGER_TOWER)

        );
        RegistrationHelper.addToBiome(
                StructureHelper.VILLAGER_TOWER,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.SAVANNA_VILLAGER_TOWER)

        );

        RegistrationHelper.addToBiome(
                StructureHelper.VILLAGER_MARKET,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.FOREST).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.VILLAGER_MARKET)
        );

        RegistrationHelper.addToBiome(
                StructureHelper.PILLAGER_FACTORY,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(RegistrationHelper.getNoHillsPredicate()).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.PILLAGER_FACTORY)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.PIRATE_SHIP,
                BiomeSelectors.categories(Biome.Category.OCEAN).and((context) -> {
                    String string = context.getBiomeKey().getValue().toString();
                    return string.contains("deep") && !string.contains("frozen");
                }).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.PIRATE_SHIP)
        );

        RegistrationHelper.addToBiome(
                StructureHelper.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.PLAINS).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.PLAINS_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.DESERT).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.DESERT_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.SAVANNA_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.TAIGA).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.TAIGA_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.ABANDONED_CHURCH,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.SNOWY_ABANDONED_CHURCH)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.ICE_TOWER,
                BiomeSelectors.categories(Biome.Category.ICY).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.ICE_TOWER)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.TAVERN,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.TAVERN)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.KILLER_BUNNY_CASTLE,
                BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.SAVANNA).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.KILLER_BUNNY_CASTLE)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.LIGHTHOUSE,
                BiomeSelectors.categories(Biome.Category.BEACH).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.LIGHTHOUSE)
        );
        RegistrationHelper.addToBiome(
                StructureHelper.VOLCANIC_VENT,
                BiomeSelectors.categories(Biome.Category.OCEAN).and(RegistrationHelper.booleanToPredicate(config.activated(StructureHelper.BARN_HOUSE))).and(BiomeSelectors.excludeByKey(BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN)).and(BiomeSelectors.foundInOverworld()),
                (context) -> RegistrationHelper.addStructure(context, ConfiguredFeatures.VOLCANIC_VENT)
        );
    }

    public static Identifier id(String name) {
        return new Identifier(MODID, name.toLowerCase(Locale.ROOT));
    }

    static StructurePieceType register(StructurePieceType pieceType, String id) {
        return Registry.register(Registry.STRUCTURE_PIECE, id(id), pieceType);
    }

    @Override
    public void onInitialize() {
        registerStructures();
        registerFeatures();
        ConfiguredFeatures.registerConfiguredFeatures();

        putStructures();
    }



}