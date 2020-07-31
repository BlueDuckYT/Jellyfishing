package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
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

    public static final RegistryObject<Block> CORAL_PLANT = BLOCKS.register("coral_plant", () -> new CoralPlant(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).notSolid().sound(SoundType.SLIME).doesNotBlockMovement()));
    public static final RegistryObject<Item> CORAL_PLANT_ITEM = ITEMS.register("coral_plant", () -> new BlockItemBase(CORAL_PLANT.get()));

    public static final RegistryObject<Block> SCRAP_METAL = BLOCKS.register("scrap_metal", () -> new Block(Block.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Item> SCRAP_METAL_ITEM = ITEMS.register("scrap_metal", () -> new BlockItemBase(SCRAP_METAL.get()));

    public static final RegistryObject<Block> CORALSTONE = BLOCKS.register("coralstone", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 1F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Item> CORALSTONE_ITEM = ITEMS.register("coralstone", () -> new BlockItemBase(CORALSTONE.get()));

    public static final RegistryObject<Block> ALGAE_GRASS = BLOCKS.register("algae_grass", () -> new AlgaeGrassBlock(Block.Properties.create(Material.EARTH, MaterialColor.LIME).sound(SoundType.WET_GRASS).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> ALGAE_GRASS_ITEM = ITEMS.register("algae_grass", () -> new BlockItemBase(ALGAE_GRASS.get()));

    public static final RegistryObject<Block> ALGAE_BLOCK = BLOCKS.register("algae_block", () -> new Block(Block.Properties.create(Material.WOOL, MaterialColor.LIME).sound(SoundType.WET_GRASS).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> ALGAE_BLOCK_ITEM = ITEMS.register("algae_block", () -> new BlockItemBase(ALGAE_BLOCK.get()));


    //public static final RegistryObject<Block> JELLYFISH_MACHINE = BLOCKS.register("jellyfish_machine", () -> new JellyfishMachineBlock(Block.Properties.create(Material.CLAY, MaterialColor.BROWN).sound(SoundType.METAL)));
    //public static final RegistryObject<Item> JELLYFISH_MACHINE_ITEM = ITEMS.register("jellyfish_machine", () -> new BlockItemBase(JELLYFISH_MACHINE.get()));





    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
