package blueduck.jellyfishing.blocks;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class JellyBlock extends HoneyBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public JellyBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    private static boolean doesEntityDoHoneyBlockSlideEffects(Entity p_226937_0_) {
        return p_226937_0_ instanceof LivingEntity || p_226937_0_ instanceof AbstractMinecartEntity || p_226937_0_ instanceof TNTEntity || p_226937_0_ instanceof BoatEntity;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0F, 1.0F);
        if (!worldIn.isRemote) {
            worldIn.setEntityState(entityIn, (byte)54);
        }

        if (entityIn.onLivingFall(fallDistance, 0.2F)) {
            entityIn.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5F, this.soundType.getPitch() * 0.75F);
        }

    }

//    @Override
//    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
//        if (this.isSlidingDown(pos, entityIn)) {
//            this.maybeDoSlideAchievement(entityIn, pos);
//            this.doSlideMovement(entityIn);
//            this.maybeDoSlideEffects(worldIn, entityIn);
//        }
//
//        super.onEntityCollision(state, worldIn, pos, entityIn);
//    }
//
//    private boolean isSlidingDown(BlockPos p_226935_1_, Entity p_226935_2_) {
//        if (p_226935_2_.onGround) {
//            return false;
//        } else if (p_226935_2_.getPosY() > (double)p_226935_1_.getY() + 0.9375D - 1.0E-7D) {
//            return false;
//        } else if (p_226935_2_.getMotion().y >= -0.08D) {
//            return false;
//        } else {
//            double d0 = Math.abs((double)p_226935_1_.getX() + 0.5D - p_226935_2_.getPosX());
//            double d1 = Math.abs((double)p_226935_1_.getZ() + 0.5D - p_226935_2_.getPosZ());
//            double d2 = 0.4375D + (double)(p_226935_2_.getWidth() / 2.0F);
//            return d0 + 1.0E-7D > d2 || d1 + 1.0E-7D > d2;
//        }
//    }
//
//    private void maybeDoSlideAchievement(Entity p_226933_1_, BlockPos p_226933_2_) {
//        if (p_226933_1_ instanceof ServerPlayerEntity && p_226933_1_.world.getGameTime() % 20L == 0L) {
//            CriteriaTriggers.HONEY_BLOCK_SLIDE.trigger((ServerPlayerEntity)p_226933_1_, p_226933_1_.world.getBlockState(p_226933_2_));
//        }
//
//    }

    private void doSlideMovement(Entity p_226938_1_) {
        Vector3d vec3d = p_226938_1_.getMotion();
        if (vec3d.y < -0.13D) {
            double d0 = -0.05D / vec3d.y;
            p_226938_1_.setMotion(new Vector3d(vec3d.x * d0, -0.05D, vec3d.z * d0));
        } else {
            p_226938_1_.setMotion(new Vector3d(vec3d.x, -0.05D, vec3d.z));
        }

        p_226938_1_.fallDistance = 0.0F;
    }

    private void maybeDoSlideEffects(World p_226934_1_, Entity p_226934_2_) {
        if (doesEntityDoHoneyBlockSlideEffects(p_226934_2_)) {
            if (p_226934_1_.rand.nextInt(5) == 0) {
                p_226934_2_.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0F, 1.0F);
            }

            if (!p_226934_1_.isRemote && p_226934_1_.rand.nextInt(5) == 0) {
                p_226934_1_.setEntityState(p_226934_2_, (byte)53);
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static void showSlideParticles(Entity p_226931_0_) {
        showParticles(p_226931_0_, 5);
    }

    @OnlyIn(Dist.CLIENT)
    public static void showJumpParticles(Entity p_226936_0_) {
        showParticles(p_226936_0_, 10);
    }

    @OnlyIn(Dist.CLIENT)
    private static void showParticles(Entity p_226932_0_, int p_226932_1_) {
        if (p_226932_0_.world.isRemote) {
            BlockState blockstate = JellyfishingBlocks.JELLY_BLOCK.get().getDefaultState();

            for(int i = 0; i < p_226932_1_; ++i) {
                p_226932_0_.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate), p_226932_0_.getPosX(), p_226932_0_.getPosY(), p_226932_0_.getPosZ(), 0.0D, 0.0D, 0.0D);
            }

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
        if (BlockTags.makeWrapperTag("jellyfishing:jelly_blocks").contains(other.getBlock()) && state != other) {
            return false;
        }
        return true;
    }

//    @OnlyIn(Dist.CLIENT)
//    public static void entitySlideParticles(Entity entity) {
//        slideParticles(entity, 5);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public static void livingSlideParticles(Entity entity) {
//        slideParticles(entity, 10);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    private static void slideParticles(Entity entity, int particleCount) {
//        if (entity.world.isRemote) {
//            BlockState blockstate = JellyfishingBlocks.JELLY_BLOCK.get().getDefaultState();
//
//            for(int i = 0; i < particleCount; ++i) {
//                entity.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate), entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
//            }
//
//        }
//    }
}