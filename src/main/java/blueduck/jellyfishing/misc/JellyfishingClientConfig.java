package blueduck.jellyfishing.misc;

import net.minecraftforge.common.ForgeConfigSpec;

public class JellyfishingClientConfig {


    public ConfigHelper.ConfigValueListener<Boolean> MUSIC;


    public JellyfishingClientConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.push("General");


        this.MUSIC = subscriber.subscribe(builder
                .comment("Enable custom music in the Jellyfish Fields?")
                .define("music", true, o -> o instanceof Boolean));


        builder.pop();
    }

}
