package blueduck.jellyfishing.jellyfishingmod.items;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class JellyfishingSpawnEgg extends SpawnEggItem {

    public static final ArrayList<JellyfishingSpawnEgg> JELLYFISHING_SPAWN_EGGS = new ArrayList<>();

    Supplier<? extends EntityType<?>> type;
    public JellyfishingSpawnEgg(Supplier<? extends EntityType<?>> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
        super(null, primaryColorIn, secondaryColorIn, builder);
        type = typeIn;
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
        return type.get();
    }


    //Method from Infernal Expansion's Spawn Egg Class
    public static void doDispenserSetup() {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
        DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> entitytype = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                entitytype.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };
        for (final SpawnEggItem egg : JELLYFISHING_SPAWN_EGGS) {
            EGGS.put(egg.getType(null), egg);
            DispenserBlock.registerDispenseBehavior(egg, defaultDispenseItemBehavior);
               }
        JELLYFISHING_SPAWN_EGGS.clear();
    }


}
