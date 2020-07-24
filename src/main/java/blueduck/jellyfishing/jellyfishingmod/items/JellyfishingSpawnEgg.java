package blueduck.jellyfishing.jellyfishingmod.items;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class JellyfishingSpawnEgg extends SpawnEggItem {
    Supplier<? extends EntityType<?>> type;
    public JellyfishingSpawnEgg(Supplier<? extends EntityType<?>> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
        super(null, primaryColorIn, secondaryColorIn, builder);
        type = typeIn;
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
        return type.get();
    }


}
