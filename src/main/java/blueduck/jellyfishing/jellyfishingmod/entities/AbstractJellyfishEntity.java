package blueduck.jellyfishing.jellyfishingmod.entities;

import blueduck.jellyfishing.jellyfishingmod.items.JellyfishingSpawnEgg;
import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingEntities;
import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingItems;
import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import javax.annotation.Nullable;

public class AbstractJellyfishEntity extends AbstractFishEntity {

    public int stingTime;
    public int stingCounter;
    public int stingDmg;
    public double stingChance;
    public double dailyDrops;
    public int dropCounter;
    public double dodgeChance;
    public double dodgeSpeed;
    public double dirX, dirY, dirZ;
    public int moveCounter;


    public static final DamageSource JELLYFISH_STING = (new DamageSource("sting")).setDamageBypassesArmor();

    public ItemStack JELLYFISH_ITEM;
    public Item JELLY_ITEM;

    public boolean canThisEntitySting;


    public AbstractJellyfishEntity(EntityType<? extends AbstractFishEntity> type, World worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage, double stingChance, double dodgeChance, double dodgeSpeed) {
        super(type, worldIn);
        JELLYFISH_ITEM = JItem;
        JELLY_ITEM = JellyItem;
        stingTime = stingTicks;
        stingCounter = 0;
        dailyDrops = dropsPerDay;
        canThisEntitySting = canSting;
        stingDmg = stingDamage;
        this.stingChance = stingChance;
        this.dodgeChance = dodgeChance;
        this.dodgeSpeed = dodgeSpeed;
        this.readAdditional(this.getPersistentData());
        if (dropCounter <= 0) {
            dropCounter = (int) (worldIn.getRandom().nextDouble() * 24000 / dropsPerDay);
        }
        dirX = (worldIn.getRandom().nextDouble()) - .5;
        dirY = (worldIn.getRandom().nextDouble()) - .5;
        dirZ = (worldIn.getRandom().nextDouble()) - .5;
        this.writeAdditional(this.getPersistentData());
        moveCounter = 80;
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(Items.WATER_BUCKET, 1);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_COD_HURT;
    }


    public ItemStack getJellyfishItem() {
        return JELLYFISH_ITEM;
    }

    public void livingTick() {
        if (this.stingCounter > 0) {
            --this.stingCounter;
        }
        if (dropCounter == 0 && this.isInWater()) {
            this.entityDropItem(new ItemStack(JELLY_ITEM, 1), -0.5F);
            dropCounter = (int) (this.getEntityWorld().getRandom().nextDouble() * 24000 / dailyDrops);
        }
        if (dropCounter > 0) {
            dropCounter--;
        }
        if (moveCounter == 0 && this.isInWater()) {
            moveCounter = 80;
            this.setMotion(dirX, dirY, dirZ);
            dirX += (this.getEntityWorld().getRandom().nextDouble() * 0.2) - 0.1;
            dirY += (this.getEntityWorld().getRandom().nextDouble() * 0.2) - 0.1;
            dirZ += (this.getEntityWorld().getRandom().nextDouble() * 0.2) - 0.1;
            if (Math.abs(dirX) > 0.5) {
                dirX = Math.abs(dirX)/dirX * 0.5;
            }
            if (Math.abs(dirY) > 0.5) {
                dirY = Math.abs(dirY)/dirY * 0.5;
            }
            if (Math.abs(dirZ) > 0.5) {
                dirZ = Math.abs(dirZ)/dirZ * 0.5;
            }
        }
        else if (moveCounter > 0) {
            moveCounter--;
        }
        //this.setHeadRotation(90, (int)(Math.atan2(dirZ, dirX) * (180/Math.PI)));
        super.livingTick();
        if (this.onGround && !this.isInWater()) {
            this.setMotion(0.0, -0.3, 0.0);
            if (dirY > 0) {
                dirY *= -1;
            }
        }
        this.writeAdditional(this.getPersistentData());
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (canThisEntitySting && stingCounter == 0 && this.isInWater()) {
            stingCounter = stingTime;
            if (this.getEntityWorld().getRandom().nextDouble() < stingChance) {
                entityIn.attackEntityFrom(JELLYFISH_STING, stingDmg);
                this.playSound(JellyfishingSounds.STING.get(), 1, 1);
                this.setNewVelocity(entityIn, dodgeSpeed);
                this.writeAdditional(this.getPersistentData());
            }
        }
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    }

    public void setNewVelocity(Entity entityIn, double multiplier) {
        this.setMotion((this.getPosX() - entityIn.getPosX()) * multiplier, (this.getPosY() - entityIn.getPosY()) * multiplier, (this.getPosZ() - entityIn.getPosZ()) * multiplier);

    }

    protected void registerGoals() {
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == JellyfishingItems.JELLYFISH_NET.get() && this.isAlive() && player.getCooldownTracker().getCooldown(itemstack.getItem(), 0) == 0) {
            if (!this.canDespawn(1) || dodgeChance < this.getEntityWorld().getRandom().nextDouble()) {
                if (0.02 > this.getEntityWorld().getRandom().nextDouble()) {
                    this.entityDropItem(new ItemStack(JellyfishingItems.MUSIC_DISC_JELLYFISH_FIELDS.get(), 1), -0.5F);
                }
                player.getCooldownTracker().setCooldown(itemstack.getItem(), 20);
                this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, 1.0F);
                itemstack.damageItem(1, player, (p_220045_0_) -> {
                    p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                });
                ItemStack itemstack1 = this.getJellyfishItem();
                this.setBucketData(itemstack1);
                if (!this.world.isRemote) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, itemstack1);
                }

                if (itemstack.isEmpty()) {
                    player.setHeldItem(hand, itemstack1);
                } else if (!player.inventory.addItemStackToInventory(itemstack1)) {
                    player.dropItem(itemstack1, false);
                }

                this.remove();
                return true;
            } else {
                this.setNewVelocity(player, dodgeSpeed);
                player.getCooldownTracker().setCooldown(itemstack.getItem(), 20);
                return true;
            }
        } else {
            return super.processInteract(player, hand);
        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("StingTicks", stingCounter);
        compound.putInt("DropTicks", dropCounter);
        compound.putInt("MoveTicks", moveCounter);
        compound.putDouble("DirX", dirX);
        compound.putDouble("DirY", dirY);
        compound.putDouble("DirZ", dirZ);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.stingCounter = compound.getInt("StingTicks");
        this.dropCounter = compound.getInt("DropTicks");
        this.moveCounter = compound.getInt("MoveTicks");
        this.dirX = compound.getDouble("DirX");
        this.dirY = compound.getDouble("DirY");
        this.dirZ = compound.getDouble("DirZ");
    }
}
