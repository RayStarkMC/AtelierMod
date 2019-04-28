package raystark.atelier.api.alchemy;

/**
 * 品質値を持つものを表すインターフェース.<p/>
 *
 * 品質値は完成品の性能に影響を与える場合がある.
 */
public interface Quality {
    /**
     * 品質の最低値
     */
    int MIN_VALUE = 1;

    /**
     * 品質値を返す
     * @return 品質値
     */
    int getQuality();
}
