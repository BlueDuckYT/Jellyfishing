package blueduck.jellyfishing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import net.minecraft.block.AbstractBlock.Properties;

public class JellyfishMachineBlock extends Block {

    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_3;

    public JellyfishMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;//JellyfishingTileEntities.JELLYFISH_MACHINE.get().create();
    }
}
