package blueduck.jellyfishing.jellyfishingmod;

import blueduck.jellyfishing.jellyfishingmod.biomes.JellyfishFields;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.BlueJellyfishRenderer;
import blueduck.jellyfishing.jellyfishingmod.client.entity.renderer.JellyfishRenderer;
import blueduck.jellyfishing.jellyfishingmod.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        RenderTypeLookup.setRenderLayer(JellyfishingBlocks.JELLY_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(JellyfishingBlocks.BLUE_JELLY_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(JellyfishingBlocks.CORAL_PLANT.get(), RenderType.getCutoutMipped());

        RenderingRegistry.registerEntityRenderingHandler(JellyfishingEntities.JELLYFISH.get(), manager -> new JellyfishRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(JellyfishingEntities.BLUE_JELLYFISH.get(), manager -> new BlueJellyfishRenderer(manager));


        ((JellyfishFields)JellyfishingBiomes.JELLYFISH_FIELDS.get()).addCreatureSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(JellyfishingEntities.JELLYFISH.get(), 100, 1, 1));
        ((JellyfishFields)JellyfishingBiomes.JELLYFISH_FIELDS.get()).addCreatureSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(JellyfishingEntities.BLUE_JELLYFISH.get(), 10, 1, 1));

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        BlockState WATER = Blocks.WATER.getDefaultState();

        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 50)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 50)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(125, 35, 0, 60))));
        DefaultBiomeFeatures.addOres(JellyfishingBiomes.JELLYFISH_FIELDS.get());
        DefaultBiomeFeatures.addExtraEmeraldOre(JellyfishingBiomes.JELLYFISH_FIELDS.get());

        JellyfishingBiomes.JELLYFISH_FIELDS.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SIMPLE_BLOCK.withConfiguration(new BlockWithContextConfig(JellyfishingBlocks.CORAL_PLANT.get().getDefaultState(), new BlockState[]{JellyfishingBlocks.CORAL_PLANT.get().getDefaultState()}, new BlockState[]{WATER}, new BlockState[]{WATER})).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(new NoPlacementConfig())));
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
}
