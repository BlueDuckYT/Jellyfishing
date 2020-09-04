package blueduck.jellyfishing.jellyfishingmod;

import blueduck.jellyfishing.jellyfishingmod.biomes.JellyfishFields;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.BlueJellyfishRenderer;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.JellyfishRenderer;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.PattyWagonRenderer;
import blueduck.jellyfishing.jellyfishingmod.items.JellyfishingSpawnEgg;
import blueduck.jellyfishing.jellyfishingmod.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("jellyfishing")
public class JellyfishingMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static String MODID = "jellyfishing";

    public JellyfishingMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        JellyfishingBlocks.init();
        JellyfishingItems.init();
        JellyfishingEntities.init();
        JellyfishingBiomes.init();
        JellyfishingSounds.init();
        JellyfishingTileEntities.init();
        JellyfishingPaintings.init();
        JellyfishingEnchantments.init();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        ((JellyfishFields)JellyfishingBiomes.JELLYFISH_FIELDS.get()).addCreatureSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(JellyfishingEntities.JELLYFISH.get(), 100, 1, 1));
        ((JellyfishFields)JellyfishingBiomes.JELLYFISH_FIELDS.get()).addCreatureSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(JellyfishingEntities.BLUE_JELLYFISH.get(), 10, 1, 1));


        BlockState WATER = Blocks.WATER.getDefaultState();


        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 50)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 50)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(250, 35, 0, 60))));
        DefaultBiomeFeatures.addOres(JellyfishingBiomes.JELLYFISH_FIELDS.get());
        DefaultBiomeFeatures.addExtraEmeraldOre(JellyfishingBiomes.JELLYFISH_FIELDS.get());
        //JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(8, 0.1D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));



        Biomes.WARM_OCEAN.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 30)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        Biomes.LUKEWARM_OCEAN.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 30)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        Biomes.DEEP_WARM_OCEAN.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 30)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        Biomes.DEEP_LUKEWARM_OCEAN.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 30)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));


        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRANITE.getDefaultState(), 20)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 40))));
        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIORITE.getDefaultState(), 20)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 40))));
        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.ANDESITE.getDefaultState(), 20)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 40))));



           }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

          }



    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("jellyfishing", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }



    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }



    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid = "jellyfishing")
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
        @SubscribeEvent
        public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
            JellyfishingBiomes.registerBiomes();
            JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingFeatures.CORAL_PLANT_FEATURE.withConfiguration(new CountConfig(5)));
            JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingFeatures.TUBE_PLANT_FEATURE.withConfiguration(new CountConfig(1)));
            JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingFeatures.SEANUT_BUSH_FEATURE.withConfiguration(new CountConfig(3)));

        }

    }


    @Mod.EventBusSubscriber(modid = "jellyfishing")
    public static class LootEvents {
        @SubscribeEvent
        public static void onLootLoad(LootTableLoadEvent event) {
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/shipwreck_treasure"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_treasure"))).build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/shipwreck_supply"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_supply"))).build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/underwater_ruin_big"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_supply"))).build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/underwater_ruin_small"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_supply"))).build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/spawn_bonus_chest"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/kelp_mustache"))).build());
            }



            if (event.getName().equals(new ResourceLocation("minecraft", "gameplay/fishing/fish"))) {
                List<LootPool> pools = ObfuscationReflectionHelper.getPrivateValue(LootTable.class, event.getTable(), "pools");
                //List<LootEntry> entries = ObfuscationReflectionHelper.getPrivateValue(LootPool.class, event.getTable(), "lootEntries");

                //(TableLootEntry.builder(new ResourceLocation(MODID, "gameplay/fishing/fish")));
                //event.getTable().pools.get(0).entries.add();
            }
        }
        @SubscribeEvent
        public static void villagerTrades(final VillagerTradesEvent event) {
            if (event.getType() == VillagerProfession.FISHERMAN) {
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.JELLYFISH_JELLY.get(), 3), 5, 10, 0.05F));
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 3), 5, 10, 0.05F));

                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(JellyfishingItems.JELLYFISH_NET.get()), 5, 10, 0.05F));

                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 7), new ItemStack(JellyfishingItems.JELLYFISH.get()), 3, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 9), new ItemStack(JellyfishingItems.BLUE_JELLYFISH.get()), 3, 10, 0.05F));



            }
            if (event.getType() == VillagerProfession.MASON) {
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingBlocks.POLISHED_CORALSTONE_ITEM.get(), 4), 5, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingBlocks.CORALSTONE_ITEM.get(), 8), new ItemStack(Items.EMERALD, 1), 5, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingBlocks.CORALSTONE_ITEM.get(), 8), 5, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingBlocks.CORALSTONE_ITEM.get(), 16), new ItemStack(Items.EMERALD, 1), 5, 10, 0.05F));
            }
        }

        @SubscribeEvent
        public static void traderTrades(final WandererTradesEvent event) {
            event.getGenericTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(JellyfishingItems.JELLYFISH_JELLY.get(), 6), 8, 10, 0.05F));
            event.getGenericTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), new ItemStack(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 4), 8, 10, 0.05F));
            event.getGenericTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingItems.KELP_MUSTACHE.get(), 1), 5, 10, 0.05F));

            event.getRareTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 1), 3, 10, 0.05F));
            event.getRareTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.JELLYFISH.get(), 1), 3, 10, 0.05F));
            event.getRareTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(JellyfishingItems.BLUE_JELLYFISH.get(), 1), 2, 10, 0.05F));
            event.getRareTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.SEANUT.get(), 1), 4, 10, 0.05F));
        }
    }


    @Mod.EventBusSubscriber(Dist.CLIENT)
    public static class RenderEventHandler {
        //@SubscribeEvent
        //public static void updateFogColor(ActiveRenderInfo activeRenderInfoIn, float partialTicks, ClientWorld worldIn, int renderDistanceChunks, float bossColorModifier) {

       // }



    }

    @Mod.EventBusSubscriber(modid = "jellyfishing", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEventBusSubscriber {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
            JellyfishingSpawnEgg.doDispenserSetup();
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.JELLY_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.BLUE_JELLY_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.CORAL_PLANT.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.TUBE_PLANT.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.SEANUT_BUSH.get(), RenderType.getCutoutMipped());


            RenderingRegistry.registerEntityRenderingHandler(JellyfishingEntities.JELLYFISH.get(), manager -> new JellyfishRenderer(manager));
            RenderingRegistry.registerEntityRenderingHandler(JellyfishingEntities.BLUE_JELLYFISH.get(), manager -> new BlueJellyfishRenderer(manager));

            RenderingRegistry.registerEntityRenderingHandler(JellyfishingEntities.PATTY_WAGON.get(), manager -> new PattyWagonRenderer(manager));
        }
    }


}
