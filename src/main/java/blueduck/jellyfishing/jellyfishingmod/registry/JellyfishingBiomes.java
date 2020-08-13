package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.biomes.JellyfishFields;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, JellyfishingMod.MODID);

    public static final RegistryObject<Biome> JELLYFISH_FIELDS = BIOMES.register("jellyfish_fields", () -> new JellyfishFields());


    public static void init() {
        BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void registerBiomes() {
        registerBiome(JELLYFISH_FIELDS.get(), BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.OVERWORLD);
    }
    public static void registerBiome(Biome biome, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(biome, types);
        //BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, 6));

    }

    //For Porting to 1.16.2
    //https://gist.github.com/CorgiTaco/3eb2d9128a1ec41bd5d5846d17994851

}


