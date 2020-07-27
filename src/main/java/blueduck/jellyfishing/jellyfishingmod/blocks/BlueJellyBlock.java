package blueduck.jellyfishing.jellyfishingmod.blocks;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlueJellyBlock extends SlimeBlock {
    public BlueJellyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        if (entityIn.isSuppressingBounce()) {
            super.onLanded(worldIn, entityIn);
        } else {
            this.func_226946_a_(entityIn);
        }

    }

    private void func_226946_a_(Entity p_226946_1_) {
        Vec3d vec3d = p_226946_1_.getMotion();
        if (vec3d.y < 0.0D) {
            double d0 = p_226946_1_ instanceof LivingEntity ? 1.0D : 0.8D;
            p_226946_1_.setMotion(vec3d.x, -vec3d.y * d0 * 1.2, vec3d.z);
        }

    }

    @Override
    public boolean isStickyBlock(BlockState state)
    {
        return true;
    }


    @Override
    public boolean canStickTo(BlockState state, BlockState other)
    {
        if (other.getBlock() == JellyfishingBlocks.JELLY_BLOCK.get() || state.getBlock() == JellyfishingBlocks.JELLY_BLOCK.get()) {
            return false;
        }
        return true;
    }

}
