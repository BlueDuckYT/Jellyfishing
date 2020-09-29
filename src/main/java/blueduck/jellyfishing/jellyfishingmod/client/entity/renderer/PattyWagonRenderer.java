package blueduck.jellyfishing.jellyfishingmod.client.entity.renderer;

import blueduck.jellyfishing.jellyfishingmod.client.entity.model.BlueJellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.client.entity.model.JellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.client.entity.model.PattyWagonModel;
import blueduck.jellyfishing.jellyfishingmod.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.PattyWagonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.MinecartModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PattyWagonRenderer<T extends PattyWagonEntity> extends EntityRenderer<T> {


    public PattyWagonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }


    @Override
    public ResourceLocation getEntityTexture(PattyWagonEntity entity) {
        return new ResourceLocation("jellyfishing", "textures/entity/patty_wagon");
    }


    protected void renderBlockState(T entityIn, float partialTicks, BlockState stateIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(stateIn, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
    }
}
