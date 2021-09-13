package blueduck.jellyfishing.items;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class DiverSuitItem extends ArmorItem {
    public DiverSuitItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (this.equals(JellyfishingItems.DIVER_SUIT_HELMET.get())) {
            if (player.isInWater()) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 10, 0, true, true));
            }
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 10, 0, true, true));
        }

    }
}
