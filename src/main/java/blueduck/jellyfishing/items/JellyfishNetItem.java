package blueduck.jellyfishing.items;

import net.minecraft.item.Item;

import net.minecraft.item.Item.Properties;

public class JellyfishNetItem extends Item {
    public JellyfishNetItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getItemEnchantability() {
        return 30;
    }
}
