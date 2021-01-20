package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingVillagers {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, JellyfishingMod.MODID);
    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, JellyfishingMod.MODID);

    public static final RegistryObject<PointOfInterestType> FRYCOOK_POI = POI_TYPES.register("frycook",
            () -> new PointOfInterestType("frycook", PointOfInterestType.getAllStates(JellyfishingBlocks.GRILL.get()), 1, 1)
    );

    public static final RegistryObject<VillagerProfession> FRYCOOK = PROFESSIONS.register("frycook",
            () -> new VillagerProfession("frycook", FRYCOOK_POI.get(),
                    ImmutableSet.of(),
                    ImmutableSet.of(JellyfishingBlocks.GRILL.get()), null)
    );

    public static void init() {
        PROFESSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        POI_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
