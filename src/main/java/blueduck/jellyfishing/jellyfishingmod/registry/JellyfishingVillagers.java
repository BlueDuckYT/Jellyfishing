package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.misc.PlunderingEnchantment;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.coremod.api.ASMAPI;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

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
