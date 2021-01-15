package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JellyfishingMod.MODID);

    public static final RegistryObject<BasicParticleType> CLOUD_PARTICLE = PARTICLES.register("cloud_particle", () -> new BasicParticleType(false));

    public static void init() {
        PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}