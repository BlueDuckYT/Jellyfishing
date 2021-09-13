package blueduck.jellyfishing.items;

import blueduck.jellyfishing.client.ClientReference;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import net.minecraft.item.Item.Properties;

public class SandySuitItem extends ArmorItem {
    public SandySuitItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (hasAllPieces(player) && player.isInWater()) {
            player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 10, 0, true, true));
        }
    }

    public static boolean hasAllPieces(PlayerEntity player) {
        return (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof SandySuitItem && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof SandySuitItem && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() instanceof SandySuitItem && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof SandySuitItem);
    }

    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return ClientReference.getSandySuitModel(armorSlot);
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        if (slot.equals(EquipmentSlotType.LEGS)) {
            return "jellyfishing:textures/models/armor/sandy_suit_legs.png";
        }
        return "jellyfishing:textures/models/armor/sandy_suit.png";
    }


}
