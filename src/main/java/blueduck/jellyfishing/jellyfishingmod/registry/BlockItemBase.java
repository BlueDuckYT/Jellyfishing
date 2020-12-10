package blueduck.jellyfishing.jellyfishingmod.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem {
    public BlockItemBase(Block p_i48527_1_) {
        super(p_i48527_1_, new Properties().group(ItemGroup.BUILDING_BLOCKS));
    }

    public BlockItemBase(Block p_i48527_1_, Properties properties) {
        super(p_i48527_1_, properties);
    }
}
