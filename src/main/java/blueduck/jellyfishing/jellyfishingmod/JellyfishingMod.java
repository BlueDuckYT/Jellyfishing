package blueduck.jellyfishing.jellyfishingmod;

import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.BlueJellyfishRenderer;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.JellyfishRenderer;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.PattyWagonRenderer;
import blueduck.jellyfishing.jellyfishingmod.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.items.JellyfishingSpawnEgg;
import blueduck.jellyfishing.jellyfishingmod.misc.CloudParticle;
import blueduck.jellyfishing.jellyfishingmod.misc.ConfigHelper;
import blueduck.jellyfishing.jellyfishingmod.misc.JellyfishingConfig;
import blueduck.jellyfishing.jellyfishingmod.registry.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

    public static JellyfishingConfig CONFIG;

    public JellyfishingMod() {
        CONFIG = ConfigHelper.register(ModConfig.Type.COMMON, JellyfishingConfig::new);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        JellyfishingEnchantments.init();
        JellyfishingBlocks.init();
        JellyfishingItems.init();
        JellyfishingEntities.init();
        JellyfishingFeatures.init();
        JellyfishingBiomes.init();
        JellyfishingSounds.init();
        JellyfishingPaintings.init();
        JellyfishingParticles.init();



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        GlobalEntityTypeAttributes.put(JellyfishingEntities.JELLYFISH.get(), AbstractJellyfishEntity.func_234176_m_().create()/*(or your own)*/);
        GlobalEntityTypeAttributes.put(JellyfishingEntities.BLUE_JELLYFISH.get(), AbstractJellyfishEntity.func_234176_m_().create()/*(or your own)*/);

        // JellyfishingFeatures.registerConfiguredFeatures();


//        JellyfishingBiomes.JELLYFISH_FIELDS.get().func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 50)).withPlacement(Placement.field_215028_n.configure(new CountRangeConfig(10, 0, 0, 256))));
//        JellyfishingBiomes.JELLYFISH_FIELDS.get().func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 50)).withPlacement(Placement.field_215028_n.configure(new CountRangeConfig(250, 35, 0, 60))));
//        DefaultBiomeFeatures.func_222288_h(JellyfishingBiomes.JELLYFISH_FIELDS.get());
//        DefaultBiomeFeatures.func_222291_j(JellyfishingBiomes.JELLYFISH_FIELDS.get());
        //JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(8, 0.1D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));


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
        }


    }


    @Mod.EventBusSubscriber(modid = "jellyfishing")
    public static class LootEvents {

        @SubscribeEvent
        public static void onBiomeLoad(BiomeLoadingEvent event) {
            if (event.getName().equals(new ResourceLocation("jellyfishing:jellyfish_fields"))) {

                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_CORAL_PLANT);
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_TUBE_PLANT);
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_SEANUT_BUSH);
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, JellyfishingConfiguredFeatures.CONFIGURED_CORALSTONE_REPLACEMENT);
            }
        }
        @SubscribeEvent
        public static void onLootLoad(LootTableLoadEvent event) throws IllegalAccessException {
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/shipwreck_treasure"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_treasure"))).name("jellyfishing_inject").build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/shipwreck_supply"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_supply"))).name("jellyfishing_inject").build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/underwater_ruin_big"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_supply"))).name("jellyfishing_inject").build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/underwater_ruin_small"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/shipwreck_supply"))).name("jellyfishing_inject").build());
            }
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/spawn_bonus_chest"))) {
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MODID, "chests/kelp_mustache"))).name("jellyfishing_inject").build());
            }


            //Fishing Loot Injection from Aquaculture 2 by Team Metallurgy
            ResourceLocation name = event.getName();
            if (name.equals(LootTables.GAMEPLAY_FISHING)) {
                LootPool pool = event.getTable().getPool("main");
                if (pool != null) {
                    if (CONFIG.JELLYFISH_FISHABLE.get()) {
                        addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing:gameplay/fishing/fish"), 10, 1));
                    }
                    if (CONFIG.NETS_FISHABLE.get()) {
                        addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing:gameplay/fishing/treasure_net"), 3, 1));
                    }
                    addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing:gameplay/fishing/junk_plants"), 1, -2));
                }
            }
            if (name.equals(LootTables.GAMEPLAY_HERO_OF_THE_VILLAGE_FISHERMAN_GIFT)) {
                LootPool pool = event.getTable().getPool("main");
                if (pool != null) {
                    addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing:gameplay/fishing/fish"), 1, 1));
                }
            }
            if (name.equals(LootTables.GAMEPLAY_HERO_OF_THE_VILLAGE_TOOLSMITH_GIFT)) {
                LootPool pool = event.getTable().getPool("main");
                if (pool != null) {
                    addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing:gameplay/toolsmith_net"), 1, 1));
                }
            }



        }

        private static LootEntry getInjectEntry(ResourceLocation location, int weight, int quality) {
            return TableLootEntry.builder(location).weight(weight).quality(quality).build();
        }



        private static void addEntry(LootPool pool, LootEntry entry) throws IllegalAccessException {
            List<LootEntry> lootEntries = (List<LootEntry>) ObfuscationReflectionHelper.findField(LootPool.class, "field_186453_a").get(pool);
            if (lootEntries.stream().anyMatch(e -> e == entry)) {
                throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
            }
            lootEntries.add(entry);
        }


        @SubscribeEvent
        public static void villagerTrades(final VillagerTradesEvent event) {
            if (event.getType() == VillagerProfession.FISHERMAN) {
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.JELLYFISH_JELLY.get(), 3), 5, 10, 0.05F));
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 3), 5, 10, 0.05F));

                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(JellyfishingItems.JELLYFISH_NET.get()), 5, 10, 0.05F));

                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 7), new ItemStack(JellyfishingItems.JELLYFISH.get()), 3, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 9), new ItemStack(JellyfishingItems.BLUE_JELLYFISH.get()), 3, 10, 0.05F));

                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingBlocks.CORAL_PLANT_ITEM.get(), 3),   5, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingBlocks.TUBE_PLANT_ITEM.get(), 2),   5, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.MASON) {
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingBlocks.POLISHED_CORALSTONE_ITEM.get(), 4), 5, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingBlocks.CORALSTONE_ITEM.get(), 8), 5, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingBlocks.CORALSTONE_ITEM.get(), 16), new ItemStack(Items.EMERALD, 1), 5, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingBlocks.CORALSTONE_ITEM.get(), 8), new ItemStack(Items.EMERALD, 1), 5, 10, 0.05F));
            }

            if (event.getType() == VillagerProfession.CLERIC) {
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.JELLYFISH_JELLY.get(), 4), new ItemStack(Items.EMERALD, 1),  8, 10, 0.05F));
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 3), new ItemStack(Items.EMERALD, 1),  8, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.SEANUT_BUTTER.get(), 3), new ItemStack(Items.EMERALD, 1),  6, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.KELP_SHAKE.get(), 1),  6, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 1),  5, 10, 0.05F));

            }
            if (event.getType() == VillagerProfession.TOOLSMITH) {
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(JellyfishingItems.JELLYFISH_NET.get()),   5, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.WEAPONSMITH) {
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(JellyfishingItems.SPATULA.get()),   3, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.ARMORER) {
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD,  2), new ItemStack(JellyfishingItems.KELP_MUSTACHE.get()),  5, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.BUTCHER) {
                event.getTrades().get(5).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(JellyfishingItems.KRABBY_PATTY.get()),   5, 10, 0.05F));
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
            event.getRareTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), new ItemStack(JellyfishingItems.KRABBY_PATTY.get(), 1), 3, 10, 0.05F));
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

        @SubscribeEvent
        public static void onParticleFactoryLoad(ParticleFactoryRegisterEvent event) {
            Minecraft.getInstance().particles.registerFactory(JellyfishingParticles.CLOUD_PARTICLE.get(), CloudParticle.Factory::new);

        }
        @SubscribeEvent
         public static void onItemColorEvent(ColorHandlerEvent.Item event) {
            for (final SpawnEggItem egg : JellyfishingSpawnEgg.JELLYFISHING_SPAWN_EGGS) {
                event.getItemColors().register((stack, i) -> egg.getColor(i), egg);
            }
        }

    }


}
