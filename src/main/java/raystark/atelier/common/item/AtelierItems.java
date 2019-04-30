package raystark.atelier.common.item;

public class AtelierItems {
    public static SamplePickaxe samplePickaxe;

    private AtelierItems() {}

    public static void init() {
        samplePickaxe = new SamplePickaxe();
    }
}
