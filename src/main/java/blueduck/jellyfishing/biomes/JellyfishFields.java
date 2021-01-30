package blueduck.jellyfishing.biomes;

import blueduck.jellyfishing.JellyfishingMod;
import blueduck.jellyfishing.registry.JellyfishingBlocks;
import blueduck.jellyfishing.registry.JellyfishingEntities;
import blueduck.jellyfishing.registry.JellyfishingParticles;
import blueduck.jellyfishing.registry.JellyfishingSounds;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class JellyfishFields extends JellyfishingBiome {

    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "jellyfishing:jellyfish_fields", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(JellyfishingBlocks.ALGAE_GRASS.get().getDefaultState(), JellyfishingBlocks.CORALSTONE.get().getDefaultState(), JellyfishingBlocks.ALGAE_GRASS.get().getDefaultState())));
    static final Biome.Climate CLIMATE = new Biome.Climate(Biome.RainType.RAIN, 0.8F, Biome.TemperatureModifier.NONE, 0.4F);

    static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();

    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(SURFACE_BUILDER);

    static float CLOUDS = JellyfishingMod.CONFIG.FLOWER_CLOUDS.get() ? 0.000005F : 0f;
    static BackgroundMusicSelector MUSIC = JellyfishingMod.CLIENT_CONFIG.MUSIC.get() ? new BackgroundMusicSelector(JellyfishingSounds.BACKGROUND_MUSIC.get(), 200, 4000, false) : BackgroundMusicTracks.WORLD_MUSIC;

    //3448555
    public JellyfishFields() {
        super(CLIMATE, Biome.Category.OCEAN, -1.2F, .15F, (new BiomeAmbience.Builder()).setWaterColor(4566523).setWaterFogColor(2587774).setFogColor(12638463).withSkyColor(7842047).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).setParticle(new ParticleEffectAmbience(JellyfishingParticles.CLOUD_PARTICLE.get(), CLOUDS)).setMusic(MUSIC).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.copy());
    }
    static {

        GENERATION_SETTINGS.withStructure(StructureFeatures.RUINED_PORTAL_OCEAN);

        DefaultBiomeFeatures.withOverworldOres(GENERATION_SETTINGS);


//        GENERATION_SETTINGS.withFeature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_CORAL_PLANT);
//        GENERATION_SETTINGS.withFeature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_TUBE_PLANT);
//        GENERATION_SETTINGS.withFeature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_SEANUT_BUSH);
//        GENERATION_SETTINGS.withFeature(6, () -> JellyfishingConfiguredFeatures.CONFIGURED_CORALSTONE_REPLACEMENT);

        SPAWN_SETTINGS.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(JellyfishingEntities.JELLYFISH.get(), 1000, 1, 1));
        SPAWN_SETTINGS.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(JellyfishingEntities.BLUE_JELLYFISH.get(), 100, 1, 1));
    }
}
