package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class JellyfishingPointofInterests {

    public static final DeferredRegister<PointOfInterestType> POINTS = new DeferredRegister<>(ForgeRegistries.POI_TYPES, JellyfishingMod.MODID);


    public static final RegistryObject<PointOfInterestType> FRYCOOK = POINTS.register("frycook", () -> new PointOfInterestType("frycook", getAllStates(JellyfishingBlocks.SEANUT_BRITTLE_BLOCK.get()), 0, 1));

    public static void init() {
        POINTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static Set<BlockState> getAllStates(Block blockIn) {
        return ImmutableSet.copyOf(blockIn.getStateContainer().getValidStates());
    }
}
