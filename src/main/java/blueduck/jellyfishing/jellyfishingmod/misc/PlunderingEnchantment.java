package blueduck.jellyfishing.jellyfishingmod.misc;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class PlunderingEnchantment extends Enchantment {


    public PlunderingEnchantment(Rarity rarityIn, EquipmentSlotType[] slots) {
        super(rarityIn, EnchantmentType.create("jellyfish_net", JellyfishingEnchantments.NET), slots);
    }
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 10 * (enchantmentLevel - 1);
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }
}
