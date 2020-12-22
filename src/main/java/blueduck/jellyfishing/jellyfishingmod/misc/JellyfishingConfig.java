package blueduck.jellyfishing.jellyfishingmod.misc;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class JellyfishingConfig {


    public ConfigHelper.ConfigValueListener<Integer> BIOME_WEIGHT;
    public ConfigHelper.ConfigValueListener<Boolean> JELLYFISH_STING;
    public ConfigHelper.ConfigValueListener<Boolean> CAUGHT_JELLYFISH_STING;
    public ConfigHelper.ConfigValueListener<Boolean> JELLYFISH_FISHABLE;
    public ConfigHelper.ConfigValueListener<Boolean> NETS_FISHABLE;

    public ConfigHelper.ConfigValueListener<Integer> SPATULA_DAMAGE;
    public ConfigHelper.ConfigValueListener<Integer> GOLDEN_SPATULA_DAMAGE;
    public ConfigHelper.ConfigValueListener<Integer> KARATE_DAMAGE;
    public ConfigHelper.ConfigValueListener<Integer> MASTER_KARATE_DAMAGE;
    public ConfigHelper.ConfigValueListener<Integer> POWER_KARATE_DAMAGE;


    public JellyfishingConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.push("General");
        this.BIOME_WEIGHT= subscriber.subscribe(builder
                .comment("Biome Weight of Jellyfish Fields")
                .defineInRange("jellyfish_fields_weight", 6, 1, 100));
        this.JELLYFISH_STING= subscriber.subscribe(builder
                .comment("Should Jellyfish sting?")
                .define("jellyfish_sting", true, o -> o instanceof Boolean));
        this.CAUGHT_JELLYFISH_STING= subscriber.subscribe(builder
                .comment("Should Caught and Released Jellyfish sting?")
                .define("caught_jellyfish_sting", false, o -> o instanceof Boolean));
        this.JELLYFISH_FISHABLE= subscriber.subscribe(builder
                .comment("Should Jellyfish be fishable with a fishing rod?")
                .define("jellyfish_fishable", true, o -> o instanceof Boolean));
        this.NETS_FISHABLE = subscriber.subscribe(builder
                .comment("Should Jellyfish Nets be fishable with a fishing rod?")
                .define("nets_fishable", true, (o) -> { return o instanceof Boolean; }));
        builder.push("Attack Damages");
        this.SPATULA_DAMAGE= subscriber.subscribe(builder
                .comment("Damage of the Spatula")
                .defineInRange("spatula_damage", 6, 1, 1000));
        this.GOLDEN_SPATULA_DAMAGE= subscriber.subscribe(builder
                .comment("Damage of the Golden Spatula")
                .defineInRange("golden_spatula_damage", 8, 1, 1000));
        this.KARATE_DAMAGE= subscriber.subscribe(builder
                .comment("Damage of the Karate Glove")
                .defineInRange("karate_damage", 3, 1, 1000));
        this.MASTER_KARATE_DAMAGE= subscriber.subscribe(builder
                .comment("Damage of the Master Karate Glove")
                .defineInRange("master_karate_damage", 5, 1, 1000));
        this.POWER_KARATE_DAMAGE= subscriber.subscribe(builder
                .comment("Damage of the Power Karate Glove")
                .defineInRange("power_karate_damage", 18, 1, 1000));

        builder.pop();
    }

}
