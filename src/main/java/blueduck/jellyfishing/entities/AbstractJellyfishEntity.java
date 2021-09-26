package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.items.SandySuitItem;
import blueduck.jellyfishing.registry.JellyfishingEnchantments;
import blueduck.jellyfishing.registry.JellyfishingItems;
import blueduck.jellyfishing.registry.JellyfishingSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

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
    public int moveTime;


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
        moveTime = 80;
        this.rotationYaw = 0;
    }
    public AbstractJellyfishEntity(EntityType<? extends AbstractFishEntity> type, World worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage, double stingChance, double dodgeChance, double dodgeSpeed, int moveTicks) {
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
            dropCounter = (int) (24000 / dropsPerDay);
        }
        dirX = (worldIn.getRandom().nextDouble()) - .5;
        dirY = (worldIn.getRandom().nextDouble()) - .5;
        dirZ = (worldIn.getRandom().nextDouble()) - .5;
        this.writeAdditional(this.getPersistentData());
        moveCounter = 80;
        moveTime = moveTicks;
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


    public Item getJellyItem() {
        return JELLY_ITEM;
    }

    public void livingTick() {
        if (this.stingCounter > 0) {
            --this.stingCounter;
        }
        if (dropCounter == 0 && this.isInWater()) {
            this.entityDropItem(new ItemStack(JELLY_ITEM, 1), -0.5F);
            dropCounter = (int) (24000 / dailyDrops);
        }
        if (dropCounter > 0) {
            dropCounter--;
        }
        if (moveCounter == 0 && this.isInWater()) {
            moveCounter = moveTime;
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
            //this.rotationYaw = (float) Math.atan2(dirZ, dirX);
        }
        else if (moveCounter > 0) {
            moveCounter--;
        }

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
        if ((!this.canDespawn(1) && !(JellyfishingMod.CONFIG.CAUGHT_JELLYFISH_STING.get())) || SandySuitItem.hasAllPieces(entityIn)) {
            return;
        }
        if (canThisEntitySting && stingCounter == 0 && this.isInWater() && JellyfishingMod.CONFIG.JELLYFISH_STING.get() && !entityIn.getEntityWorld().getDifficulty().equals(Difficulty.PEACEFUL)) {
            stingCounter = stingTime;
            if (this.getEntityWorld().getRandom().nextDouble() < stingChance) {
                entityIn.attackEntityFrom(JELLYFISH_STING, stingDmg);
                this.playSound(JellyfishingSounds.STING.get(), 1, 1);
                this.setNewVelocity(entityIn, dodgeSpeed);
                this.writeAdditional(this.getPersistentData());
            }
        }
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6.0D);
    }

    public void setNewVelocity(Entity entityIn, double multiplier) {
        this.setMotion((this.getPosX() - entityIn.getPosX()) * multiplier, (this.getPosY() - entityIn.getPosY()) * multiplier, (this.getPosZ() - entityIn.getPosZ()) * multiplier);

    }

    protected void registerGoals() {
    }

    @Override
    protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {

        if (player.getHeldItem(hand).getItem() == (Items.WATER_BUCKET)) {
            return ActionResultType.FAIL;
        }

        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == JellyfishingItems.JELLYFISH_NET.get() && player.getCooldownTracker().getCooldown(itemstack.getItem(), 0) == 0) {
            player.swingArm(hand);
            if (!player.getEntityWorld().isRemote() && this.isAlive()) {
                if (!this.canDespawn(1) || dodgeChance - (EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.AGILITY.get(), itemstack)/10) < this.getEntityWorld().getRandom().nextDouble()) {

                    player.getCooldownTracker().setCooldown(itemstack.getItem(), 20);
                    this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, 1.0F);
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemstack) == 0 || (EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemstack) > 0 && player.getEntityWorld().getRandom().nextDouble() < (EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemstack) / 3))) {
                        itemstack.damageItem(1, player, (p_220045_0_) -> {
                            p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                        });
                    }
                    if (this.canDespawn(1)) {
                        int i = (int) (this.dodgeChance * 10);
                        while(i > 0) {
                            int j = ExperienceOrbEntity.getXPSplit(i);
                            i -= j;
                            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), j));
                        }
                    }
                    boolean greaseFlag = false;
                    if (0.005 > this.getEntityWorld().getRandom().nextDouble() && this.canDespawn(1)) {
                        this.entityDropItem(new ItemStack(JellyfishingItems.GREASE_BALL.get(), 1), -0.5F);
                        this.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                        greaseFlag = true;
                    }
                    if (EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > 0 && this.canDespawn(1)) {
                        if (this.canDespawn(1) && player.getEntityWorld().getRandom().nextDouble() < (0.1 * EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack))) {
                            try {
                                LootContext.Builder lootcontext$builder = (new LootContext.Builder(this.getServer().getWorld(this.getEntityWorld().getDimensionKey()))).withParameter(LootParameters.field_237457_g_, this.getMotion()).withParameter(LootParameters.TOOL, itemstack).withRandom(this.rand).withLuck(player.getLuck() + (EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) - 1));
                                lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, player).withParameter(LootParameters.THIS_ENTITY, this);
                                LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
                                List<ItemStack> list = loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING));
                                this.entityDropItem(list.get(0));
                                this.playSound(SoundEvents.ENTITY_FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                            } catch (Exception e) {
                                this.playSound(SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, 1.0F, 1.0F);
                            }
                        }
                        if (!greaseFlag && 0.005 * EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > this.getEntityWorld().getRandom().nextDouble() && this.canDespawn(1)) {
                            this.entityDropItem(new ItemStack(JellyfishingItems.GREASE_BALL.get(), 1), -0.5F);
                            this.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                        }
                        if (0.005 * EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > this.getEntityWorld().getRandom().nextDouble() && this.canDespawn(1)) {
                            this.entityDropItem(new ItemStack(JellyfishingItems.MUSIC_DISC_JELLYFISH_FIELDS.get(), 1), -0.5F);
                        }
                        if (0.005 * EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > this.getEntityWorld().getRandom().nextDouble() && this.canDespawn(1)) {
                            this.entityDropItem(new ItemStack(JellyfishingItems.BUBBLE_WAND.get(), 1), -0.5F);
                        }
                    }
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

                    return ActionResultType.SUCCESS;
                } else {
                    this.setNewVelocity(player, dodgeSpeed);
                    player.getCooldownTracker().setCooldown(itemstack.getItem(), 20 - 10 * EnchantmentHelper.getEnchantmentLevel(JellyfishingEnchantments.AGILITY.get(), itemstack));
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return super.func_230254_b_(player, hand);
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


    public static boolean canSpawn(EntityType<? extends AbstractJellyfishEntity> type, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.hasWater(pos);
    }

}
