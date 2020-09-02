package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, JellyfishingMod.MODID);

    public static final RegistryObject<SoundEvent> STING = SOUNDS.register("sting", () -> new SoundEvent(new ResourceLocation("jellyfishing", "entity.jellyfish.sting")));

    public static final RegistryObject<SoundEvent> JELLYFISH_FIELDS = SOUNDS.register("jellyfish_fields", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.jellyfishfields")));


    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
