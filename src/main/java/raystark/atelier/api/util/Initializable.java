package raystark.atelier.api.util;

public interface Initializable {
    /**
     * インスタンスの初期化を行います。
     *
     * <p>このメソッドはMod本体のinit(FMLInitializationEvent event)上で呼ぶ必要があります。
     */
    void init();

    /**
     * このインスタンスが初期化済みならtrueを返します。
     *
     * @return インスタンスが初期化済みの場合true
     */
    boolean hasInit();
}
