package blueduck.jellyfishing;

import blueduck.jellyfishing.client.entity.renderer.BlueJellyfishRenderer;
import blueduck.jellyfishing.client.entity.renderer.JellyfishRenderer;
import blueduck.jellyfishing.client.entity.renderer.PattyWagonRenderer;
import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.entities.JellyfishEntity;
import blueduck.jellyfishing.items.JellyfishingSpawnEgg;
import blueduck.jellyfishing.misc.*;
import blueduck.jellyfishing.registry.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
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
    public static JellyfishingClientConfig CLIENT_CONFIG;

    public JellyfishingMod() {
        CONFIG = ConfigHelper.register(ModConfig.Type.COMMON, JellyfishingConfig::new);
        CLIENT_CONFIG = ConfigHelper.register(ModConfig.Type.CLIENT, JellyfishingClientConfig::new);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.addListener(JellyfishingMod::onFluidChangeBlock);

        JellyfishingEnchantments.init();
        JellyfishingBlocks.init();
        JellyfishingItems.init();
        JellyfishingEntities.init();
        JellyfishingFeatures.init();
        JellyfishingBiomes.init();
        JellyfishingSounds.init();
        JellyfishingPaintings.init();
        JellyfishingParticles.init();
        JellyfishingVillageStructures.init();
        JellyfishingVillagers.init();



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        GlobalEntityTypeAttributes.put(JellyfishingEntities.JELLYFISH.get(), AbstractJellyfishEntity.createAttributes().create()/*(or your own)*/);
        GlobalEntityTypeAttributes.put(JellyfishingEntities.BLUE_JELLYFISH.get(), AbstractJellyfishEntity.createAttributes().create()/*(or your own)*/);

        EntitySpawnPlacementRegistry.register(JellyfishingEntities.JELLYFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canSpawn);
        EntitySpawnPlacementRegistry.register(JellyfishingEntities.BLUE_JELLYFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canSpawn);


        ImmutableMap.Builder<Item,Integer> FOOD_VALUES_BUILDER = ImmutableMap.builder();
        ImmutableSet.Builder<Item> ALLOWED_ITEMS_BUILDER = ImmutableSet.builder();

        VillagerEntity.FOOD_VALUES = FOOD_VALUES_BUILDER.putAll(VillagerEntity.FOOD_VALUES).put(JellyfishingItems.ROASTED_SEANUT.get(), 2).put(JellyfishingItems.SEANUT_BRITTLE.get(), 5).put(JellyfishingItems.JELLYFISH_JELLY_SANDWICH.get(), 4).put(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.get(), 5).put(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.get(), 5).put(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.get(), 6).put(JellyfishingItems.KRABBY_PATTY.get(), 20).put(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 8).put(JellyfishingItems.PINEAPPLE.get(), 1).build();
        VillagerEntity.ALLOWED_INVENTORY_ITEMS = ALLOWED_ITEMS_BUILDER.addAll(VillagerEntity.ALLOWED_INVENTORY_ITEMS).add(JellyfishingItems.ROASTED_SEANUT.get()).add(JellyfishingItems.SEANUT_BRITTLE.get()).add(JellyfishingItems.JELLYFISH_JELLY_SANDWICH.get()).add(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.get()).add(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.get()).add(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.get()).add(JellyfishingItems.KRABBY_PATTY.get()).add(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get()).add(JellyfishingItems.PINEAPPLE.get()).build();

        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

        pot.addPlant(new ResourceLocation("jellyfishing:pineapple_seeds"), JellyfishingBlocks.POTTED_PINEAPPLE);

        event.enqueueWork(() -> {
            PointOfInterestType.registerBlockStates(JellyfishingVillagers.FRYCOOK_POI.get());
            PointOfInterestType.BLOCKS_OF_INTEREST.addAll(JellyfishingVillagers.FRYCOOK_POI.get().blockStates);

            JellyfishingBlocks.registerFlammables();
            JellyfishingItems.registerCompostables();
            JellyfishingConfiguredFeatures.registerConfiguredFeatures();
        });

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

    public static void onFluidChangeBlock(BlockEvent.FluidPlaceBlockEvent event) {
        if (event.getWorld().getBlockState(event.getPos().down()).equals(JellyfishingBlocks.JELLY_BLOCK.get().getDefaultState()) && event.getNewState().equals(Blocks.COBBLESTONE.getDefaultState())) {
            event.setNewState(JellyfishingBlocks.CORALSTONE.get().getDefaultState());
        }
    }

    @SubscribeEvent
    public void entitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        if (!event.getWorld().isRemote()) {
            if (event.getEntityLiving() instanceof DrownedEntity) {
                if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.025) {
                    if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {
                        event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(JellyfishingItems.KELP_MUSTACHE.get()));
                        ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.HEAD, 0.085F);

                    }
                }
                if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.01) {
                    if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                        event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(JellyfishingItems.JELLYFISH_NET.get()));
                        ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.MAINHAND, 0.085F);

                    }
                }
                if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.01) {
                    if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                        event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(JellyfishingItems.SPATULA.get()));
                        ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.MAINHAND, 0.085F);

                    }
                }
                if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.02) {
                    if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.9) {
                        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                            event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(JellyfishingItems.KARATE_GLOVE.get()));
                            ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.MAINHAND, 0.085F);
                        }
                        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty()) {
                            event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(JellyfishingItems.KARATE_GLOVE.get()));
                            ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.OFFHAND, 0.085F);

                        }
                    }
                    else if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.5) {
                        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                            event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                            ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.MAINHAND, 0.085F);
                        }
                        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty()) {
                            event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                            ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.OFFHAND, 0.085F);

                        }
                    }
                    else {
                        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
                            event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(JellyfishingItems.POWER_KARATE_GLOVE.get()));
                            ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.MAINHAND, 0.085F);
                        }
                        if (event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty()) {
                            event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(JellyfishingItems.POWER_KARATE_GLOVE.get()));
                            ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.OFFHAND, 0.085F);

                        }
                    }
                }
                if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.01) {
                    event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(JellyfishingItems.AIR_SUIT_HELMET.get()));
                    ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.HEAD, 0.01F);

                    if (event.getEntityLiving().getEntityWorld().getRandom().nextDouble() < 0.5) {
                        event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                        ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.MAINHAND, 0.01F);
                        event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                        ((MobEntity) (event.getEntityLiving())).setDropChance(EquipmentSlotType.OFFHAND, 0.01F);

                    }
                }
            }
        }
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

            if (event.getCategory().equals(Biome.Category.JUNGLE)) {
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_PINEAPPLE_PLANT_PATCH);

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
            if (event.getName().equals(new ResourceLocation("minecraft", "chests/end_city_treasure"))) {
                LootPool pool = event.getTable().getPool("main");
                if (pool != null) {
                    addEntry(pool, getInjectEntry(new ResourceLocation("jellyfishing:chests/end_city_treasure"), 10, 1));
                }}


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
            if (event.getType() == VillagerProfession.FARMER) {
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.SEANUT.get(), 24), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.TOOLSMITH) {
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(JellyfishingItems.JELLYFISH_NET.get()),   5, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.WEAPONSMITH) {
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(JellyfishingItems.SPATULA.get()),   3, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.ARMORER) {
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD,  2), new ItemStack(JellyfishingItems.KELP_MUSTACHE.get()),  5, 10, 0.05F));
            }
            if (event.getType() == VillagerProfession.BUTCHER) {
                event.getTrades().get(5).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(JellyfishingItems.KRABBY_PATTY.get()),   5, 10, 0.05F));
            }

            if (event.getType() == JellyfishingVillagers.FRYCOOK.get()) {
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.JELLYFISH_JELLY.get(), 20), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 16), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.SEANUT_BUTTER.get(), 8), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));
                event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.SEANUT.get(), 20), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));

                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.JELLYFISH.get(), 5), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));
                event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.BLUE_JELLYFISH.get(), 2), new ItemStack(Items.EMERALD, 1), 8, 10, 0.05F));

                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.ROASTED_SEANUT.get(), 36),  16, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingItems.JELLYFISH_JELLY_SANDWICH.get(), 2),  16, 10, 0.05F));
                event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.get(), 1),  16, 10, 0.05F));

                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 3),  16, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.get(), 1),  16, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.get(), 1),  16, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.SEANUT_BRITTLE.get(), 8),  16, 10, 0.05F));
                event.getTrades().get(4).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(JellyfishingItems.KELP_SHAKE.get(), 1),  16, 10, 0.05F));

                event.getTrades().get(5).add((entity, random) -> new MerchantOffer(new ItemStack(JellyfishingItems.GREASE_BALL.get(), 1), new ItemStack(Items.EMERALD, 32), 8, 10, 0.05F));
                event.getTrades().get(5).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(JellyfishingItems.KRABBY_PATTY.get(), 1),  8, 10, 0.05F));
                event.getTrades().get(5).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 8), new ItemStack(JellyfishingItems.KARATE_GLOVE.get(), 1),  3, 10, 0.05F));

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
            event.getRareTrades().add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(JellyfishingItems.BUBBLE_WAND.get(), 1), 2, 10, 0.05F));
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
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.BUBBLE_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.CORAL_PLANT.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.TUBE_PLANT.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.SEANUT_BUSH.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.PINEAPPLE_PLANT.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.POTTED_PINEAPPLE.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.SCRAP_METAL_WINDOW.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.CHROME_DOOR.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(JellyfishingBlocks.CHROME_VENT.get(), RenderType.getCutoutMipped());


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
