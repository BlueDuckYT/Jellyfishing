package blueduck.jellyfishing.jellyfishingmod.blocks;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class JellyMachineTE extends TileEntity implements ITickableTileEntity {
    public JellyMachineTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public JellyMachineTE() {
        this(null);//JellyfishingTileEntities.JELLYFISH_MACHINE.get());
    }

    @Override
    public void tick() {

    }
}
