package blueduck.jellyfishing.items;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.block.*;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Supplier;

import net.minecraft.item.Item.Properties;

public class JellyfishItem extends Item {


    Supplier<EntityType<?>> entityType;

    public JellyfishItem(Properties properties, Supplier<EntityType<?>> etype) {
        super(properties);
        entityType = etype;

        DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> entitytype = ((JellyfishItem) stack.getItem()).entityType.get();
                AbstractJellyfishEntity entity = (AbstractJellyfishEntity) entitytype.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                if (entity != null) {
                    ((AbstractFishEntity)entity).setFromBucket(true);
                }
                stack.shrink(1);
                return stack;
            }
        };
        if (!JellyfishingMod.CONFIG.NOPLACE_JELLYFISH.get()) {
            DispenserBlock.registerDispenseBehavior(this, defaultDispenseItemBehavior);
        }
    }




    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        if (world.isRemote || JellyfishingMod.CONFIG.NOPLACE_JELLYFISH.get()) {
            AbstractJellyfishEntity ent = (AbstractJellyfishEntity) this.entityType.get().create(world);
            player.entityDropItem(new ItemStack(ent.getJellyItem(), world.getRandom().nextInt(3) + 2), 0F);
            ent.remove();
            if (!player.abilities.isCreativeMode) {
                player.getHeldItem(hand).shrink(1);
            }
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

            Entity entity = this.entityType.get().spawn((ServerWorld) world, itemstack, (PlayerEntity)null, blockpos1, SpawnReason.BUCKET, true, false);
            if (entity != null) {
                ((AbstractFishEntity)entity).setFromBucket(true);
            }
            if (!context.getPlayer().abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (JellyfishingMod.CONFIG.NOPLACE_JELLYFISH.get()) {
            AbstractJellyfishEntity ent = (AbstractJellyfishEntity) this.entityType.get().create(worldIn);
            playerIn.entityDropItem(new ItemStack(ent.getJellyItem(), worldIn.getRandom().nextInt(3) + 2), 0F);
            ent.remove();
            if (!playerIn.abilities.isCreativeMode) {
                playerIn.getHeldItem(handIn).shrink(1);
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
