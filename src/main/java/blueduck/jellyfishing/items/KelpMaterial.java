package blueduck.jellyfishing.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class KelpMaterial implements IArmorMaterial {
    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 180;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return null;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(Items.KELP);
    }

    @Override
    public String getName() {
        return "jellyfishing:mustache";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 5;
    }
}
