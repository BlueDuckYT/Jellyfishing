package blueduck.jellyfishing.jellyfishingmod.items;

import net.minecraft.item.Item;

public class JellyfishNetItem extends Item {
    public JellyfishNetItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getItemEnchantability() {
        return 30;
    }
}
