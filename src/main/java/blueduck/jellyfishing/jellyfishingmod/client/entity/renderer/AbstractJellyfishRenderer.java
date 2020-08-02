package blueduck.jellyfishing.jellyfishingmod.client.entity.renderer;

import blueduck.jellyfishing.jellyfishingmod.client.entity.model.AbstractJellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.client.entity.model.JellyfishModel;
import blueduck.jellyfishing.jellyfishingmod.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.jellyfishingmod.entities.JellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AbstractJellyfishRenderer extends MobRenderer<AbstractJellyfishEntity, AbstractJellyfishModel> {

    public ResourceLocation jellyfishTexture;

    public AbstractJellyfishRenderer(EntityRendererManager renderManagerIn, ResourceLocation texture) {
        super(renderManagerIn, new AbstractJellyfishModel(), 0.3F);
        jellyfishTexture = texture;
    }

    @Override
    public ResourceLocation getEntityTexture(AbstractJellyfishEntity entity) {
        return jellyfishTexture;
    }
}
