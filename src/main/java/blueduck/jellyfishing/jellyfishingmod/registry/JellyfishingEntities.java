package blueduck.jellyfishing.jellyfishingmod.registry;

import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.SpatulaEntity;
import blueduck.jellyfishing.jellyfishingmod.items.JellyfishingSpawnEgg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, JellyfishingMod.MODID);


    public static final RegistryObject<EntityType<JellyfishEntity>> JELLYFISH = ENTITIES.register("jellyfish", () -> EntityType.Builder.<JellyfishEntity>create(JellyfishEntity::new, EntityClassification.WATER_CREATURE).size(0.5F, 0.4F).build(new ResourceLocation("jellyfishing", "textures/entities/jellyfish.png").toString()));
    public static final RegistryObject<EntityType<BlueJellyfishEntity>> BLUE_JELLYFISH = ENTITIES.register("blue_jellyfish", () -> EntityType.Builder.<BlueJellyfishEntity>create(BlueJellyfishEntity::new, EntityClassification.WATER_CREATURE).size(0.5F, 0.4F).build(new ResourceLocation("jellyfishing", "textures/entities/blue_jellyfish.png").toString()));

    public static final RegistryObject<EntityType<MinecartEntity>> PATTY_WAGON = ENTITIES.register("patty_wagon", () -> EntityType.Builder.<MinecartEntity>create(MinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(new ResourceLocation("jellyfishing", "textures/entities/patty_wagon").toString()));

    public static final RegistryObject<EntityType<SpatulaEntity>> SPATULA = ENTITIES.register("spatula", () -> EntityType.Builder.<SpatulaEntity>create(SpatulaEntity::new, EntityClassification.MISC).size(0.3F, 0.3F).build(new ResourceLocation("jellyfishing", "textures/entities/patty_wagon").toString()));



    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
