package blueduck.jellyfishing.jellyfishingmod.client;

import blueduck.jellyfishing.jellyfishingmod.client.entity.model.SandySuitModel;
import net.minecraft.inventory.EquipmentSlotType;

public class ClientReference {

    private static final SandySuitModel SandySuitModel = new SandySuitModel(1.0f);
    private static final SandySuitModel SandySuitLeggings = new SandySuitModel(0.5f);

    public static <A> A getSandySuitModel(EquipmentSlotType armorSlot) {
        return (A) (armorSlot == EquipmentSlotType.LEGS ? SandySuitLeggings : SandySuitModel);
    }
}