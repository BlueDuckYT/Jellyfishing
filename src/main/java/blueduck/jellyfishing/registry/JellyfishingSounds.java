package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JellyfishingMod.MODID);

    public static final RegistryObject<SoundEvent> STING = SOUNDS.register("sting", () -> new SoundEvent(new ResourceLocation("jellyfishing", "entity.jellyfish.sting")));

    public static final RegistryObject<SoundEvent> JELLYFISH_FIELDS = SOUNDS.register("jellyfish_fields", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.jellyfishfields")));
    public static final RegistryObject<SoundEvent> BACKGROUND_MUSIC = SOUNDS.register("background_music", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.general")));

    public static final SoundType CARPETED_WOOD = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_WOOD_BREAK, SoundEvents.BLOCK_WOOL_STEP, SoundEvents.BLOCK_WOOD_PLACE, SoundEvents.BLOCK_WOOD_HIT, SoundEvents.BLOCK_WOOL_FALL);


    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
