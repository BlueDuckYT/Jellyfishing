package blueduck.jellyfishing.jellyfishingmod.biomes;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingBlocks;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

public class JellyfishFields extends Biome {

    public JellyfishFields() {
        super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(JellyfishingBlocks.ALGAE_GRASS.get().getDefaultState(), JellyfishingBlocks.CORALSTONE.get().getDefaultState(), JellyfishingBlocks.ALGAE_GRASS.get().getDefaultState())).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.5F).scale(0.2F).temperature(0.5F).downfall(0.5F).waterColor(4566523).waterFogColor(604792).parent((String)null));

        DefaultBiomeFeatures.addOceanCarvers(this);
        DefaultBiomeFeatures.addStructures(this);


    }

    public void addCreatureSpawn(EntityClassification classification, Biome.SpawnListEntry entry) {
        this.addSpawn(classification, entry);
    }


}
