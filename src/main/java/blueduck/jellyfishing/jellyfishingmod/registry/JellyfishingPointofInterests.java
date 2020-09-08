package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Util;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

public class JellyfishingPointofInterests {

    public static final PointOfInterestType FRYCOOK = create("frycook", JellyfishingBlocks.SEANUT_BRITTLE_BLOCK.get(), 1, 1);

    private static PointOfInterestType create(String key, Block block, int maxFree, int proximity)
    {
        PointOfInterestType ret = null;
        try {
            Constructor<PointOfInterestType> ctr = PointOfInterestType.class.getDeclaredConstructor(String.class, Set.class, int.class, int.class);
            ctr.setAccessible(true);
            ret = ctr.newInstance(key, ImmutableSet.copyOf(block.getStateContainer().getValidStates()), maxFree, proximity);
            ret.setRegistryName(JellyfishingMod.MODID, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void register(IForgeRegistry<PointOfInterestType> registry)
    {
        registry.register(FRYCOOK);
        addStates(FRYCOOK);
    }

    static void addStates(PointOfInterestType type)
    {
        try {
            Method method = PointOfInterestType.class.getDeclaredMethod("func_221052_a", PointOfInterestType.class);
            method.setAccessible(true);
            method.invoke(null, type);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
