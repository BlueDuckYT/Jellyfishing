package blueduck.jellyfishing.jellyfishingmod.items;

import blueduck.jellyfishing.jellyfishingmod.entities.AbstractJellyfishEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.IntNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

import java.util.Objects;
import java.util.function.Supplier;

public class JellyfishItem extends Item {


    Supplier<EntityType<?>> entityType;

    public JellyfishItem(Properties properties, Supplier<EntityType<?>> etype) {
        super(properties);
        entityType = etype;
    }




    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }

            Entity entity = this.entityType.get().spawn(world, itemstack, (PlayerEntity)null, blockpos1, SpawnReason.BUCKET, true, false);
            if (entity != null) {
                ((AbstractFishEntity)entity).setFromBucket(true);
            }
            if (!context.getPlayer().abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        ItemStack itemstack = playerIn.getHeldItem(handIn);
//        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
//        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
//            return ActionResult.resultPass(itemstack);
//        } else if (worldIn.isRemote) {
//            return ActionResult.resultSuccess(itemstack);
//        } else {
//            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
//            BlockPos blockpos = blockraytraceresult.getPos();
//            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
//                return ActionResult.resultPass(itemstack);
//            } else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
//                 if (false) {
//                    return ActionResult.resultFail(itemstack);
//                } else {
//                    if (!playerIn.abilities.isCreativeMode) {
//                        itemstack.shrink(1);
//                    }
//
//                    playerIn.addStat(Stats.ITEM_USED.get(this));
//                    return ActionResult.resultFail(itemstack);
//                }
//            } else {
//                return ActionResult.resultFail(itemstack);
//            }
//        }
//    }

}
