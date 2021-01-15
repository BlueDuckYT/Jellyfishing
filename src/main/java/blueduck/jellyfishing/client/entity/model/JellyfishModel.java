package blueduck.jellyfishing.client.entity.model;


import blueduck.jellyfishing.entities.JellyfishEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Jellyfish - Coda1552
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class JellyfishModel extends EntityModel<JellyfishEntity> {
    public ModelRenderer body;
    public ModelRenderer tentacle1;
    public ModelRenderer tentacle2;
    public ModelRenderer tentacle3;
    public ModelRenderer tentacle4;

    public JellyfishModel() {
        this.textureWidth = 24;
        this.textureHeight = 12;
        this.tentacle3 = new ModelRenderer(this, 0, 0);
        this.tentacle3.setRotationPoint(-1.5F, 3.0F, -1.5F);
        this.tentacle3.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tentacle2 = new ModelRenderer(this, 0, 0);
        this.tentacle2.setRotationPoint(-1.5F, 3.0F, 1.5F);
        this.tentacle2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.body.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.tentacle4 = new ModelRenderer(this, 0, 0);
        this.tentacle4.setRotationPoint(1.5F, 3.0F, -1.5F);
        this.tentacle4.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tentacle1 = new ModelRenderer(this, 0, 0);
        this.tentacle1.setRotationPoint(1.5F, 3.0F, 1.5F);
        this.tentacle1.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.tentacle3);
        this.body.addChild(this.tentacle2);
        this.body.addChild(this.tentacle4);
        this.body.addChild(this.tentacle1);
    }

    @Override
    public void setRotationAngles(JellyfishEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if  (!entityIn.isInWater()) {
            body.rotateAngleX = (float) (30 + MathHelper.sin(limbSwing) * 0.5);
        }
        else {
            //body.rotateAngleX = 30;//(float) (entityIn.dirY * -180) + 90;
            //body.rotateAngleY = (float) (360 +(Math.atan2(entityIn.dirZ, entityIn.dirX) % (Math.PI)))%360;
            body.rotateAngleX = 0f;
        }

        float rotateVal = ageInTicks * 0.1F;

        tentacle1.rotateAngleX = Math.abs(MathHelper.sin(rotateVal)) * 0.5F;
        tentacle1.rotateAngleZ = Math.abs(MathHelper.sin(rotateVal)) * -0.5F;

        tentacle2.rotateAngleX = Math.abs(MathHelper.sin(rotateVal)) * 0.5F;
        tentacle2.rotateAngleZ = Math.abs(MathHelper.sin(rotateVal)) * 0.5F;

        tentacle3.rotateAngleX = Math.abs(MathHelper.sin(rotateVal)) * -0.5F;
        tentacle3.rotateAngleZ = Math.abs(MathHelper.sin(rotateVal)) * 0.5F;

        tentacle4.rotateAngleX = Math.abs(MathHelper.sin(rotateVal)) * -0.5F;
        tentacle4.rotateAngleZ = Math.abs(MathHelper.sin(rotateVal)) * -0.5F;

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
