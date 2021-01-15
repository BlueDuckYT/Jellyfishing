package blueduck.jellyfishing.misc;

import blueduck.jellyfishing.registry.JellyfishingEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class PlunderingEnchantment extends Enchantment {


    public PlunderingEnchantment(Rarity rarityIn, EquipmentSlotType[] slots) {
        super(rarityIn, JellyfishingEnchantments.ENCHANTMENT_TYPE, slots);
    }
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 10 * (enchantmentLevel - 1);
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }


    public int getMinLevel() {
        return 1;
    }
    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }
}
