package blueduck.jellyfishing.jellyfishingmod.features;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.block.TallSeaGrassBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.*;

import java.util.Random;
import java.util.function.Function;

public class CoralPlantFeature extends Feature<ProbabilityConfig> {
    public CoralPlantFeature(Codec<ProbabilityConfig> p_i231988_1_) {
        super(p_i231988_1_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
        int i = 0;

        for(int j = 0; j < config.probability; ++j) {
            int k = rand.nextInt(8) - rand.nextInt(8);
            int l = rand.nextInt(8) - rand.nextInt(8);
            int i1 = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + k, pos.getZ() + l);
            BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);
            BlockState blockstate = JellyfishingBlocks.CORAL_PLANT.get().getDefaultState();
            if (reader.getBlockState(blockpos).getBlock() == Blocks.WATER && blockstate.isValidPosition(reader, blockpos)) {
                reader.setBlockState(blockpos, blockstate, 2);
                ++i;
            }
        }

        return i > 0;
        }
    }

