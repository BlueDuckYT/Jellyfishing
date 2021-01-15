package blueduck.jellyfishing.client.entity.model;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class SandySuitModel extends BipedModel<LivingEntity> {
	private final ModelRenderer flower;
	private final ModelRenderer cube_r1;
	public SandySuitModel(float modelSize) {
		super(modelSize, 0.0F, 64, 64);
		textureWidth = 64;
		textureHeight = 64;

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, 0.0F, -1.0F);
		bipedHead.setTextureOffset(5, 0).addBox(-6.0F, -11.0F, -5.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		bipedHeadwear.showModel = false;

		flower = new ModelRenderer(this);
		flower.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(flower);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-5.7362F, -11.2977F, -5.2504F);
		flower.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.3927F, 0.3927F);
		cube_r1.setTextureOffset(0, 26).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, false);

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.setTextureOffset(13, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.2F, false);

		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		bipedRightArm.setTextureOffset(37, 25).addBox(-3.2F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.4F, false);

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		bipedLeftArm.setTextureOffset(37, 25).addBox(-0.6F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.4F, false);

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		bipedRightLeg.setTextureOffset(0, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.1F, false);
		bipedRightLeg.setTextureOffset(31, 42).addBox(-3F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		bipedLeftLeg.setTextureOffset(0, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.1F, false);
		bipedLeftLeg.setTextureOffset(31, 42).addBox(-2.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setVisible(boolean visible) {
		this.bipedHead.showModel = visible;
		this.bipedHeadwear.showModel = false;
		this.bipedBody.showModel = visible;
		this.bipedRightArm.showModel = visible;
		this.bipedLeftArm.showModel = visible;
		this.bipedRightLeg.showModel = visible;
		this.bipedLeftLeg.showModel = visible;
	}

	public Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.bipedBody, this.bipedRightArm, this.bipedLeftArm, this.bipedRightLeg, this.bipedLeftLeg);
	}

}