package blueduck.jellyfishing.jellyfishingmod.client.entity.model;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import blueduck.jellyfishing.jellyfishingmod.entities.SpatulaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SpatulaModel extends EntityModel<SpatulaEntity> {
	private final ModelRenderer bone;

	public SpatulaModel() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(8.0F, 24.0F, -8.0F);
		bone.setTextureOffset(0, 18).addBox(-10.0F, -8.0F, 7.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(12, 18).addBox(-9.0F, -17.0F, 8.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 28).addBox(-10.0F, -18.0F, 8.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 30).addBox(-11.0F, -19.0F, 8.0F, 5.0F, 1.0F, 1.0F, 0.0F, true);
		bone.setTextureOffset(0, 12).addBox(-12.0F, -24.0F, 8.0F, 7.0F, 5.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SpatulaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}