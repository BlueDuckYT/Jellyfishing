package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.BlueJellyBlock;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HoneyBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, JellyfishingMod.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Block> JELLY_BLOCK = BLOCKS.register("jelly_block", () -> new JellyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(0.5F).notSolid().sound(SoundType.field_226947_m_)));
    public static final RegistryObject<Item> JELLY_BLOCK_ITEM = ITEMS.register("jelly_block", () -> new BlockItemBase(JELLY_BLOCK.get()));

    public static final RegistryObject<Block> BLUE_JELLY_BLOCK = BLOCKS.register("blue_jelly_block", () -> new BlueJellyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(2F).notSolid().sound(SoundType.field_226947_m_)));
    public static final RegistryObject<Item> BLUE_JELLY_BLOCK_ITEM = ITEMS.register("blue_jelly_block", () -> new BlockItemBase(BLUE_JELLY_BLOCK.get()));



    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
