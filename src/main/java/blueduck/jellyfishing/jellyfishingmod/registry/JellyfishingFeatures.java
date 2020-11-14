package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.features.CoralPlantFeature;
import blueduck.jellyfishing.jellyfishingmod.features.SeanutBushFeature;
import blueduck.jellyfishing.jellyfishingmod.features.TubePlantFeature;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingFeatures extends net.minecraftforge.registries.ForgeRegistryEntry<Feature<?>> {


    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JellyfishingMod.MODID);

    public static final RegistryObject<Feature<ProbabilityConfig>> CORAL_PLANT_FEATURE = FEATURES.register("coral_plant_feature", () -> new CoralPlantFeature(ProbabilityConfig.CODEC));
    public static final RegistryObject<Feature<ProbabilityConfig>> TUBE_PLANT_FEATURE = FEATURES.register("tube_plant_feature", () ->new TubePlantFeature(ProbabilityConfig.CODEC));
    public static final RegistryObject<Feature<ProbabilityConfig>> SEANUT_BUSH_FEATURE = FEATURES.register("seanut_bush_feature", () -> new SeanutBushFeature(ProbabilityConfig.CODEC));


    public static void init() {
        FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

//    public static void registerConfiguredFeatures() {
//        register("coral_plant_feature", CORAL_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.5F)).range(32).square().func_242731_b(100));
//        register("tube_plant_feature", TUBE_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.1F)).range(32).square().func_242731_b(100));
//        register("seanut_bush_feature", SEANUT_BUSH_FEATURE.get().withConfiguration(new ProbabilityConfig(.2F)).range(32).square().func_242731_b(100));
//        register("coralstone_replacement", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 100)).range(300).square().func_242731_b(50));
//    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(JellyfishingMod.MODID, name), feature);
    }
}
