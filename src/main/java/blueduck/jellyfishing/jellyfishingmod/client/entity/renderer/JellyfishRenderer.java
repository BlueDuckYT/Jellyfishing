package blueduck.jellyfishing.jellyfishingmod.client.entity.renderer;

import blueduck.jellyfishing.jellyfishingmod.client.entity.model.JellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class JellyfishRenderer extends MobRenderer<JellyfishEntity, JellyfishModel> {

    public JellyfishRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new JellyfishModel(), 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture(JellyfishEntity entity) {
        return new ResourceLocation("jellyfishing", "textures/entity/jellyfish.png");
    }
}
