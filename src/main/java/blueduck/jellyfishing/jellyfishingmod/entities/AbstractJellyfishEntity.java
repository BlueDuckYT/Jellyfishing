package blueduck.jellyfishing.jellyfishingmod.entities;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingItems;
import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import javax.annotation.Nullable;

public class AbstractJellyfishEntity extends AbstractFishEntity {

    public int stingTime;
    public int stingCounter;
    public double dailyDrops;
    public int dropCounter;

    public static final DamageSource JELLYFISH_STING = (new DamageSource("sting")).setDamageBypassesArmor();

    public ItemStack JELLYFISH_ITEM;
    public Item JELLY_ITEM;

    public boolean canThisEntitySting;


    public AbstractJellyfishEntity(EntityType<? extends AbstractFishEntity> type, World worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage) {
        super(type, worldIn);
        JELLYFISH_ITEM = JItem;
        JELLY_ITEM = JellyItem;
        stingTime = stingTicks;
        stingCounter = 0;
        dailyDrops = dropsPerDay;
        dropCounter = (int) (Math.random() * 24000 / dropsPerDay);
        canThisEntitySting = canSting;
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(Items.WATER_BUCKET, 1);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }


    public ItemStack getJellyfishItem() {
        return JELLYFISH_ITEM;
    }

    public void livingTick() {
        if (this.stingCounter > 0) {
            --this.stingCounter;
        }
        if (dropCounter == 0) {
            this.entityDropItem(new ItemStack(JELLY_ITEM, 1), -0.5F);
            dropCounter = (int) (Math.random() * 24000 / dailyDrops);
        }
        dropCounter--;
        super.livingTick();
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (canThisEntitySting && stingCounter == 0 && this.isInWater()) {
            stingCounter = stingTime;
            entityIn.attackEntityFrom(JELLYFISH_STING, 3);
            this.playSound(JellyfishingSounds.STING.get(), 1, 1);
        }
    }


    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == JellyfishingItems.JELLYFISH_NET.get() && this.isAlive()) {
            this.playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0F, 1.0F);
            itemstack.damageItem(1, player, (p_220045_0_) -> {
                p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
            ItemStack itemstack1 = this.getJellyfishItem();
            this.setBucketData(itemstack1);
            if (!this.world.isRemote) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)player, itemstack1);
            }

            if (itemstack.isEmpty()) {
                player.setHeldItem(hand, itemstack1);
            } else if (!player.inventory.addItemStackToInventory(itemstack1)) {
                player.dropItem(itemstack1, false);
            }

            this.remove();
            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }





}
