package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JellyfishEntity extends AbstractJellyfishEntity {

    public JellyfishEntity(EntityType<? extends AbstractFishEntity> type, World worldIn) {
        super(type, worldIn, new ItemStack(JellyfishingItems.JELLYFISH.get(), 1), JellyfishingItems.JELLYFISH_JELLY.get(), 1, true, 500, 3, 0.1, 0.2, 0.1);
    }




}
