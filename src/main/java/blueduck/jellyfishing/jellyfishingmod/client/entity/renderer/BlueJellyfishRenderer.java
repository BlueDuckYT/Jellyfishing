package blueduck.jellyfishing.jellyfishingmod.client.entity.renderer;

import blueduck.jellyfishing.jellyfishingmod.client.entity.model.BlueJellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.client.entity.model.JellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
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
