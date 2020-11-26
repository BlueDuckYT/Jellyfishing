package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.misc.CloudParticle;
import com.mojang.serialization.Codec;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.gen.feature.Feature;
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