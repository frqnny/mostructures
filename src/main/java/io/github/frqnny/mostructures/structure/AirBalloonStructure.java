package io.github.frqnny.mostructures.structure;

import io.github.frqnny.mostructures.util.StrucUtils;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.random.AtomicSimpleRandom;
import net.minecraft.world.gen.random.ChunkRandom;

import java.util.Optional;

public class AirBalloonStructure extends StructureFeature<StructurePoolFeatureConfig> {
    public AirBalloonStructure() {
        super(StructurePoolFeatureConfig.CODEC, context -> {
            if (!ModStructure.canGenerate(context)) {
                return Optional.empty();
            }

            StrucUtils.initPools();

            ChunkRandom chunkRandom = new ChunkRandom(new AtomicSimpleRandom(0L));
            chunkRandom.setCarverSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);

            BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), Math.max(chunkRandom.nextInt(100), 45), context.chunkPos().getStartZ());
            return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockPos, true, true);
        });
    }
}