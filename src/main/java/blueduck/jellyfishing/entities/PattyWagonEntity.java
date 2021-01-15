package blueduck.jellyfishing.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.World;

public class PattyWagonEntity extends MinecartEntity {
    public PattyWagonEntity(EntityType<?> p_i50126_1_, World p_i50126_2_) {
        super(p_i50126_1_, p_i50126_2_);
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return new SSpawnObjectPacket(this);
    }
}
