package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.BlueJellyBlock;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyBlock;
import blueduck.jellyfishing.jellyfishingmod.items.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Item> JELLYFISH_NET = ITEMS.register("jellyfish_net", () -> new JellyfishNetItem(new Item.Properties().group(ItemGroup.TOOLS).defaultMaxDamage(148)));

    public static final RegistryObject<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(3).saturation(5.0F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY = ITEMS.register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(6).saturation(7.5F).build())));

    public static final RegistryObject<Item> JELLYFISH = ITEMS.register("jellyfish", () -> new JellyfishItem(new Item.Properties().group(ItemGroup.MATERIALS),  () -> JellyfishingEntities.JELLYFISH.get()));
    public static final RegistryObject<Item> BLUE_JELLYFISH = ITEMS.register("blue_jellyfish", () -> new JellyfishItem(new Item.Properties().group(ItemGroup.MATERIALS),  () -> JellyfishingEntities.BLUE_JELLYFISH.get()));

    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JellyfishingEntities.JELLYFISH.get(),15615448, 15615377, new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BLUE_JELLYFISH_SPAWN_EGG = ITEMS.register("blue_jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JellyfishingEntities.BLUE_JELLYFISH.get(),5733356, 5719532, new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> SEANUT = ITEMS.register("seanut", () -> new BlockNamedItem(JellyfishingBlocks.SEANUT_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(new Food.Builder().hunger(3).saturation(1).build())));
    public static final RegistryObject<Item> ROASTED_SEANUT = ITEMS.register("roasted_seanut", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(5).saturation(2.0F).build())));
    public static final RegistryObject<Item> SEANUT_BUTTER = ITEMS.register("seanut_butter", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(6).saturation(2.0F).build())));
    public static final RegistryObject<Item> SEANUT_BRITTLE = ITEMS.register("seanut_brittle", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(7).saturation(1.0F).build())));



    public static final RegistryObject<Item> JELLYFISH_JELLY_SANDWICH = ITEMS.register("jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(12).saturation(8F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(14).saturation(10F).build())));

    public static final RegistryObject<Item> SEANUT_JELLYFISH_JELLY_SANDWICH = ITEMS.register("seanut_butter_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(15).saturation(10F).build())));
    public static final RegistryObject<Item> SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("seanut_butter_blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(17).saturation(12F).build())));

    public static final RegistryObject<Item> KELP_SHAKE = ITEMS.register("kelp_shake", () -> new KelpShakeItem(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(6).saturation(2.0F).build())));

    public static final RegistryObject<Item> KELP_MUSTACHE = ITEMS.register("kelp_mustache", () -> new KelpMustacheItem(new KelpMaterial(), new Item.Properties().group(ItemGroup.COMBAT)));

    public static final RegistryObject<Item> TRIPLE_GOOBERBERRY_SUNRISE = ITEMS.register("triple_gooberberry_sunrise", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(10).saturation(3.0F).fastToEat().build())));

    public static final SuitMaterial SUIT_MATERIAL = new SuitMaterial();

    public static final RegistryObject<Item> AIR_SUIT_HELMET = ITEMS.register("air_suit_helmet", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> AIR_SUIT_CHESTPLATE = ITEMS.register("air_suit_chestplate", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> AIR_SUIT_LEGGINGS = ITEMS.register("air_suit_leggings", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> AIR_SUIT_BOOTS = ITEMS.register("air_suit_boots", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));


    public static final RegistryObject<Item> DIVER_SUIT_HELMET = ITEMS.register("diver_suit_helmet", () -> new DiverSuitItem(SUIT_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));


    public static final RegistryObject<Item> MUSIC_DISC_JELLYFISH_FIELDS = ITEMS.register("music_disc_jellyfish_fields", () -> new JellyfishingMusicDisc(15, () -> JellyfishingSounds.JELLYFISH_FIELDS.get(), new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.RARE)));






    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
