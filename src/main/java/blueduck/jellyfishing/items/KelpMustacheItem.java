package blueduck.jellyfishing.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class KelpMustacheItem extends ArmorItem {
    public KelpMustacheItem(IArmorMaterial materialIn, Properties builder) {
        super(materialIn, EquipmentSlotType.HEAD, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (world.getRandom().nextDouble() < 0.025) {
            stack.damageItem(1, player, (p_213360_0_) -> {
                p_213360_0_.sendBreakAnimation(EquipmentSlotType.HEAD);
            });
        }
        player.addPotionEffect(new EffectInstance(Effect.get(11), 10, 0));
    }
}
