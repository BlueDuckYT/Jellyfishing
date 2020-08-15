package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.features.CoralPlantFeature;
import blueduck.jellyfishing.jellyfishingmod.features.SeanutBushFeature;
import blueduck.jellyfishing.jellyfishingmod.features.TubePlantFeature;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingFeatures extends net.minecraftforge.registries.ForgeRegistryEntry<Feature<?>> {

    public static final Feature<CountConfig> CORAL_PLANT_FEATURE = register("coral_plant_feature", new CoralPlantFeature(CountConfig::deserialize));
    public static final Feature<CountConfig> TUBE_PLANT_FEATURE = register("tube_plant_feature", new TubePlantFeature(CountConfig::deserialize));
    public static final Feature<CountConfig> SEANUT_BUSH_FEATURE = register("seanut_bush_feature", new SeanutBushFeature(CountConfig::deserialize));


    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, key, value));
    }
}
