package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyMachineTE;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, JellyfishingMod.MODID);


    //public static final RegistryObject<TileEntityType<JellyMachineTE>> JELLYFISH_MACHINE = TILE_ENTITIES.register("jellyfish_machine", () -> TileEntityType.Builder.<JellyMachineTE>create(JellyMachineTE::new, JellyfishingBlocks.JELLYFISH_MACHINE.get()).build(null));


    public static void init() {
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
