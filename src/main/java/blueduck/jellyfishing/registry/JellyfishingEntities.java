package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.entities.JellyfishEntity;
import blueduck.jellyfishing.entities.PattyWagonEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, JellyfishingMod.MODID);


    public static final RegistryObject<EntityType<JellyfishEntity>> JELLYFISH = ENTITIES.register("jellyfish", () -> EntityType.Builder.<JellyfishEntity>create(JellyfishEntity::new, EntityClassification.WATER_AMBIENT).size(0.5F, 0.4F).build(new ResourceLocation("jellyfishing", "textures/entities/jellyfish.png").toString()));
    public static final RegistryObject<EntityType<BlueJellyfishEntity>> BLUE_JELLYFISH = ENTITIES.register("blue_jellyfish", () -> EntityType.Builder.<BlueJellyfishEntity>create(BlueJellyfishEntity::new, EntityClassification.WATER_AMBIENT).size(0.5F, 0.4F).build(new ResourceLocation("jellyfishing", "textures/entities/blue_jellyfish.png").toString()));

    public static final RegistryObject<EntityType<PattyWagonEntity>> PATTY_WAGON = ENTITIES.register("patty_wagon", () -> EntityType.Builder.<PattyWagonEntity>create(PattyWagonEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build(new ResourceLocation("jellyfishing", "textures/entities/patty_wagon").toString()));


    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
