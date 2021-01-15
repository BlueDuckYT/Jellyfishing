package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.client.entity.model.AbstractJellyfishModel;
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
