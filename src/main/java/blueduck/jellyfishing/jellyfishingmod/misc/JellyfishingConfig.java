package blueduck.jellyfishing.jellyfishingmod.misc;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class JellyfishingConfig {


    public ConfigHelper.ConfigValueListener<Integer> BIOME_WEIGHT;
    public ConfigHelper.ConfigValueListener<Boolean> JELLYFISH_STING;


    public JellyfishingConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.push("General");
        this.BIOME_WEIGHT= subscriber.subscribe(builder
                .comment("Biome Weight of Jellyfish Fields")
                .defineInRange("jellyfish_fields_weight", 6, 1, 100));
        this.JELLYFISH_STING= subscriber.subscribe(builder
                .comment("Should Jellyfish sting?")
                .define("jellyfish_sting", true, o -> o instanceof Boolean));
        builder.pop();
    }

}
