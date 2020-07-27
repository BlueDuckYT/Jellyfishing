package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyMachineTE;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyfishMachineBlock;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, JellyfishingMod.MODID);


    //public static final RegistryObject<ContainerType<>> JELLYFISH_MACHINE = CONTAINER_TYPES.register("jellyfish_machine", () -> ContainerType.Builder.<JellyMachineTE>create(JellyMachineTE::new, JellyfishingBlocks.JELLYFISH_MACHINE.get()).build(null));


    public static void init() {
        CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
