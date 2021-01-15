package blueduck.jellyfishing.misc;

import net.minecraftforge.common.ForgeConfigSpec;

public class JellyfishingClientConfig {


    public ConfigHelper.ConfigValueListener<Boolean> FLOWER_CLOUDS;
    public ConfigHelper.ConfigValueListener<Boolean> MUSIC;


    public JellyfishingClientConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.push("General");

        this.FLOWER_CLOUDS = subscriber.subscribe(builder
                .comment("Enable Flower Clouds?")
                .define("flower_clouds", true, o -> o instanceof Boolean));
        this.MUSIC = subscriber.subscribe(builder
                .comment("Enable custom music in the Jellyfish Fields?")
                .define("music", true, o -> o instanceof Boolean));


        builder.pop();
    }

}
