package blueduck.jellyfishing.jellyfishingmod.blocks;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

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
