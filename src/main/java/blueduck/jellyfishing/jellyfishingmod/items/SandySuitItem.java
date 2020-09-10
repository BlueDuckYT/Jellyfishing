package blueduck.jellyfishing.jellyfishingmod.items;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import javax.management.remote.rmi._RMIConnection_Stub;

public class SandySuitItem extends ArmorItem {
    public SandySuitItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (this.equals(JellyfishingItems.AIR_SUIT_HELMET.get()) && player.isInWater()) {
            player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 10, 0, true, true));
        }
    }
}
