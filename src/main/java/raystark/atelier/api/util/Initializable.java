package raystark.atelier.api.util;

public interface Initializable {
    /**
     * インスタンスの初期化を行います。
     *
     * <p>このメソッドはMod本体のinit(FMLInitializationEvent event)上で呼ぶ必要があります。
     */
    void init();

    boolean hasInit();
}
