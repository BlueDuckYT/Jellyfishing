package blueduck.jellyfishing.jellyfishingmod.misc;

import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Locale;

public class CloudParticle extends SpriteTexturedParticle {

    private double posX, posY, posZ;

    public CloudParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
                         double ySpeedIn, double zSpeedIn) {
        super((ClientWorld) worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
//        if (this.rand.nextFloat() < 0.975) {
//            this.posY = 0;
//            this.setExpired();
//        }

        this.motionX = 0.1f * (this.rand.nextFloat()) - 0.05f;
        this.motionY = 0.1f * (this.rand.nextFloat()) - 0.05f;
        this.motionZ = 0.1f * (this.rand.nextFloat()) - 0.05f;
        this.posX = xCoordIn;
        this.posY = yCoordIn;
        this.posZ = zCoordIn;
        this.particleScale = 0.1f * (this.rand.nextFloat() * 0.5f + 1.7f);
        this.particleRed = .5F;
        this.particleGreen = .5F;
        this.particleBlue = .5F;
        this.maxAge = (int) (Math.random() * 100.0d) + 400;
    }

    @Override
    public void tick() {
        this.prevPosX = posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else if (false) {
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteIn) {
            this.spriteSet = spriteIn;
        }

        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            CloudParticle particle = new CloudParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.selectSpriteRandomly(spriteSet);
            return particle;
        }
    }
}

