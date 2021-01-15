package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingPaintings {

    public static final DeferredRegister<PaintingType> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, JellyfishingMod.MODID);

    public static final RegistryObject<PaintingType> CAPTAIN = PAINTINGS.register("captain", ()-> new PaintingType(64, 48));
    public static final RegistryObject<PaintingType> BOLD_AND_BRASH = PAINTINGS.register("bold_and_brash", ()-> new PaintingType(16, 32));
    public static final RegistryObject<PaintingType> JELLYFISH = PAINTINGS.register("jellyfish", ()-> new PaintingType(16, 16));
    public static final RegistryObject<PaintingType> JELLYFISH_FIELDS = PAINTINGS.register("jellyfish_fields", ()-> new PaintingType(32, 16));
    public static final RegistryObject<PaintingType> PATTY_WAGON = PAINTINGS.register("patty_wagon", ()-> new PaintingType(32, 32));
    public static final RegistryObject<PaintingType> MILLIONTH_DOLLAR = PAINTINGS.register("millionth_dollar", ()-> new PaintingType(32, 16));



    public static void init() {
        PAINTINGS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
