package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JellyfishingMod.MODID);

    public static final RegistryObject<SoundEvent> STING = SOUNDS.register("sting", () -> new SoundEvent(new ResourceLocation("jellyfishing", "entity.jellyfish.sting")));

    public static final RegistryObject<SoundEvent> JELLYFISH_FIELDS = SOUNDS.register("jellyfish_fields", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.jellyfishfields")));
    public static final RegistryObject<SoundEvent> BACKGROUND_MUSIC = SOUNDS.register("background_music", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.general")));


    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
