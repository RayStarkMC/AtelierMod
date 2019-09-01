package raystark.atelier.common.item;

import net.minecraft.item.Item;

public final class AtelierItems {
    public static Item samplePickaxe;
    public static Item bandage;

    private AtelierItems() {}

    public static void init() {
        samplePickaxe = new SamplePickaxe();
        bandage = new Bandage();
    }
}
