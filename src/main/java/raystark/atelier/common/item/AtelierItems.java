package raystark.atelier.common.item;

public final class AtelierItems {
    public static SamplePickaxe samplePickaxe;

    private AtelierItems() {}

    public static void init() {
        samplePickaxe = new SamplePickaxe("SamplePickaxe", 1);
    }
}
