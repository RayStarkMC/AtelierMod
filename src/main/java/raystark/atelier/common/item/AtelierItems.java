package raystark.atelier.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.util.LinkedList;
import java.util.List;

public final class AtelierItems {
    private static List<Item> items;

    public static Item samplePickaxe;
    public static Item bandage;

    private AtelierItems() {}

    public static void init() {
        items = new LinkedList<>();
        samplePickaxe = new SamplePickaxe();
        bandage = new Bandage();

        items.forEach(item -> GameRegistry.registerItem(item, ""));
    }
}
