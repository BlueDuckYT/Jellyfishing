package blueduck.jellyfishing.client.entity.model;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import blueduck.jellyfishing.entities.PattyWagonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;

//Model by Evo

public class PattyWagonModel extends EntityModel<PattyWagonEntity> {
	private final ModelRenderer front;
	private final ModelRenderer back;
	private final ModelRenderer right;
	private final ModelRenderer left;
	private final ModelRenderer base;
	private final ModelRenderer plate;
	private final ModelRenderer wheel1;
	private final ModelRenderer wheel2;
	private final ModelRenderer wheel3;
	private final ModelRenderer wheel4;

	public PattyWagonModel() {
		textureWidth = 128;
		textureHeight = 128;

		front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(front, 0.0F, 3.1416F, 0.0F);
		front.setTextureOffset(0, 47).addBox(-8.0F, -10.0F, 8.0F, 16.0F, 8.0F, 2.0F, 0.0F, false);
		front.setTextureOffset(16, 18).addBox(-7.0F, -14.0F, 4.0F, 14.0F, 4.0F, 5.0F, 0.0F, false);
		front.setTextureOffset(8, 40).addBox(4.0F, -9.0F, 10.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		front.setTextureOffset(0, 40).addBox(-7.0F, -9.0F, 10.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		front.setTextureOffset(35, 63).addBox(-2.5F, -11.0F, 6.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, 24.0F, 0.0F);
		back.setTextureOffset(44, 0).addBox(-8.0F, -10.0F, 8.0F, 16.0F, 8.0F, 2.0F, 0.0F, false);
		back.setTextureOffset(0, 50).addBox(7.0F, -24.0F, 7.0F, 0.0F, 15.0F, 7.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(right, 0.0F, -1.5708F, 0.0F);
		right.setTextureOffset(26, 37).addBox(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F, 0.0F, false);

		left = new ModelRenderer(this);
		left.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(left, 0.0F, 1.5708F, 0.0F);
		left.setTextureOffset(26, 27).addBox(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F, 0.0F, false);

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(base, 0.0F, -1.5708F, 1.5708F);
		base.setTextureOffset(0, 0).addBox(-10.0F, -8.0F, 0.0F, 20.0F, 16.0F, 2.0F, 0.0F, false);
		base.setTextureOffset(36, 47).addBox(-2.0F, -6.0F, 2.0F, 10.0F, 12.0F, 2.0F, 0.0F, false);
		base.setTextureOffset(0, 18).addBox(5.0F, -6.0F, 4.0F, 3.0F, 12.0F, 10.0F, 0.0F, false);

		plate = new ModelRenderer(this);
		plate.setRotationPoint(-10.4F, 0.0F, 1.0F);
		base.addChild(plate);
		setRotationAngle(plate, 0.0F, 0.1745F, 0.0F);
		plate.setTextureOffset(0, 14).addBox(0.0F, -3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 0.0F, false);

		wheel1 = new ModelRenderer(this);
		wheel1.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel1.setTextureOffset(58, 15).addBox(8.0F, -4.0F, -8.0F, 2.0F, 5.0F, 5.0F, 0.0F, false);

		wheel2 = new ModelRenderer(this);
		wheel2.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel2.setTextureOffset(14, 57).addBox(8.0F, -4.0F, 4.0F, 2.0F, 5.0F, 5.0F, 0.0F, false);

		wheel3 = new ModelRenderer(this);
		wheel3.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel3.setTextureOffset(55, 56).addBox(-10.0F, -4.0F, -8.0F, 2.0F, 5.0F, 5.0F, 0.0F, false);

		wheel4 = new ModelRenderer(this);
		wheel4.setRotationPoint(0.0F, 24.0F, 0.0F);
		wheel4.setTextureOffset(49, 10).addBox(-10.0F, -4.0F, 4.0F, 2.0F, 5.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PattyWagonEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		front.render(matrixStack, buffer, packedLight, packedOverlay);
		back.render(matrixStack, buffer, packedLight, packedOverlay);
		right.render(matrixStack, buffer, packedLight, packedOverlay);
		left.render(matrixStack, buffer, packedLight, packedOverlay);
		base.render(matrixStack, buffer, packedLight, packedOverlay);
		wheel1.render(matrixStack, buffer, packedLight, packedOverlay);
		wheel2.render(matrixStack, buffer, packedLight, packedOverlay);
		wheel3.render(matrixStack, buffer, packedLight, packedOverlay);
		wheel4.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}



}