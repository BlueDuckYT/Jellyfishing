package blueduck.jellyfishing.jellyfishingmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class JellyfishItem extends Item {

    public Item PRODUCE_ITEM;
    public int PRODUCE_AMOUNT;
    public boolean BREEDABLE;
    public ResourceLocation entityTexture;

    public JellyfishItem(Properties properties, Item Output, ResourceLocation resloc) {
        super(properties);
        PRODUCE_ITEM = Output;
        entityTexture = resloc;
    }

    public ItemStack GetOutputItemStack() {
        return new ItemStack(PRODUCE_ITEM, PRODUCE_AMOUNT);
    }

    public boolean isBreedable() {
        return BREEDABLE;
    }

    public ResourceLocation getEntityTexture() {
        return entityTexture;
    }

}
