package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Constructor;
import java.util.Set;

public class JellyfishingProfessions {

    //public static final VillagerProfession FRYCOOK = create("frycook", JellyfishingPointofInterests.FRYCOOK, ImmutableSet.of(), ImmutableSet.of(JellyfishingBlocks.SEANUT_BRITTLE_BLOCK.get()), JellyfishingSounds.STING.get());
    private static Constructor<?> ctr1 = null;
    private static Constructor<?> ctr2 = null;

    @SuppressWarnings("unused")
    private static VillagerProfession create(String key, PointOfInterestType type, SoundEvent event)
    {
        return create(key, type, ImmutableSet.of(), ImmutableSet.of(), event);
    }

    private static VillagerProfession create(String key, PointOfInterestType type, ImmutableSet<Item> items, ImmutableSet<Block> blocks, SoundEvent event)
    {
        VillagerProfession ret = null;
        try {
            Constructor<VillagerProfession> ctr = VillagerProfession.class.getDeclaredConstructor(String.class,
                    PointOfInterestType.class, ImmutableSet.class, ImmutableSet.class, SoundEvent.class);
            ctr.setAccessible(true);
            ret = ctr.newInstance(key, type, items, blocks, event);
            ret.setRegistryName(JellyfishingMod.MODID, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void register(IForgeRegistry<VillagerProfession> registry)
    {
        //registry.register(FRYCOOK);
        //setupTrades();
        //setupLoot();
    }



}
