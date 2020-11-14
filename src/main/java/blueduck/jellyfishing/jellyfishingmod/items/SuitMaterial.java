package blueduck.jellyfishing.jellyfishingmod.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class SuitMaterial implements IArmorMaterial {

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * 25;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return (int) (MAX_DAMAGE_ARRAY[slotIn.getIndex()] * 0.1);
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return null;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(Items.IRON_INGOT);
    }

    @Override
    public String getName() {
        return "jellyfishing:suit";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
