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
        builder.pop();
    }

}
