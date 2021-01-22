package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.blocks.PineapplePlant;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class JellyfishingConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> CONFIGURED_CORAL_PLANT = JellyfishingFeatures.CORAL_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.5F)).range(32).square().func_242731_b(5);
    public static final ConfiguredFeature<?, ?> CONFIGURED_TUBE_PLANT = JellyfishingFeatures.TUBE_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.2F)).range(32).square().func_242731_b(2);
    public static final ConfiguredFeature<?, ?> CONFIGURED_SEANUT_BUSH = JellyfishingFeatures.SEANUT_BUSH_FEATURE.get().withConfiguration(new ProbabilityConfig(.03F)).range(32).square().func_242731_b(2);
    public static final ConfiguredFeature<?, ?> CONFIGURED_PINEAPPLE_PLANT = JellyfishingFeatures.PINEAPPLE_PLANT_FEATURE.get().withConfiguration(new ProbabilityConfig(.05F)).range(32).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> CONFIGURED_PINEAPPLE_PLANT_PATCH = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(JellyfishingBlocks.PINEAPPLE_PLANT.get().getDefaultState().with(PineapplePlant.AGE, Integer.valueOf(3))), SimpleBlockPlacer.PLACER).tries(24).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().build()).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> CONFIGURED_CORALSTONE_REPLACEMENT = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 100)).range(300).square().func_242731_b(250);



}
