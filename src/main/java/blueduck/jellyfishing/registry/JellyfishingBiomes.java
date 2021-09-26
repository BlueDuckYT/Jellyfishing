package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.biomes.JellyfishFields;
import blueduck.jellyfishing.misc.AbnormalsCoreOceanBiomeRegistry;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, JellyfishingMod.MODID);

    public static final RegistryObject<Biome> JELLYFISH_FIELDS = BIOMES.register("jellyfish_fields", () -> new JellyfishFields().getBiome());


    public static void init() {
        BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void registerBiomes() {
        registerBiome(JELLYFISH_FIELDS.get(), BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.OVERWORLD);
    }
    public static void registerBiome(Biome biome, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("jellyfishing:jellyfish_fields")), types);
        if (ModList.get().isLoaded("abnormals_core")) {
            RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("jellyfishing:jellyfish_fields"));
            AbnormalsCoreOceanBiomeRegistry.registerBiome(key, key, JellyfishingMod.CONFIG.BIOME_WEIGHT.get());
        }
        else {
            BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("jellyfishing:jellyfish_fields")), JellyfishingMod.CONFIG.BIOME_WEIGHT.get()));
        }
    }

    //For Porting to 1.16.2
    //https://gist.github.com/CorgiTaco/3eb2d9128a1ec41bd5d5846d17994851

}


