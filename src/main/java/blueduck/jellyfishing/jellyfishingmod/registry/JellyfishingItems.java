package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.BlueJellyBlock;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyBlock;
import blueduck.jellyfishing.jellyfishingmod.items.JellyfishItem;
import blueduck.jellyfishing.jellyfishingmod.items.JellyfishingSpawnEgg;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Item> JELLYFISH_NET = ITEMS.register("jellyfish_net", () -> new Item(new Item.Properties().group(ItemGroup.TOOLS).defaultMaxDamage(148)));

    public static final RegistryObject<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(3).saturation(5.0F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY = ITEMS.register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(6).saturation(7.5F).build())));

    public static final RegistryObject<Item> JELLYFISH = ITEMS.register("jellyfish", () -> new JellyfishItem(new Item.Properties().group(ItemGroup.MATERIALS),  () -> JellyfishingEntities.JELLYFISH.get()));
    public static final RegistryObject<Item> BLUE_JELLYFISH = ITEMS.register("blue_jellyfish", () -> new JellyfishItem(new Item.Properties().group(ItemGroup.MATERIALS),  () -> JellyfishingEntities.BLUE_JELLYFISH.get()));

    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JellyfishingEntities.JELLYFISH.get(),15615448, 15615377, new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BLUE_JELLYFISH_SPAWN_EGG = ITEMS.register("blue_jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JellyfishingEntities.BLUE_JELLYFISH.get(),5733356, 5719532, new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> SEANUT = ITEMS.register("seanut", () -> new BlockNamedItem(JellyfishingBlocks.SEANUT_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(new Food.Builder().hunger(3).saturation(1).build())));


    public static final RegistryObject<Item> JELLYFISH_JELLY_SANDWICH = ITEMS.register("jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(12).saturation(8F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(14).saturation(10F).build())));




    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
