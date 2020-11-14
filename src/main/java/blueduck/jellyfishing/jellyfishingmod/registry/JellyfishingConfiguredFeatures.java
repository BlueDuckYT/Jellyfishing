package blueduck.jellyfishing.jellyfishingmod.registry;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class JellyfishingConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> CONFIGURED_CORAL_PLANT = JellyfishingFeatures.CORAL_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.5F)).range(32).square().func_242731_b(10);
    public static final ConfiguredFeature<?, ?> CONFIGURED_TUBE_PLANT = JellyfishingFeatures.TUBE_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.2F)).range(32).square().func_242731_b(4);
    public static final ConfiguredFeature<?, ?> CONFIGURED_SEANUT_BUSH = JellyfishingFeatures.SEANUT_BUSH_FEATURE.get().withConfiguration(new ProbabilityConfig(.1F)).range(32).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> CONFIGURED_CORALSTONE_REPLACEMENT = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 100)).range(300).square().func_242731_b(250);

}
