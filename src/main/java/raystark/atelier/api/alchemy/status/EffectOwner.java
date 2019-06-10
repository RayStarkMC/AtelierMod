package raystark.atelier.api.alchemy.status;


import raystark.atelier.api.alchemy.effect.IEffect;

import java.util.List;

/**
 * 効果を持つものを表すインターフェース
 */
public interface EffectOwner {
    /**
     * このアイテムに付与された効果一覧<p/>
     *
     * @return 効果のリスト
     */
    List<IEffect> getEffectList();
}
