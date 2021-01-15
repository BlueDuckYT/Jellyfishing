package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.items.JellyfishNetItem;
import blueduck.jellyfishing.misc.AgilityEnchantment;
import blueduck.jellyfishing.misc.PlunderingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingEnchantments {

    public static java.util.function.Predicate<Item> NET = new java.util.function.Predicate<Item>() {
        @Override
        public boolean test(Item item) {
            return item instanceof JellyfishNetItem;
        }
    };
    public static EnchantmentType ENCHANTMENT_TYPE = EnchantmentType.create("jellyfish_net", JellyfishingEnchantments.NET);

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, JellyfishingMod.MODID);

    public static final RegistryObject<Enchantment> AGILITY = ENCHANTMENTS.register("agility", () -> new AgilityEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));
    public static final RegistryObject<Enchantment> PLUNDERING = ENCHANTMENTS.register("plundering", () -> new PlunderingEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));

    public static void init() {
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EnchantmentType[] TYPES = new EnchantmentType[ItemGroup.TOOLS.getRelevantEnchantmentTypes().length + 2];
        for (int i = 0; i < ItemGroup.TOOLS.getRelevantEnchantmentTypes().length; i++) {
            TYPES[i] = ItemGroup.TOOLS.getRelevantEnchantmentTypes()[i];
        }
        TYPES[TYPES.length - 1] = ENCHANTMENT_TYPE;
        ItemGroup.TOOLS.enchantmentTypes = TYPES;

        }

    }



