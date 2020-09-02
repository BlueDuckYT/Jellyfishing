package blueduck.jellyfishing.jellyfishingmod.items;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class JellyfishingMusicDisc extends MusicDiscItem {
    public JellyfishingMusicDisc(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder) {
        super(comparatorValue, soundSupplier, builder.maxStackSize(1));
    }


}
