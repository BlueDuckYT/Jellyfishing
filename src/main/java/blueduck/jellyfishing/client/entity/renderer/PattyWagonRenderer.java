package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.entities.PattyWagonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

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
