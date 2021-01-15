package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.client.entity.model.BlueJellyfishModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BlueJellyfishRenderer extends MobRenderer<BlueJellyfishEntity, BlueJellyfishModel> {

    public BlueJellyfishRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BlueJellyfishModel(), 0.3F);
    }


    @Override
    public ResourceLocation getEntityTexture(BlueJellyfishEntity entity) {
        return new ResourceLocation("jellyfishing", "textures/entity/blue_jellyfish.png");
    }
}
