package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.biomes.JellyfishFields;
import blueduck.jellyfishing.jellyfishingmod.misc.AgilityEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;

public class JellyfishingEnchantments {

    public static java.util.function.Predicate<Item> NET = new java.util.function.Predicate<Item>() {
        @Override
        public boolean test(Item item) {
            return item.getName().equals(JellyfishingItems.JELLYFISH_NET.get().getName());
        }
    };

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(ForgeRegistries.ENCHANTMENTS, JellyfishingMod.MODID);

    public static final RegistryObject<Enchantment> AGILITY = ENCHANTMENTS.register("agility", () -> new AgilityEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.values()));




    public static void init() {
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}


