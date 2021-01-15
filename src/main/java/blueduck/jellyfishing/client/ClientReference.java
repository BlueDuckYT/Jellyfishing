package blueduck.jellyfishing.client;

import blueduck.jellyfishing.client.entity.model.SandySuitModel;
import net.minecraft.inventory.EquipmentSlotType;

public class ClientReference {

    private static final blueduck.jellyfishing.client.entity.model.SandySuitModel SandySuitModel = new SandySuitModel(1.0f);
    private static final SandySuitModel SandySuitLeggings = new SandySuitModel(0.5f);

    public static <A> A getSandySuitModel(EquipmentSlotType armorSlot) {
        return (A) (armorSlot == EquipmentSlotType.LEGS ? SandySuitLeggings : SandySuitModel);
    }
}