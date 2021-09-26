package blueduck.jellyfishing.misc;

import blueduck.jellyfishing.JellyfishingMod;
import com.minecraftabnormals.abnormals_core.core.util.BiomeUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.BiomeSubRegistryHelper;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;

import javax.annotation.Nullable;

public class AbnormalsCoreOceanBiomeRegistry {
    public static void registerBiome(RegistryKey<Biome> biome, @Nullable RegistryKey<Biome> deep, int weight) {
        BiomeUtil.addOceanBiome(BiomeUtil.OceanType.WARM, biome, deep, weight);
    }
}
