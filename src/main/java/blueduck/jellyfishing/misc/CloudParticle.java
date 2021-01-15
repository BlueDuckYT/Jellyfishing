package blueduck.jellyfishing.misc;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class CloudParticle extends SpriteTexturedParticle {

    private double posX, posY, posZ;

    public CloudParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
                         double ySpeedIn, double zSpeedIn) {
        super((ClientWorld) worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.motionX = 0.1f * (this.rand.nextFloat()) - 0.05f + 2;
        this.motionY = 0.1f * (this.rand.nextFloat()) - 0.05f;
        this.motionZ = 0.1f * (this.rand.nextFloat()) - 0.05f;
        this.posX = xCoordIn;
        this.posY = yCoordIn;
        this.posZ = zCoordIn;
        this.particleScale = 10f * (this.rand.nextFloat() * 0.5f + 1.7f);
        this.particleRed = .5F;
        this.particleGreen = .5F;
        this.particleBlue = .5F;
        this.maxAge = (int) (Math.random() * 100.0d) + 2000;
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
            if (worldIn.isDaytime()) {
                y = 148;
            }
            else {
                y = 0;
            }
            CloudParticle particle = new CloudParticle(worldIn, x + (256 * worldIn.rand.nextDouble()) - 128, y, z + (256 * worldIn.rand.nextDouble()) - 128, xSpeed, ySpeed, zSpeed);
            particle.selectSpriteRandomly(spriteSet);
            return particle;
        }
    }
}

