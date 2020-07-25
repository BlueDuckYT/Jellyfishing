package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.items.JellyfishingSpawnEgg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, JellyfishingMod.MODID);


    public static final RegistryObject<EntityType<JellyfishEntity>> JELLYFISH = ENTITIES.register("jellyfish", () -> EntityType.Builder.<JellyfishEntity>create(JellyfishEntity::new, EntityClassification.WATER_CREATURE).size(0.5F, 0.5F).build(new ResourceLocation("jellyfishing", "textures/entities/jellyfish.png").toString()));
    public static final RegistryObject<EntityType<BlueJellyfishEntity>> BLUE_JELLYFISH = ENTITIES.register("blue_jellyfish", () -> EntityType.Builder.<BlueJellyfishEntity>create(BlueJellyfishEntity::new, EntityClassification.WATER_CREATURE).size(0.5F, 0.5F).build(new ResourceLocation("jellyfishing", "textures/entities/blue_jellyfish.png").toString()));


    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JellyfishingMod.MODID);

    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> JELLYFISH.get(),16724691, 16724595, new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BLUE_JELLYFISH_SPAWN_EGG = ITEMS.register("blue_jellyfish_spawn_egg", () -> new JellyfishingSpawnEgg(() -> BLUE_JELLYFISH.get(),3105279, 3120127, new Item.Properties().group(ItemGroup.MISC)));


    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
