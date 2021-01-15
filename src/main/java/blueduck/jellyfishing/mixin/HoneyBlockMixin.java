package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HoneyBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(HoneyBlock.class)
public abstract class HoneyBlockMixin {

    @OnlyIn(Dist.CLIENT)
    @Overwrite
    public static void slideParticles(Entity entity, int particleCount) {
        if (entity.world.isRemote) {
            BlockState blockstate = isNearJelly(entity.getPosition(), entity.getEntityWorld()) ? JellyfishingBlocks.JELLY_BLOCK.get().getDefaultState() : Blocks.HONEY_BLOCK.getDefaultState();

            for(int i = 0; i < particleCount; ++i) {
                entity.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate), entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
            }

        }
    }

    private static boolean isNearJelly(BlockPos pos, IWorld worldIn) {
        boolean flag = false;
        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 3; j++) {
                for (int k = -1; k < 3; k++) {
                    if (worldIn.getBlockState(pos.add(i, j, k)).equals(JellyfishingBlocks.JELLY_BLOCK.get().getDefaultState()))
                        flag = true;
                }
            }
        }
        return flag;
    }
}
