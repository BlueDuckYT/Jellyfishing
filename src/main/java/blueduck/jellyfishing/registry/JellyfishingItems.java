package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.items.*;
import net.minecraft.block.ComposterBlock;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Item> JELLYFISH_NET = ITEMS.register("jellyfish_net", () -> new JellyfishNetItem(new Item.Properties().group(ItemGroup.TOOLS).defaultMaxDamage(148)));

    public static final RegistryObject<Item> BUBBLE_WAND = ITEMS.register("bubble_wand", () -> new BubbleKitItem(new Item.Properties().group(ItemGroup.TOOLS).defaultMaxDamage(128)));

    public static final RegistryObject<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(3).saturation(0.2F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY = ITEMS.register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(6).saturation(0.3F).build())));

    public static final RegistryObject<Item> JELLYFISH = ITEMS.register("jellyfish", () -> new JellyfishItem(new Item.Properties().group(ItemGroup.MATERIALS),  () -> JellyfishingEntities.JELLYFISH.get()));
    public static final RegistryObject<Item> BLUE_JELLYFISH = ITEMS.register("blue_jellyfish", () -> new JellyfishItem(new Item.Properties().group(ItemGroup.MATERIALS),  () -> JellyfishingEntities.BLUE_JELLYFISH.get()));

    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JellyfishingEntities.JELLYFISH.get(),15615448, 15615377, new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BLUE_JELLYFISH_SPAWN_EGG = ITEMS.register("blue_jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JellyfishingEntities.BLUE_JELLYFISH.get(),5733356, 5719532, new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> SEANUT = ITEMS.register("seanut", () -> new BlockNamedItem(JellyfishingBlocks.SEANUT_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(new Food.Builder().hunger(3).saturation(0.1F).build())));
    public static final RegistryObject<Item> ROASTED_SEANUT = ITEMS.register("roasted_seanut", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(5).saturation(0.4F).build())));
    public static final RegistryObject<Item> SEANUT_BUTTER = ITEMS.register("seanut_butter", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(6).saturation(0.5F).build())));
    public static final RegistryObject<Item> SEANUT_BRITTLE = ITEMS.register("seanut_brittle", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(12).saturation(0.8F).build())));

    public static final RegistryObject<Item> PINEAPPLE_SEEDS = ITEMS.register("pineapple_seeds", () -> new BlockNamedItem(JellyfishingBlocks.PINEAPPLE_PLANT.get(), (new Item.Properties()).group(ItemGroup.MATERIALS)));

    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).food(new Food.Builder().hunger(4).saturation(0.3F).build())));


    public static final RegistryObject<Item> JELLYFISH_JELLY_SANDWICH = ITEMS.register("jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(8).saturation(0.45F).build())));
    public static final RegistryObject<Item> BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(12).saturation(0.55F).build())));

    public static final RegistryObject<Item> SEANUT_JELLYFISH_JELLY_SANDWICH = ITEMS.register("seanut_butter_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(11).saturation(0.55F).build())));
    public static final RegistryObject<Item> SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("seanut_butter_blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(15).saturation(0.7F).build())));

    public static final RegistryObject<Item> KELP_SHAKE = ITEMS.register("kelp_shake", () -> new KelpShakeItem(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(6).saturation(0.2F).build())));

    public static final RegistryObject<Item> KELP_MUSTACHE = ITEMS.register("kelp_mustache", () -> new KelpMustacheItem(new KelpMaterial(), new Item.Properties().group(ItemGroup.COMBAT)));

    public static final RegistryObject<Item> TRIPLE_GOOBERBERRY_SUNRISE = ITEMS.register("triple_gooberberry_sunrise", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(10).saturation(0.6F).fastToEat().build())));

    public static final RegistryObject<Item> GREASE_BALL = ITEMS.register("grease_ball", () -> new Item(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> KRABBY_PATTY = ITEMS.register("krabby_patty", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(10).saturation(1.5F).meat().build()).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SPATULA = ITEMS.register("spatula", () -> new SpatulaItem(new Item.Properties().maxDamage(250).group(ItemGroup.COMBAT), JellyfishingMod.CONFIG.SPATULA_DAMAGE.get() - 3, -2, ItemTier.IRON));

    public static final RegistryObject<Item> GOLDEN_SPATULA = ITEMS.register("golden_spatula", () -> new SpatulaItem(new Item.Properties().maxDamage(2031).group(ItemGroup.COMBAT).rarity(Rarity.RARE), JellyfishingMod.CONFIG.GOLDEN_SPATULA_DAMAGE.get() - 3, -2F, ItemTier.NETHERITE));

    public static final RegistryObject<Item> KARATE_GLOVE = ITEMS.register("karate_glove", () -> new GloveItem(new Item.Properties().maxDamage(1000).group(ItemGroup.COMBAT), JellyfishingMod.CONFIG.KARATE_DAMAGE.get() - 1, -1.5F));
    public static final RegistryObject<Item> MASTER_KARATE_GLOVE = ITEMS.register("master_karate_glove", () -> new GloveItem(new Item.Properties().maxDamage(2031).group(ItemGroup.COMBAT), JellyfishingMod.CONFIG.MASTER_KARATE_DAMAGE.get() - 1, -1.5F));
    public static final RegistryObject<Item> POWER_KARATE_GLOVE = ITEMS.register("power_karate_glove", () -> new GloveItem(new Item.Properties().maxDamage(2031).group(ItemGroup.COMBAT), JellyfishingMod.CONFIG.POWER_KARATE_DAMAGE.get() - 1, -3.625F));


    public static final SuitMaterial SUIT_MATERIAL = new SuitMaterial();

    public static final RegistryObject<Item> AIR_SUIT_HELMET = ITEMS.register("air_suit_helmet", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> AIR_SUIT_CHESTPLATE = ITEMS.register("air_suit_chestplate", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> AIR_SUIT_LEGGINGS = ITEMS.register("air_suit_leggings", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> AIR_SUIT_BOOTS = ITEMS.register("air_suit_boots", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));



    public static final RegistryObject<Item> DIVER_SUIT_HELMET = null;//ITEMS.register("diver_suit_helmet", () -> new DiverSuitItem(SUIT_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));


    public static final RegistryObject<Item> MUSIC_DISC_JELLYFISH_FIELDS = ITEMS.register("music_disc_jellyfish_fields", () -> new JellyfishingMusicDisc(15, () -> JellyfishingSounds.JELLYFISH_FIELDS.get(), new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.RARE)));






    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static void registerCompostables() {
        ComposterBlock.registerCompostable(0.3F, SEANUT.get());
        ComposterBlock.registerCompostable(0.3F, PINEAPPLE_SEEDS.get());
        ComposterBlock.registerCompostable(0.7F, PINEAPPLE.get());
    }

}
