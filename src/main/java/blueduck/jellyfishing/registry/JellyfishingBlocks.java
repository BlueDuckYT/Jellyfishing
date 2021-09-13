package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.blocks.*;
import blueduck.jellyfishing.blocks.DirectionalBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class JellyfishingBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JellyfishingMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Block> JELLY_BLOCK = BLOCKS.register("jelly_block", () -> new JellyBlock(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(0.5F).notSolid().sound(SoundType.HONEY)));
    public static final RegistryObject<Item> JELLY_BLOCK_ITEM = ITEMS.register("jelly_block", () -> new BlockItemBase(JELLY_BLOCK.get()));

    public static final RegistryObject<Block> BLUE_JELLY_BLOCK = BLOCKS.register("blue_jelly_block", () -> new BlueJellyBlock(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(2F).notSolid().sound(SoundType.HONEY)));
    public static final RegistryObject<Item> BLUE_JELLY_BLOCK_ITEM = ITEMS.register("blue_jelly_block", () -> new BlockItemBase(BLUE_JELLY_BLOCK.get()));

    public static final RegistryObject<Block> CORAL_PLANT = BLOCKS.register("coral_plant", () -> new CoralPlant(AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.ADOBE).notSolid().sound(SoundType.SLIME).doesNotBlockMovement().setLightLevel(blockState -> 12)));
    public static final RegistryObject<Item> CORAL_PLANT_ITEM = ITEMS.register("coral_plant", () -> new BlockItemBase(CORAL_PLANT.get()));

    public static final RegistryObject<Block> TUBE_PLANT = BLOCKS.register("tube_plant", () -> new CoralPlant(AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.ADOBE).notSolid().sound(SoundType.SLIME).doesNotBlockMovement()));
    public static final RegistryObject<Item> TUBE_PLANT_ITEM = ITEMS.register("tube_plant", () -> new BlockItemBase(TUBE_PLANT.get()));

    public static final RegistryObject<Block> SCRAP_METAL = BLOCKS.register("scrap_metal", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Item> SCRAP_METAL_ITEM = ITEMS.register("scrap_metal", () -> new BlockItemBase(SCRAP_METAL.get()));

    public static final RegistryObject<Block> SCRAP_METAL_STAIRS = BLOCKS.register("scrap_metal_stairs", () -> new StairsBlock(() -> SCRAP_METAL.get().getDefaultState(), AbstractBlock.Properties.from(SCRAP_METAL.get())));
    public static final RegistryObject<Item> SCRAP_METAL_STAIRS_ITEM = ITEMS.register("scrap_metal_stairs", () -> new BlockItemBase(SCRAP_METAL_STAIRS.get()));

    public static final RegistryObject<Block> SCRAP_METAL_SLAB = BLOCKS.register("scrap_metal_slab", () -> new SlabBlock(AbstractBlock.Properties.from(SCRAP_METAL.get())));
    public static final RegistryObject<Item> SCRAP_METAL_SLAB_ITEM = ITEMS.register("scrap_metal_slab", () -> new BlockItemBase(SCRAP_METAL_SLAB.get()));

    public static final RegistryObject<Block> SCRAP_METAL_VERTICAL_SLAB = conditionallyRegisterBlock("scrap_metal_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.from(SCRAP_METAL.get())), () -> isLoaded("quark"));
    public static final RegistryObject<Item> SCRAP_METAL_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("scrap_metal_vertical_slab", () -> new BlockItem(SCRAP_METAL_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistryObject<Block> CHROME_METAL = BLOCKS.register("chrome_metal", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Item> CHROME_METAL_ITEM = ITEMS.register("chrome_metal", () -> new BlockItemBase(CHROME_METAL.get()));

    public static final RegistryObject<Block> CHROME_METAL_STAIRS = BLOCKS.register("chrome_metal_stairs", () -> new StairsBlock(() -> CHROME_METAL.get().getDefaultState(), AbstractBlock.Properties.from(CHROME_METAL.get())));
    public static final RegistryObject<Item> CHROME_METAL_STAIRS_ITEM = ITEMS.register("chrome_metal_stairs", () -> new BlockItemBase(CHROME_METAL_STAIRS.get()));

    public static final RegistryObject<Block> CHROME_METAL_SLAB = BLOCKS.register("chrome_metal_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CHROME_METAL.get())));
    public static final RegistryObject<Item> CHROME_METAL_SLAB_ITEM = ITEMS.register("chrome_metal_slab", () -> new BlockItemBase(CHROME_METAL_SLAB.get()));

    public static final RegistryObject<Block> CHROME_METAL_VERTICAL_SLAB = conditionallyRegisterBlock("chrome_metal_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.from(CHROME_METAL.get())), () -> isLoaded("quark"));
    public static final RegistryObject<Item> CHROME_METAL_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("chrome_metal_vertical_slab", () -> new BlockItem(CHROME_METAL_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistryObject<Block> CHROME_BRICKS = BLOCKS.register("chrome_bricks", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Item> CHROME_BRICKS_ITEM = ITEMS.register("chrome_bricks", () -> new BlockItemBase(CHROME_BRICKS.get()));

    public static final RegistryObject<Block> CHROME_BRICK_STAIRS = BLOCKS.register("chrome_brick_stairs", () -> new StairsBlock(() -> CHROME_METAL.get().getDefaultState(), AbstractBlock.Properties.from(CHROME_METAL.get())));
    public static final RegistryObject<Item> CHROME_BRICK_STAIRS_ITEM = ITEMS.register("chrome_brick_stairs", () -> new BlockItemBase(CHROME_BRICK_STAIRS.get()));

    public static final RegistryObject<Block> CHROME_BRICK_SLAB = BLOCKS.register("chrome_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CHROME_METAL.get())));
    public static final RegistryObject<Item> CHROME_BRICK_SLAB_ITEM = ITEMS.register("chrome_brick_slab", () -> new BlockItemBase(CHROME_BRICK_SLAB.get()));

    public static final RegistryObject<Block> CHROME_BRICK_VERTICAL_SLAB = conditionallyRegisterBlock("chrome_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.from(CHROME_BRICKS.get())), () -> isLoaded("quark"));
    public static final RegistryObject<Item> CHROME_BRICK_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("chrome_brick_vertical_slab", () -> new BlockItem(CHROME_BRICK_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistryObject<Block> VAULT_DOOR = BLOCKS.register("vault_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.PISTON, MaterialColor.GRAY).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Item> VAULT_DOOR_ITEM = ITEMS.register("vault_door", () -> new BlockItemBase(VAULT_DOOR.get(), new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<Block> VAULT_TRAPDOOR = BLOCKS.register("vault_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(VAULT_DOOR.get())));
    public static final RegistryObject<Item> VAULT_TRAPDOOR_ITEM = ITEMS.register("vault_trapdoor", () -> new BlockItemBase(VAULT_TRAPDOOR.get(), new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<Block> SCRAP_METAL_WINDOW = BLOCKS.register("scrap_metal_window", () -> new TrapDoorBlock(AbstractBlock.Properties.from(VAULT_DOOR.get()).notSolid()));
    public static final RegistryObject<Item> SCRAP_METAL_WINDOW_ITEM = ITEMS.register("scrap_metal_window", () -> new BlockItemBase(SCRAP_METAL_WINDOW.get(), new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<Block> CHROME_DOOR = BLOCKS.register("chrome_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.PISTON, MaterialColor.GRAY).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Item> CHROME_DOOR_ITEM = ITEMS.register("chrome_door", () -> new BlockItemBase(CHROME_DOOR.get(), new Item.Properties().group(ItemGroup.REDSTONE)));

    public static final RegistryObject<Block> CHROME_VENT = BLOCKS.register("chrome_vent", () -> new TrapDoorBlock(AbstractBlock.Properties.from(VAULT_DOOR.get())));
    public static final RegistryObject<Item> CHROME_VENT_ITEM = ITEMS.register("chrome_vent", () -> new BlockItemBase(CHROME_VENT.get(), new Item.Properties().group(ItemGroup.REDSTONE)));


    public static final RegistryObject<Block> SEANUT_BRITTLE_BLOCK = BLOCKS.register("seanut_brittle_block", () -> new Block(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.BROWN).sound(SoundType.STONE).hardnessAndResistance(0.3F, 0.3F).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> SEANUT_BRITTLE_BLOCK_ITEM = ITEMS.register("seanut_brittle_block", () -> new BlockItemBase(SEANUT_BRITTLE_BLOCK.get()));

    public static final RegistryObject<Block> PINEAPPLE_BLOCK = BLOCKS.register("pineapple_block", () -> new Block(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.YELLOW).sound(SoundType.WET_GRASS).hardnessAndResistance(0.9F, 0.9F).harvestTool(ToolType.AXE).harvestLevel(0)));
    public static final RegistryObject<Item> PINEAPPLE_BLOCK_ITEM = ITEMS.register("pineapple_block", () -> new BlockItemBase(PINEAPPLE_BLOCK.get()));

    public static final RegistryObject<Block> PINEAPPLE_PILLAR = BLOCKS.register("pineapple_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(PINEAPPLE_BLOCK.get())));
    public static final RegistryObject<Item> PINEAPPLE_PILLAR_ITEM = ITEMS.register("pineapple_pillar", () -> new BlockItemBase(PINEAPPLE_PILLAR.get()));

    public static final RegistryObject<Block> CHISELED_PINEAPPLE_BLOCK = BLOCKS.register("chiseled_pineapple_block", () -> new Block(AbstractBlock.Properties.from(PINEAPPLE_BLOCK.get())));
    public static final RegistryObject<Item> CHISELED_PINEAPPLE_BLOCK_ITEM = ITEMS.register("chiseled_pineapple_block", () -> new BlockItemBase(CHISELED_PINEAPPLE_BLOCK.get()));

    public static final RegistryObject<Block> CORALSTONE = BLOCKS.register("coralstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 1F).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Item> CORALSTONE_ITEM = ITEMS.register("coralstone", () -> new BlockItemBase(CORALSTONE.get()));

    public static final RegistryObject<Block> CORALSTONE_WALL = BLOCKS.register("coralstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(CORALSTONE.get())));
    public static final RegistryObject<Item> CORALSTONE_WALL_ITEM = ITEMS.register("coralstone_wall", () -> new BlockItemBase(CORALSTONE_WALL.get()));

    public static final RegistryObject<Block> CORALSTONE_STAIRS = BLOCKS.register("coralstone_stairs", () -> new StairsBlock(() -> CORALSTONE.get().getDefaultState(), AbstractBlock.Properties.from(CORALSTONE.get())));
    public static final RegistryObject<Item> CORALSTONE_STAIRS_ITEM = ITEMS.register("coralstone_stairs", () -> new BlockItemBase(CORALSTONE_STAIRS.get()));

    public static final RegistryObject<Block> CORALSTONE_SLAB = BLOCKS.register("coralstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CORALSTONE.get())));
    public static final RegistryObject<Item> CORALSTONE_SLAB_ITEM = ITEMS.register("coralstone_slab", () -> new BlockItemBase(CORALSTONE_SLAB.get()));

    public static final RegistryObject<Block> CORALSTONE_VERTICAL_SLAB = conditionallyRegisterBlock("coralstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.from(CORALSTONE.get())), () -> isLoaded("quark"));
    public static final RegistryObject<Item> CORALSTONE_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("coralstone_vertical_slab", () -> new BlockItem(CORALSTONE_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistryObject<Block> POLISHED_CORALSTONE = BLOCKS.register("polished_coralstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 1F).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_ITEM = ITEMS.register("polished_coralstone", () -> new BlockItemBase(POLISHED_CORALSTONE.get()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE_STAIRS = BLOCKS.register("polished_coralstone_stairs", () -> new StairsBlock(() -> POLISHED_CORALSTONE.get().getDefaultState(), AbstractBlock.Properties.from(POLISHED_CORALSTONE.get())));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_STAIRS_ITEM = ITEMS.register("polished_coralstone_stairs", () -> new BlockItemBase(POLISHED_CORALSTONE_STAIRS.get()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE_SLAB = BLOCKS.register("polished_coralstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(POLISHED_CORALSTONE.get())));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_SLAB_ITEM = ITEMS.register("polished_coralstone_slab", () -> new BlockItemBase(POLISHED_CORALSTONE_SLAB.get()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE_VERTICAL_SLAB = conditionallyRegisterBlock("polished_coralstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.from(CORALSTONE.get())), () -> isLoaded("quark"));
    public static final RegistryObject<Item> POLISHED_CORALSTONE_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("polished_coralstone_vertical_slab", () -> new BlockItem(POLISHED_CORALSTONE_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistryObject<Block> ALGAE_GRASS = BLOCKS.register("algae_grass", () -> new AlgaeGrassBlock(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.LIME).sound(SoundType.WET_GRASS).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> ALGAE_GRASS_ITEM = ITEMS.register("algae_grass", () -> new BlockItemBase(ALGAE_GRASS.get()));

    public static final RegistryObject<Block> ALGAE_BLOCK = BLOCKS.register("algae_block", () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIME).sound(SoundType.WET_GRASS).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> ALGAE_BLOCK_ITEM = ITEMS.register("algae_block", () -> new BlockItemBase(ALGAE_BLOCK.get()));

    public static final RegistryObject<Block> PATTY_TILES = BLOCKS.register("patty_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.ORANGE_TERRACOTTA).sound(SoundType.HYPHAE).hardnessAndResistance(1F, 1F).harvestTool(ToolType.SHOVEL).harvestLevel(0)));
    public static final RegistryObject<Item> PATTY_TILES_ITEM = ITEMS.register("patty_tiles", () -> new BlockItemBase(PATTY_TILES.get()));

    public static final RegistryObject<Block> BAMBOO_WALL = BLOCKS.register("bamboo_wall", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static final RegistryObject<Item> BAMBOO_WALL_ITEM = ITEMS.register("bamboo_wall", () -> new BlockItemBase(BAMBOO_WALL.get()));

    public static final RegistryObject<Block> PINK_BAMBOO_WALL = BLOCKS.register("pink_bamboo_wall", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static final RegistryObject<Item> PINK_BAMBOO_WALL_ITEM = ITEMS.register("pink_bamboo_wall", () -> new BlockItemBase(PINK_BAMBOO_WALL.get()));

    public static final RegistryObject<Block> BLUE_BAMBOO_WALL = BLOCKS.register("blue_bamboo_wall", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static final RegistryObject<Item> BLUE_BAMBOO_WALL_ITEM = ITEMS.register("blue_bamboo_wall", () -> new BlockItemBase(BLUE_BAMBOO_WALL.get()));

    public static final RegistryObject<Block> YELLOW_BAMBOO_WALL = BLOCKS.register("yellow_bamboo_wall", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static final RegistryObject<Item> YELLOW_BAMBOO_WALL_ITEM = ITEMS.register("yellow_bamboo_wall", () -> new BlockItemBase(YELLOW_BAMBOO_WALL.get()));


    public static final RegistryObject<Block> WHITE_CARPETED_TILES = BLOCKS.register("white_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> WHITE_CARPETED_TILES_ITEM = ITEMS.register("white_carpeted_tiles", () -> new BlockItemBase(WHITE_CARPETED_TILES.get()));

    public static final RegistryObject<Block> ORANGE_CARPETED_TILES = BLOCKS.register("orange_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> ORANGE_CARPETED_TILES_ITEM = ITEMS.register("orange_carpeted_tiles", () -> new BlockItemBase(ORANGE_CARPETED_TILES.get()));

    public static final RegistryObject<Block> MAGENTA_CARPETED_TILES = BLOCKS.register("magenta_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.MAGENTA_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> MAGENTA_CARPETED_TILES_ITEM = ITEMS.register("magenta_carpeted_tiles", () -> new BlockItemBase(MAGENTA_CARPETED_TILES.get()));

    public static final RegistryObject<Block> LIGHT_BLUE_CARPETED_TILES = BLOCKS.register("light_blue_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> LIGHT_BLUE_CARPETED_TILES_ITEM = ITEMS.register("light_blue_carpeted_tiles", () -> new BlockItemBase(LIGHT_BLUE_CARPETED_TILES.get()));

    public static final RegistryObject<Block> YELLOW_CARPETED_TILES = BLOCKS.register("yellow_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> YELLOW_CARPETED_TILES_ITEM = ITEMS.register("yellow_carpeted_tiles", () -> new BlockItemBase(YELLOW_CARPETED_TILES.get()));

    public static final RegistryObject<Block> LIME_CARPETED_TILES = BLOCKS.register("lime_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> LIME_CARPETED_TILES_ITEM = ITEMS.register("lime_carpeted_tiles", () -> new BlockItemBase(LIME_CARPETED_TILES.get()));

    public static final RegistryObject<Block> PINK_CARPETED_TILES = BLOCKS.register("pink_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> PINK_CARPETED_TILES_ITEM = ITEMS.register("pink_carpeted_tiles", () -> new BlockItemBase(PINK_CARPETED_TILES.get()));

    public static final RegistryObject<Block> GRAY_CARPETED_TILES = BLOCKS.register("gray_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> GRAY_CARPETED_TILES_ITEM = ITEMS.register("gray_carpeted_tiles", () -> new BlockItemBase(GRAY_CARPETED_TILES.get()));

    public static final RegistryObject<Block> LIGHT_GRAY_CARPETED_TILES = BLOCKS.register("light_gray_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> LIGHT_GRAY_CARPETED_TILES_ITEM = ITEMS.register("light_gray_carpeted_tiles", () -> new BlockItemBase(LIGHT_GRAY_CARPETED_TILES.get()));

    public static final RegistryObject<Block> CYAN_CARPETED_TILES = BLOCKS.register("cyan_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.CYAN_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> CYAN_CARPETED_TILES_ITEM = ITEMS.register("cyan_carpeted_tiles", () -> new BlockItemBase(CYAN_CARPETED_TILES.get()));

    public static final RegistryObject<Block> PURPLE_CARPETED_TILES = BLOCKS.register("purple_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> PURPLE_CARPETED_TILES_ITEM = ITEMS.register("purple_carpeted_tiles", () -> new BlockItemBase(PURPLE_CARPETED_TILES.get()));

    public static final RegistryObject<Block> BLUE_CARPETED_TILES = BLOCKS.register("blue_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> BLUE_CARPETED_TILES_ITEM = ITEMS.register("blue_carpeted_tiles", () -> new BlockItemBase(BLUE_CARPETED_TILES.get()));

    public static final RegistryObject<Block> BROWN_CARPETED_TILES = BLOCKS.register("brown_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> BROWN_CARPETED_TILES_ITEM = ITEMS.register("brown_carpeted_tiles", () -> new BlockItemBase(BROWN_CARPETED_TILES.get()));

    public static final RegistryObject<Block> GREEN_CARPETED_TILES = BLOCKS.register("green_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> GREEN_CARPETED_TILES_ITEM = ITEMS.register("green_carpeted_tiles", () -> new BlockItemBase(GREEN_CARPETED_TILES.get()));

    public static final RegistryObject<Block> RED_CARPETED_TILES = BLOCKS.register("red_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> RED_CARPETED_TILES_ITEM = ITEMS.register("red_carpeted_tiles", () -> new BlockItemBase(RED_CARPETED_TILES.get()));

    public static final RegistryObject<Block> BLACK_CARPETED_TILES = BLOCKS.register("black_carpeted_tiles", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(JellyfishingSounds.CARPETED_WOOD)));
    public static final RegistryObject<Item> BLACK_CARPETED_TILES_ITEM = ITEMS.register("black_carpeted_tiles", () -> new BlockItemBase(BLACK_CARPETED_TILES.get()));




    public static final RegistryObject<Block> SEANUT_BUSH = BLOCKS.register("seanut_bush", () -> new SeanutBush(AbstractBlock.Properties.create(Material.OCEAN_PLANT).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> PINEAPPLE_PLANT = BLOCKS.register("pineapple_plant", () -> new PineapplePlant(AbstractBlock.Properties.create(Material.OCEAN_PLANT).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> BUBBLE_BLOCK = BLOCKS.register("bubble_block", () -> new BubbleBlock(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.GRASS).slipperiness(0.8F).sound(SoundType.SLIME).notSolid()));

    public static final RegistryObject<Block> GRILL = BLOCKS.register("grill", () -> new DirectionalBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2F, 2F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().notSolid()));
    public static final RegistryObject<Item> GRILL_ITEM = ITEMS.register("grill", () -> new BlockItem(GRILL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));


    public static final RegistryObject<Block> POTTED_PINEAPPLE = BLOCKS.register("potted_pineapple", () -> new FlowerPotBlock( () -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> PINEAPPLE_PLANT.get(), AbstractBlock.Properties.from(Blocks.FLOWER_POT)));


    //public static final RegistryObject<Block> JELLYFISH_MACHINE = BLOCKS.register("jellyfish_machine", () -> new JellyfishMachineBlock(Block.Properties.create(Material.CLAY, MaterialColor.BROWN).sound(SoundType.METAL)));
    //public static final RegistryObject<Item> JELLYFISH_MACHINE_ITEM = ITEMS.register("jellyfish_machine", () -> new BlockItemBase(JELLYFISH_MACHINE.get()));





    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void registerFlammables() {
        registerFlammable(BAMBOO_WALL.get(), 10, 20);
        registerFlammable(PINK_BAMBOO_WALL.get(), 10, 20);
        registerFlammable(BLUE_BAMBOO_WALL.get(), 10, 20);
        registerFlammable(YELLOW_BAMBOO_WALL.get(), 10, 20);

        registerFlammable(WHITE_CARPETED_TILES.get(), 8, 20);
        registerFlammable(ORANGE_CARPETED_TILES.get(), 8, 20);
        registerFlammable(MAGENTA_CARPETED_TILES.get(), 8, 20);
        registerFlammable(LIGHT_BLUE_CARPETED_TILES.get(), 8, 20);
        registerFlammable(YELLOW_CARPETED_TILES.get(), 8, 20);
        registerFlammable(LIME_CARPETED_TILES.get(), 8, 20);
        registerFlammable(PINK_CARPETED_TILES.get(), 8, 20);
        registerFlammable(GRAY_CARPETED_TILES.get(), 8, 20);
        registerFlammable(LIGHT_GRAY_CARPETED_TILES.get(), 8, 20);
        registerFlammable(CYAN_CARPETED_TILES.get(), 8, 20);
        registerFlammable(PURPLE_CARPETED_TILES.get(), 8, 20);
        registerFlammable(BLUE_CARPETED_TILES.get(), 8, 20);
        registerFlammable(BROWN_CARPETED_TILES.get(), 8, 20);
        registerFlammable(GREEN_CARPETED_TILES.get(), 8, 20);
        registerFlammable(RED_CARPETED_TILES.get(), 8, 20);
        registerFlammable(BLACK_CARPETED_TILES.get(), 8, 20);
    }



    public static void registerFlammable(Block block, int encouragement, int flammability) {
        FireBlock fire = (FireBlock)Blocks.FIRE;
        fire.setFireInfo(block, encouragement, flammability);
    }

    public static RegistryObject<Item> conditionallyRegisterItem(String registryName, Supplier<Item> item, Supplier<Boolean> condition) {
        if (condition.get())
            return ITEMS.register(registryName, item);
        return null;
    }
    public static RegistryObject<Block> conditionallyRegisterBlock(String registryName, Supplier<Block> block, Supplier<Boolean> condition) {
        if (condition.get())
            return BLOCKS.register(registryName, block);
        return null;
    }

    public static boolean isLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

}
