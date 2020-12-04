package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.*;
import blueduck.jellyfishing.jellyfishingmod.blocks.DirectionalBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

public class JellyfishingBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JellyfishingMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Block> JELLY_BLOCK = BLOCKS.register("jelly_block", () -> new JellyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(0.5F).notSolid().sound(SoundType.HONEY)));
    public static final RegistryObject<Item> JELLY_BLOCK_ITEM = ITEMS.register("jelly_block", () -> new BlockItemBase(JELLY_BLOCK.get()));

    public static final RegistryObject<Block> BLUE_JELLY_BLOCK = BLOCKS.register("blue_jelly_block", () -> new BlueJellyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(2F).notSolid().sound(SoundType.HONEY)));
    public static final RegistryObject<Item> BLUE_JELLY_BLOCK_ITEM = ITEMS.register("blue_jelly_block", () -> new BlockItemBase(BLUE_JELLY_BLOCK.get()));

    public static final RegistryObject<Block> CORAL_PLANT = BLOCKS.register("coral_plant", () -> new CoralPlant(Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.ADOBE).notSolid().sound(SoundType.SLIME).doesNotBlockMovement().setLightLevel(blockState -> 12)));
    public static final RegistryObject<Item> CORAL_PLANT_ITEM = ITEMS.register("coral_plant", () -> new BlockItemBase(CORAL_PLANT.get()));

    public static final RegistryObject<Block> TUBE_PLANT = BLOCKS.register("tube_plant", () -> new CoralPlant(Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.ADOBE).notSolid().sound(SoundType.SLIME).doesNotBlockMovement()));
    public static final RegistryObject<Item> TUBE_PLANT_ITEM = ITEMS.register("tube_plant", () -> new BlockItemBase(TUBE_PLANT.get()));

    public static final RegistryObject<Block> SCRAP_METAL = BLOCKS.register("scrap_metal", () -> new Block(Block.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Item> SCRAP_METAL_ITEM = ITEMS.register("scrap_metal", () -> new BlockItemBase(SCRAP_METAL.get()));

    public static final RegistryObject<Block> SCRAP_METAL_STAIRS = BLOCKS.register("scrap_metal_stairs", () -> new StairsBlock(() -> SCRAP_METAL.get().getDefaultState(), Block.Properties.from(SCRAP_METAL.get())));
    public static final RegistryObject<Item> SCRAP_METAL_STAIRS_ITEM = ITEMS.register("scrap_metal_stairs", () -> new BlockItemBase(SCRAP_METAL_STAIRS.get()));

    public static final RegistryObject<Block> SCRAP_METAL_SLAB = BLOCKS.register("scrap_metal_slab", () -> new SlabBlock(Block.Properties.from(SCRAP_METAL.get())));
    public static final RegistryObject<Item> SCRAP_METAL_SLAB_ITEM = ITEMS.register("scrap_metal_slab", () -> new BlockItemBase(SCRAP_METAL_SLAB.get()));

    public static final RegistryObject<Block> SEANUT_BRITTLE_BLOCK = BLOCKS.register("seanut_brittle_block", () -> new Block(Block.Properties.create(Material.GLASS, MaterialColor.BROWN).sound(SoundType.STONE).hardnessAndResistance(0.3F, 0.3F).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> SEANUT_BRITTLE_BLOCK_ITEM = ITEMS.register("seanut_brittle_block", () -> new BlockItemBase(SEANUT_BRITTLE_BLOCK.get()));

    public static final RegistryObject<Block> CORALSTONE = BLOCKS.register("coralstone", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 1F).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Item> CORALSTONE_ITEM = ITEMS.register("coralstone", () -> new BlockItemBase(CORALSTONE.get()));

    public static final RegistryObject<Block> CORALSTONE_WALL = BLOCKS.register("coralstone_wall", () -> new WallBlock(Block.Properties.from(CORALSTONE.get())));
    public static final RegistryObject<Item> CORALSTONE_WALL_ITEM = ITEMS.register("coralstone_wall", () -> new BlockItemBase(CORALSTONE_WALL.get()));

    public static final RegistryObject<Block> CORALSTONE_STAIRS = BLOCKS.register("coralstone_stairs", () -> new StairsBlock(() -> CORALSTONE.get().getDefaultState(), Block.Properties.from(CORALSTONE.get())));
    public static final RegistryObject<Item> CORALSTONE_STAIRS_ITEM = ITEMS.register("coralstone_stairs", () -> new BlockItemBase(CORALSTONE_STAIRS.get()));

    public static final RegistryObject<Block> CORALSTONE_SLAB = BLOCKS.register("coralstone_slab", () -> new SlabBlock(Block.Properties.from(CORALSTONE.get())));
    public static final RegistryObject<Item> CORALSTONE_SLAB_ITEM = ITEMS.register("coralstone_slab", () -> new BlockItemBase(CORALSTONE_SLAB.get()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE = BLOCKS.register("polished_coralstone", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 1F).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_ITEM = ITEMS.register("polished_coralstone", () -> new BlockItemBase(POLISHED_CORALSTONE.get()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE_STAIRS = BLOCKS.register("polished_coralstone_stairs", () -> new StairsBlock(() -> POLISHED_CORALSTONE.get().getDefaultState(), Block.Properties.from(POLISHED_CORALSTONE.get())));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_STAIRS_ITEM = ITEMS.register("polished_coralstone_stairs", () -> new BlockItemBase(POLISHED_CORALSTONE_STAIRS.get()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE_SLAB = BLOCKS.register("polished_coralstone_slab", () -> new SlabBlock(Block.Properties.from(POLISHED_CORALSTONE.get())));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_SLAB_ITEM = ITEMS.register("polished_coralstone_slab", () -> new BlockItemBase(POLISHED_CORALSTONE_SLAB.get()));

    public static final RegistryObject<Block> ALGAE_GRASS = BLOCKS.register("algae_grass", () -> new AlgaeGrassBlock(Block.Properties.create(Material.EARTH, MaterialColor.LIME).sound(SoundType.WET_GRASS).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> ALGAE_GRASS_ITEM = ITEMS.register("algae_grass", () -> new BlockItemBase(ALGAE_GRASS.get()));

    public static final RegistryObject<Block> ALGAE_BLOCK = BLOCKS.register("algae_block", () -> new Block(Block.Properties.create(Material.WOOL, MaterialColor.LIME).sound(SoundType.WET_GRASS).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> ALGAE_BLOCK_ITEM = ITEMS.register("algae_block", () -> new BlockItemBase(ALGAE_BLOCK.get()));

    public static final RegistryObject<Block> SEANUT_BUSH = BLOCKS.register("seanut_bush", () -> new SeanutBush(Block.Properties.create(Material.OCEAN_PLANT).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> GRILL = BLOCKS.register("grill", () -> new DirectionalBlock(Block.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().notSolid()));
    public static final RegistryObject<Item> GRILL_ITEM = ITEMS.register("grill", () -> new BlockItemBase(GRILL.get()));


    //public static final RegistryObject<Block> JELLYFISH_MACHINE = BLOCKS.register("jellyfish_machine", () -> new JellyfishMachineBlock(Block.Properties.create(Material.CLAY, MaterialColor.BROWN).sound(SoundType.METAL)));
    //public static final RegistryObject<Item> JELLYFISH_MACHINE_ITEM = ITEMS.register("jellyfish_machine", () -> new BlockItemBase(JELLYFISH_MACHINE.get()));





    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
