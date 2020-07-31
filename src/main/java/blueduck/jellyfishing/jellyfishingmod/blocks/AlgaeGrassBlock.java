package blueduck.jellyfishing.jellyfishingmod.blocks;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AlgaeGrassBlock extends Block {

    public AlgaeGrassBlock(Properties properties) {
        super(properties);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (worldIn.getBlockState(pos.up()).isSolid()) {
            worldIn.setBlockState(pos, state);
        }
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        this.tick(state, worldIn, pos, random);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (worldIn.getBlockState(pos.up()).isSolid()) {
            worldIn.setBlockState(pos, state);
        }
    }



}
