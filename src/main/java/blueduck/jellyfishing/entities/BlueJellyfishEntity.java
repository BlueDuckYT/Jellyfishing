package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class BlueJellyfishEntity extends AbstractJellyfishEntity {

    public BlueJellyfishEntity(EntityType<? extends AbstractFishEntity> type, World worldIn) {
        super(type, worldIn, new ItemStack(JellyfishingItems.BLUE_JELLYFISH.get(), 1), JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 1, true, 200, 6, 0.8, 0.75, 0.3);
    }



}
