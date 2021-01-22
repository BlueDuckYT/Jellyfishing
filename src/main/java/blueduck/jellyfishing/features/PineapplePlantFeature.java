package blueduck.jellyfishing.features;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;

public class PineapplePlantFeature extends Feature<ProbabilityConfig> {
    public PineapplePlantFeature(Codec<ProbabilityConfig> p_i231988_1_) {
        super(p_i231988_1_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
        int i = 0;

        for(int j = 0; j < config.probability; ++j) {
            int k = rand.nextInt(8) - rand.nextInt(8);
            int l = rand.nextInt(8) - rand.nextInt(8);
            int i1 = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX() + k, pos.getZ() + l);
            BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);
            BlockState blockstate = JellyfishingBlocks.PINEAPPLE_PLANT.get().getDefaultState().with(BlockStateProperties.AGE_0_3, Integer.valueOf(rand.nextInt(2) + 2));
            if (blockstate.isValidPosition(reader, blockpos)) {
                reader.setBlockState(blockpos, blockstate, 2);
                ++i;
            }
        }

        return i > 0;
    }
}
